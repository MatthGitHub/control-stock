package com.mercadolibre.stock.model.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name="deposits",
        indexes = @Index(columnList = "code"),
        uniqueConstraints = @UniqueConstraint(columnNames = "code")
)
public class Deposit extends PersistentObject {
    @Column(name = "code", nullable = false, columnDefinition = "VARCHAR(10)")
    private String code;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "deposit", cascade = CascadeType.ALL)
    private Set<Location> locations = new HashSet<>();

    public Deposit(){}

    public Deposit(String code){
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Location> getLocations() {
        return locations;
    }

    public void setLocations(Set<Location> locations) {
        this.locations = locations;
    }
}
