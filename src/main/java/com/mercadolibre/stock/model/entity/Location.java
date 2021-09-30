package com.mercadolibre.stock.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="locations",
        indexes = @Index(columnList = "code"),
        uniqueConstraints = {@UniqueConstraint(columnNames = {"code", "deposit_id"})}
)
public class Location extends PersistentObject {
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(11)")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "deposit_id", referencedColumnName = "id", nullable = false)
    private Deposit deposit;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "location", cascade = CascadeType.ALL)
    private Set<Stock> stocks = new HashSet<>();

    public Location(){}

    public Location(String code, Deposit deposit){
        this.code = code;
        this.deposit = deposit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Deposit getDeposit() {
        return deposit;
    }

    public void setDeposit(Deposit deposit) {
        this.deposit = deposit;
    }

    public Set<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(Set<Stock> stocks) {
        this.stocks = stocks;
    }
}
