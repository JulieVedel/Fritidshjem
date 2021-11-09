package com.company;

import java.io.Serializable;

public class Groceries {
    private int amount;
    private String product;
    private double price;

    public Groceries(int amount, String product, double price) {
        this.amount = amount;
        this.product = product;
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
