package com.neil.ecoblue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;
    private String itemName;
    private double itemPoint;
    private double phpValue;

    public Item(){}

    public Item(String itemName, double itemPoint, double phpValue) {
        this.itemName = itemName;
        this.itemPoint = itemPoint;
        this.phpValue = phpValue;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPoint() {
        return itemPoint;
    }

    public void setItemPoint(double itemPoint) {
        this.itemPoint = itemPoint;
    }

    public double getPhpValue() {
        return phpValue;
    }

    public void setPhpValue(double phpValue) {
        this.phpValue = phpValue;
    }
}
