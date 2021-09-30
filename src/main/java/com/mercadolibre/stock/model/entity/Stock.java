package com.mercadolibre.stock.model.entity;

import javax.persistence.*;

@Entity
@Table(name="stocks", indexes = @Index(columnList = "item_id"))
public class Stock extends PersistentObject {
    @Column(name = "item_id", nullable = false, columnDefinition = "VARCHAR(100)")
    private String itemId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id", nullable = false)
    private Location location;

    @Column(name = "quantity", nullable = false, columnDefinition = "INTEGER")
    private int quantity;

    public Stock(){}

    public Stock(String itemId){
        this.itemId = itemId;
    }

    public Stock(String itemId, Location location){
        this.itemId = itemId;
        this.location = location;
    }

    public Stock(String itemId, int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public Stock(String itemId, Location location, int quantity){
        this.itemId = itemId;
        this.location = location;
        this.quantity = quantity;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
}
