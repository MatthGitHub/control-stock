package com.mercadolibre.stock.repository;

import com.mercadolibre.stock.model.dto.ItemDTO;
import com.mercadolibre.stock.model.entity.Location;
import com.mercadolibre.stock.model.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {
    long countByLocation(Location location);
    long countByLocationCode(String locationCode);
    Optional<Stock> findByItemIdAndLocationCodeAndLocationDepositCode(String itemId, String locationCode, String depositCode);
    Optional<Stock> findByItemIdAndLocation(String itemId, Location location);
    Set<Stock> findByLocationCodeAndLocationDepositCode(String locationCode, String depositCode);

    @Query("select " +
            " new com.mercadolibre.stock.model.dto.ItemDTO(" +
            " s.location.code, s.itemId, s.quantity " +
            " ) " +
            " from Stock s " +
            " where s.location.deposit.code = :depositCode" +
            " and s.itemId = :itemId")
    Page<ItemDTO> findByFilters(@Param("depositCode") String despositCode,
                                @Param("itemId") String itemId,
                                Pageable pageable);
}