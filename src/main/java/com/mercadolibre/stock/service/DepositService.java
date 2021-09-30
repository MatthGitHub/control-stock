package com.mercadolibre.stock.service;

import com.mercadolibre.stock.model.entity.Deposit;

public interface DepositService {
    Deposit get(String code);
}
