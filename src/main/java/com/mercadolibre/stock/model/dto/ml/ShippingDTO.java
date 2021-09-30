package com.mercadolibre.stock.model.dto.ml;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ShippingDTO {
    @JsonProperty("logistic_type")
    private String logisticType;

    public String getLogisticType() {
        return logisticType;
    }

    public void setLogisticType(String logisticType) {
        this.logisticType = logisticType;
    }
}
