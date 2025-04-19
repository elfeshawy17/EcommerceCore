import models.cart.Cart;
import models.customer.Customer;
import models.products.impl.DigitalProduct;
import models.products.impl.PerishableProduct;
import services.OrderService;
import services.ShippingService;

import java.time.LocalDate;

public class Main {

    public static void main (String [] args) {

        OrderService order = new OrderService();

        Customer customer1 = new Customer("Mohamed", "Mohamed@gmail.com", "123", "010", 10000);
        Customer customer2 = new Customer("Ali", "Ali@gmail.com", "123", "010", 5000);

        LocalDate expireDate = LocalDate.of(2026, 10, 5);

        PerishableProduct cheese = new PerishableProduct("Cheese", 100, 5, 400, expireDate);
        PerishableProduct biscuits = new PerishableProduct("biscuits", 150, 50, 700, expireDate);
        DigitalProduct cardScratch = new DigitalProduct("cardScratch", 20, 50);

        Cart cart1 = customer1.getCart();
        Cart cart2 = customer2.getCart();

        cart1.add(cheese, 2);
        cart1.add(biscuits, 1);
        cart1.add(cardScratch, 10);
        order.checkOut(customer1, cart1, "Cairo");

        cart2.add(cheese, 1);
        cart2.add(cardScratch, 5);
        cart2.add(cheese, 2);
        cart2.add(biscuits, 10);
        order.checkOut(customer2, cart2, "Alex");

    }

}
