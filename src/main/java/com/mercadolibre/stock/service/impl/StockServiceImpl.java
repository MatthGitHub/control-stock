package com.mercadolibre.stock.service.impl;

import com.mercadolibre.stock.exception.CustomException;
import com.mercadolibre.stock.model.dto.StockDTO;
import com.mercadolibre.stock.model.dto.ml.ItemDTO;
import com.mercadolibre.stock.model.entity.Deposit;
import com.mercadolibre.stock.model.entity.Location;
import com.mercadolibre.stock.model.entity.Stock;
import com.mercadolibre.stock.repository.StockRepository;
import com.mercadolibre.stock.service.DepositService;
import com.mercadolibre.stock.service.LocationService;
import com.mercadolibre.stock.service.MercadoLibreService;
import com.mercadolibre.stock.service.StockService;
import com.mercadolibre.stock.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.Objects;

@Service("stockService")
public class StockServiceImpl implements StockService {
    @Value("${spring.application.location.items.diff.max}")
    private int LOCATION_ITEMS_DIFF_MAX;
    @Value("${spring.application.location.items.total}")
    private int LOCATION_ITEMS_TOTAL;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private MercadoLibreService mercadoLibreService;
    @Autowired
    private LocationService locationService;
    @Autowired
    private DepositService depositService;
    @Autowired
    private MessageSource messageSource;

    @Transactional
    @Override
    public StockDTO add(String itemId, StockDTO stockDTO) {
        ItemDTO itemDTO = mercadoLibreService.getItemById(itemId);
        if(Objects.isNull(itemDTO)
                || Objects.isNull(itemDTO.getShippingDTO())
                || !itemDTO.getShippingDTO().getLogisticType().equals(Constants.LOGICTIC_FULFILLMENT))
            throw new CustomException(messageSource.getMessage("api.error.internal.data.deposit", new Object[]{itemDTO.getId()}, Locale.ENGLISH));

        if(stockRepository.countByLocationCode(stockDTO.getLocation()) >= LOCATION_ITEMS_DIFF_MAX)
            throw new CustomException(messageSource.getMessage("api.error.location.items.diff.limit", new Object[]{stockDTO.getLocation()}, Locale.ENGLISH));

        Location location = locationService.get(stockDTO.getLocation(), stockDTO.getDeposit());
        int totalItems = (Objects.nonNull(location)) ? location.getStocks().stream().mapToInt(Stock::getQuantity).sum() : 0;
        if ((totalItems + stockDTO.getQuantity()) > LOCATION_ITEMS_TOTAL)
            throw new CustomException(messageSource.getMessage("api.error.location.items.total", new Object[]{stockDTO.getLocation()}, Locale.ENGLISH));

        Stock stock = stockRepository.findByItemIdAndLocationCodeAndLocationDepositCode(
                itemId, stockDTO.getLocation(), stockDTO.getDeposit()
        ).orElseGet(() -> buildNewStock(itemId, stockDTO, location));

        stock.addQuantity(stockDTO.getQuantity());
        save(stock);

        return stockDTO;
    }

    private Stock buildNewStock(String itemId, StockDTO stockDTO, Location location){
        Stock stock = new Stock(itemId);
        if(Objects.nonNull(location)) {
            stock.setLocation(location);
        } else {
            Deposit deposit = depositService.get(stockDTO.getDeposit());
            if(Objects.isNull(deposit))
                deposit = new Deposit(stockDTO.getDeposit());
            stock.setLocation(new Location(stockDTO.getLocation(), deposit));
        }
        return stock;
    }

    private Stock save(Stock stock){
        try {
            return stockRepository.saveAndFlush(stock);
        } catch (Exception e) {
            throw new CustomException(messageSource.getMessage("api.error.internal.data.persistence", null, Locale.ENGLISH), e);
        }
    }
}
