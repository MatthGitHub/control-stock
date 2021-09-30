package com.mercadolibre.stock.controller;

import com.mercadolibre.stock.model.dto.ItemWrapperDTO;
import com.mercadolibre.stock.model.dto.PaginationWrapperDTO;
import com.mercadolibre.stock.model.dto.StockDTO;
import com.mercadolibre.stock.service.StockService;
import com.mercadolibre.stock.utils.CheckObjectErrors;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class StockController {
    @Autowired
    private StockService stockService;

    @ApiOperation(value = "Cargar stock de productos", response = StockDTO.class)
    @PutMapping("/items/{itemId}")
    public ResponseEntity<?> add(@PathVariable(name = "itemId") String itemId, @Valid @RequestBody StockDTO stockDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return CheckObjectErrors.map(bindingResult);

        return ResponseEntity.ok(stockService.add(itemId, stockDTO));
    }

    @ApiOperation(value = "Retirar productos de stock", response = StockDTO.class)
    @PutMapping("/items/{itemId}/consume")
    public ResponseEntity<?> consume(@PathVariable(name = "itemId") String itemId, @Valid @RequestBody StockDTO stockDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return CheckObjectErrors.map(bindingResult);

        return ResponseEntity.ok(stockService.consume(itemId, stockDTO));
    }

    @ApiOperation(value = "Consultar productos segun ubicación y depósito", response = ItemWrapperDTO.class)
    @GetMapping("/items")
    public ResponseEntity<?> getByLocationDeposit(@RequestParam(name = "location") String locationCode,
                                                  @RequestParam(name = "deposit") String depositCode) {

        return ResponseEntity.ok(stockService.getByLocationDeposit(locationCode, depositCode));
    }

    @ApiOperation(value = "Busqueda de productos", response = PaginationWrapperDTO.class)
    @GetMapping("/items/search")
    public ResponseEntity<?> search(@RequestParam(name = "deposit") String deposit,
                                    @RequestParam(name = "itemId") String itemId,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "${spring.application.pagination.max-result}") int size) {
        return ResponseEntity.ok(stockService.search(deposit, itemId, page, size));
    }
}