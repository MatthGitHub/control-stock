package com.mercadolibre.stock.service;

import com.mercadolibre.stock.model.dto.ml.ItemMLDTO;

public interface MercadoLibreService {
    ItemMLDTO getItemById(String itemId);
}
