package com.mercadolibre.stock.repository;

import com.mercadolibre.stock.model.entity.Location;
import com.mercadolibre.stock.model.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    long countByLocation(Location location);
    long countByLocationCode(String locationCode);
    Optional<Stock> findByItemIdAndLocationCodeAndLocationDepositCode(String itemId, String locationCode, String depositCode);
    Optional<Stock> findByItemIdAndLocation(String itemId, Location location);
}