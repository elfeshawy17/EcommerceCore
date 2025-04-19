package services;

import models.cart.Cart;
import models.cart.CartItem;
import models.customer.Customer;
import models.products.Product;
import models.products.Shippable;
import services.impl.Order;
import services.impl.Status;

public class OrderService {

    ShippingService shippingService = new ShippingService();

    public void checkOut (Customer customer, Cart cart, String address) {
        cart.isEmpty();
        Order order = new Order(customer, cart.getCart(), cart.getShippedItems(), address);
        customer.reduceBalance(order.getSubTotal());
        order.setStatus(Status.PAID);
        cart.getCart().clear();
        printReceipt(order);
        shippingService.deliver(order);
    }

    private void printReceipt(Order order) {
        System.out.println(" ");
        System.out.println("Order ID: " + order.getId());
        System.out.println("Customer: " + order.getCustomer().getName());
        System.out.println("Current Balance: " + order.getCustomer().getBalance());
        System.out.println(" ");

        System.out.println("** Shipment notice **");

        for (CartItem cartItem : order.getShippedItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            String name = product.getName();
            double weight = ((Shippable) product).getWeight() * quantity;
            System.out.println(quantity + "x " + name + "        " + weight + "g");
        }

        System.out.println("Total package weight " + order.getTotalWeight() + "Kg");
        System.out.println(" ");
        System.out.println("** Checkout receipt ** ");

        for (CartItem cartItem : order.getItems()) {
            Product product = cartItem.getProduct();
            int quantity = cartItem.getQuantity();
            String name = product.getName();
            double price = product.getPrice() * quantity;
            System.out.println(quantity + "x " + name + "        " + price);
        }

        System.out.println("---------------------- ");
        System.out.println("Subtotal         " + order.getSubTotal());
        System.out.println("Shipping         30");

        double totalPrice = order.getSubTotal() + 30;
        System.out.println("Amount           " + totalPrice);
        System.out.println(" ");
    }

}
