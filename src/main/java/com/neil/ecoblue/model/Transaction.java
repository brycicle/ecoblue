package com.neil.ecoblue.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private int transactionType;
    private Date transactionDate;
    private int qty;
    private double transactionValue;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
    //Naka optional to kasi pag yung transaction is redeem walang item na kasama
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Item item;
    //same sa item
    @OneToOne(fetch = FetchType.LAZY, optional = true)
    private Redeem redeem;

    public Transaction(){}
    //constructor sa pag convert ng item
    public Transaction(Account account, Item item, int qty, double transactionValue) {
        this.account = account;
        this.qty = qty;
        this.transactionDate = new Date();
        this.transactionValue = transactionValue;
        this.item = item;
        this.transactionType = 1;
    }
    //constructor sa pag redeem ng item
    public Transaction(Account account, Redeem redeem, int qty, double transactionValue) {
        this.account = account;
        this.qty = qty;
        this.transactionDate = new Date();
        this.transactionValue = transactionValue;
        this.redeem = redeem;
        this.transactionType = 2;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(double transactionValue) {
        this.transactionValue = transactionValue;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Redeem getRedeem() {
        return redeem;
    }

    public void setRedeem(Redeem redeem) {
        this.redeem = redeem;
    }
}
