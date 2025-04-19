package models.products.impl;

import models.products.Expirable;
import models.products.Product;

import java.time.LocalDate;

public class PerishableDigitalProduct extends Product implements Expirable {

    private LocalDate expiryDate;

    public PerishableDigitalProduct(String name, double price, int quantity, LocalDate expiryDate) {
        super(name, price, quantity);
        this.expiryDate = expiryDate;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public void isExpired() {
        if ( expiryDate.isBefore(LocalDate.now()) ) {
            throw new IllegalArgumentException("This product is expired.");
        }
    }
}
