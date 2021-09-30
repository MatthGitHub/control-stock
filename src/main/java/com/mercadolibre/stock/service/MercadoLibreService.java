package com.mercadolibre.stock.service;

import com.mercadolibre.stock.model.dto.ml.ItemDTO;

public interface MercadoLibreService {
    ItemDTO getItemById(String itemId);
}
