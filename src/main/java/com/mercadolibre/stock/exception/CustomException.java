package com.mercadolibre.stock.exception;

import com.mercadolibre.stock.model.dto.ErrorDTO;

public class CustomException extends RuntimeException {
    private Integer statusCode;
    private String error;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(String message, String error){
        super(message);
        this.error = error;
    }

    public CustomException(String message, String error, Integer statusCode){
        super(message);
        this.error = error;
        this.statusCode = statusCode;
    }

    public CustomException(ErrorDTO errorDTO){
        this(errorDTO.getMessage(), errorDTO.getError(), errorDTO.getStatus());
    }

    public CustomException(String message, Integer statusCode, Exception exception) {
        super(message, exception);
        this.statusCode = statusCode;
    }

    public CustomException(String message, Exception exception) {
        super(message, exception);
    }

    public String getError() {
        return error;
    }

    public Integer getStatusCode() {
        return statusCode;
    }
}