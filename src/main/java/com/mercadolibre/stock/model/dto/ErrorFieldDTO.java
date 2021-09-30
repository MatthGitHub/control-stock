package com.mercadolibre.stock.model.dto;

import java.util.Set;

public class ErrorFieldDTO {
    private Set<String> message;

    public ErrorFieldDTO(){}

    public ErrorFieldDTO(Set<String> message){
        this.message = message;
    }

    public Set<String> getMessage() {
        return message;
    }

    public void setMessage(Set<String> message) {
        this.message = message;
    }
}
