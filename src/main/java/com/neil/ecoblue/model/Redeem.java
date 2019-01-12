package com.neil.ecoblue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Redeem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int redeemId;
    private String redeemName;
    private double redeemValue;

    public Redeem(){}

    public int getRedeemId() {
        return redeemId;
    }

    public void setRedeemId(int redeemId) {
        this.redeemId = redeemId;
    }

    public String getRedeemName() {
        return redeemName;
    }

    public void setRedeemName(String redeemName) {
        this.redeemName = redeemName;
    }

    public double getRedeemValue() {
        return redeemValue;
    }

    public void setRedeemValue(double redeemValue) {
        this.redeemValue = redeemValue;
    }
}
