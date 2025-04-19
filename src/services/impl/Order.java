package services.impl;

import models.cart.CartItem;
import models.customer.Customer;
import models.products.Shippable;

import java.util.ArrayList;

public class Order {

    private static int counter = 1;

    private int id;
    private Customer customer;
    private Status status;
    private String address;
    private ArrayList<CartItem> items;
    private ArrayList<CartItem> shippedItems;

    public Order(Customer customer, ArrayList<CartItem> items, ArrayList<CartItem> shippedItems, String address) {
        this.id = counter++;
        this.customer = customer;
        this.status = Status.PENDING;
        this.address = address;
        this.items = new ArrayList<>(items);
        this.shippedItems = new ArrayList<>(shippedItems);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ArrayList<CartItem> getShippedItems() {
        return shippedItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public double getSubTotal() {
        double subTotal = 0;
        for (CartItem cartItem : getItems()) {
            subTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return subTotal;
    }

    public double getTotalWeight() {
        double totalWeight = 0;
        for (CartItem cartItem : getShippedItems()){
            totalWeight += ((Shippable) cartItem.getProduct()).getWeight() * cartItem.getQuantity();
        }
        return totalWeight / 1000;
    }
}
