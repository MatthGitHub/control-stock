package com.mercadolibre.stock.model.dto;

import com.mercadolibre.stock.model.dto.validators.ValidDeposit;
import com.mercadolibre.stock.model.dto.validators.ValidQuantity;
import com.mercadolibre.stock.utils.PatternUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class StockDTO {
    @ValidDeposit
    private String deposit;
    @ValidQuantity
    private int quantity;
    @Pattern(regexp = PatternUtils.LOCATION_PATTERN, message = "{api.error.internal.format}")
    private String location;

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
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
