package com.mercadolibre.stock.repository;

import com.mercadolibre.stock.model.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, UUID> {
    Optional<Deposit> findByCode(String code);
}
