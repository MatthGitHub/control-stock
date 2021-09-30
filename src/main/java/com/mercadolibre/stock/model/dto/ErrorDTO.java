package com.mercadolibre.stock.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ErrorDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object[] cause;

    public ErrorDTO(){}

    public ErrorDTO(String message){
        this.message = message;
    }

    public ErrorDTO(String message, Integer status){
        this.message = message;
        this.status = status;
    }

    public ErrorDTO(String message, String error){
        this.message = message;
        this.error = error;
    }

    public ErrorDTO(String message, String error, Integer status){
        this.message = message;
        this.error = error;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Object[] getCause() {
        return cause;
    }

    public void setCause(Object[] cause) {
        this.cause = cause;
    }
}
