package com.mercadolibre.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ItemDTO {
    private String itemId;
    private int quantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String location;

    public ItemDTO(){}

    public ItemDTO(String itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public ItemDTO(String location, String itemId, int quantity){
        this.location = location;
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
