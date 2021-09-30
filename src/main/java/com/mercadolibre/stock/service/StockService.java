package com.mercadolibre.stock.service;

import com.mercadolibre.stock.model.dto.ItemWrapperDTO;
import com.mercadolibre.stock.model.dto.PaginationWrapperDTO;
import com.mercadolibre.stock.model.dto.StockDTO;
import com.mercadolibre.stock.model.entity.Stock;

import java.util.UUID;


public interface StockService {
    StockDTO add(String itemId, StockDTO stockDTO);
    StockDTO consume(String itemId, StockDTO stockDTO);
    ItemWrapperDTO getByLocationDeposit(String locationCode, String depositCode);
    PaginationWrapperDTO search(String deposit, String itemId, int page, int size);
    Stock save(Stock stock);
    void removeForTestOnly(UUID id);
}