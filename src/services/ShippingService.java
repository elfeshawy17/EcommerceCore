package services;

import services.impl.Order;
import services.impl.Status;

public class ShippingService implements DeliveryService {

    @Override
    public void deliver(Order order) {
        checkStatus(order);
        order.setStatus(Status.SHIPPED);
        System.out.println("Order has been successfully shipped to: " + order.getAddress());
    }

    private void checkStatus(Order order) {
        if (order.getStatus() != Status.PAID) {
            throw new IllegalArgumentException("Order has not been paid, Cannot proceed with shipping.");
        }
    }

}
