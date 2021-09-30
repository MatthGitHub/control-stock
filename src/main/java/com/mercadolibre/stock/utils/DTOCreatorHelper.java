package com.mercadolibre.stock.utils;

import com.mercadolibre.stock.model.dto.ItemDTO;
import com.mercadolibre.stock.model.dto.StockDTO;
import com.mercadolibre.stock.model.entity.Stock;

public class DTOCreatorHelper {
    public static StockDTO createStockDTOfromEntity(Stock stock) {
        return new StockDTO(
                stock.getLocation().getCode(),
                stock.getLocation().getDeposit().getCode(),
                stock.getQuantity()
        );
    }

    public static ItemDTO createItemDTOfromEntity(Stock stock) {
        return new ItemDTO(
                stock.getItemId(),
                stock.getQuantity()
        );
    }
}
