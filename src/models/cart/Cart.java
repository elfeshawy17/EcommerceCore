package models.cart;

import models.products.Expirable;
import models.customer.Customer;
import models.products.Product;
import models.products.Shippable;

import java.util.ArrayList;

public class Cart {

    private ArrayList<CartItem> cart = new ArrayList<>();
    private ArrayList<CartItem> shippedItems = new ArrayList<>();

    private Customer customer;

    public Cart(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void add (Product product, int quantity) {
        boolean exist = checkProductExist(product);
         if (exist) {
             for (CartItem cartItem : cart) {
                 if (cartItem.getProduct().equals(product)){
                     product.reduceQuantity(quantity);
                     quantity += cartItem.getQuantity();
                     cartItem.setQuantity(quantity);
                 }
             }
         } else {
             checkExpiration(product);
             product.reduceQuantity(quantity);
             CartItem cartItem = new CartItem(product, quantity);
             cart.add(cartItem);
         }
    }

    private boolean checkProductExist(Product product) {
        return cart.stream().anyMatch(cartItem -> cartItem.getProduct().equals(product));
    }

    private void checkExpiration(Product product) {
        if (product instanceof Expirable) {
            ((Expirable) product).isExpired();
        }
    }

    public void isEmpty() {
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart is empty.");
        }
    }

    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public ArrayList<CartItem> getShippedItems() {
        for (CartItem cartItem : getCart()){
            if (cartItem.getProduct() instanceof Shippable) {
                shippedItems.add(cartItem);
            }
        }
        return shippedItems;
    }
}
