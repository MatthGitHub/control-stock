package com.mercadolibre.stock.service;

import com.mercadolibre.stock.model.dto.StockDTO;

public interface StockService {
    StockDTO add(String itemId, StockDTO stockDTO);
}
