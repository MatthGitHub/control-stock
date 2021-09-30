package com.mercadolibre.stock.model.dto;

import java.util.HashSet;
import java.util.Set;

public class ItemWrapperDTO {
    private String location;
    private String deposit;
    private int total;
    private Set<ItemDTO> items = new HashSet<>();

    public ItemWrapperDTO(){}

    public ItemWrapperDTO(String location, String deposit, int total, Set<ItemDTO> items){
        this.location = location;
        this.deposit = deposit;
        this.total = total;
        this.items = items;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Set<ItemDTO> getItems() {
        return items;
    }

    public void setItems(Set<ItemDTO> items) {
        this.items = items;
    }
}
