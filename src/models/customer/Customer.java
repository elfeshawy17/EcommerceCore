package models.customer;

import models.cart.Cart;

public class Customer {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private double balance;
    private Cart cart;

    public Customer(String name, String email, String password, String phoneNumber, double balance) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.cart = new Cart(this);
    }

    public Cart getCart() {
        return cart;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void reduceBalance(double subTotal){
        if (getBalance() < subTotal){
            throw new IllegalArgumentException("Your balance is insufficient.");
        }
        balance -= subTotal + 30;
    }
}
