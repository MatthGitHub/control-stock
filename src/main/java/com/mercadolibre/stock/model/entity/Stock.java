package com.mercadolibre.stock.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

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

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_date", nullable = false, columnDefinition = "TIMESTAMP")
    private Date creationDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date", columnDefinition = "TIMESTAMP")
    private Date updateDate;


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

    public int consumeQuantity(int quantity){
        return this.quantity -= quantity;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}