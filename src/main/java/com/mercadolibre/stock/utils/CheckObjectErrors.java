package com.mercadolibre.stock.utils;

import com.mercadolibre.stock.model.dto.ErrorDTO;
import com.mercadolibre.stock.model.dto.ErrorFieldDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Set;
import java.util.stream.Collectors;

public class CheckObjectErrors {
    public static ResponseEntity<?> map(BindingResult bindingResult) {
        Set<String> errorMessageSet = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> new String(fieldError.getField() + ": " + fieldError.getDefaultMessage()))
                .collect(Collectors.toSet());

        if(errorMessageSet.isEmpty()){
            errorMessageSet = bindingResult.getAllErrors()
                    .stream()
                    .map(objectError -> new String(objectError.getObjectName() + ": " +objectError.getDefaultMessage()))
                    .collect(Collectors.toSet());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorFieldDTO(errorMessageSet));
    }
}