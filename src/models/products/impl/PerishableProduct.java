package models.products.impl;

import models.products.Expirable;
import models.products.Shippable;
import models.products.Product;

import java.time.LocalDate;

public class PerishableProduct extends Product implements Expirable, Shippable {

    private double weight;
    private LocalDate expiryDate;

    public PerishableProduct(String name, double price, int quantity, double weight, LocalDate expiryDate) {
        super(name, price, quantity);
        this.weight = weight;
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public void isExpired() {
        if ( expiryDate.isBefore(LocalDate.now()) ) {
            throw new IllegalArgumentException("This product is expired.");
        }
    }

}
