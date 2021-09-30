package com.mercadolibre.stock.controller;

import com.mercadolibre.stock.model.dto.StockDTO;
import com.mercadolibre.stock.service.StockService;
import com.mercadolibre.stock.utils.CheckObjectErrors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;

    @PutMapping("/items/{itemId}")
    public ResponseEntity<?> add(@PathVariable(name = "itemId") String itemId, @Valid @RequestBody StockDTO stockDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return CheckObjectErrors.map(bindingResult);

        return ResponseEntity.ok(stockService.add(itemId, stockDTO));
    }
}