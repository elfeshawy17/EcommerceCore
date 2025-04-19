package models.products;

import models.cart.Cart;
import utils.LoggerUtil;

import java.util.logging.Logger;

public class Product {

    private static final Logger logger = LoggerUtil.getLogger(Product.class);

    private String name;
    private double price;
    private int availableQuantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public void reduceQuantity (int quantity) {
        boolean wrongQuantity = checkQuantity(quantity);
        if (wrongQuantity) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        boolean available = checkAvailability(quantity);
        if (!available) {
            throw new IllegalArgumentException("This product is out of stock");
        }

        this.availableQuantity -= quantity;
    }

    private boolean checkQuantity(int quantity) {
        return quantity <= 0;
    }

    private boolean checkAvailability(int quantity) {
        return getAvailableQuantity() >= quantity;
    }
}
