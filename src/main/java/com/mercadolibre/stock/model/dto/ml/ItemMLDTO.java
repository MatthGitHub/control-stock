package com.mercadolibre.stock.model.dto.ml;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemMLDTO {
    private String id;
    @JsonProperty("shipping")
    private ShippingDTO shippingDTO;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShippingDTO getShippingDTO() {
        return shippingDTO;
    }

    public void setShippingDTO(ShippingDTO shippingDTO) {
        this.shippingDTO = shippingDTO;
    }
}