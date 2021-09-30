package com.mercadolibre.stock.service.impl;

import com.mercadolibre.stock.model.entity.Deposit;
import com.mercadolibre.stock.repository.DepositRepository;
import com.mercadolibre.stock.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("depositService")
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositRepository depositRepository;

    @Override
    public Deposit get(String code) {
        return depositRepository.findByCode(code)
                .orElse(null);
    }
}
