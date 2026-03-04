package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.model.Order;
import com.inventory.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderController {
    private List<Order> orders;
    private Inventory inventory;

    public OrderController(Inventory inventory) {
        this.orders = new ArrayList<>();
        this.inventory = inventory;
    }

    public void placeOrder(String orderId, String customerId, String productId, int quantity) {
        Optional<Product> productOpt = inventory.getProductById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            if (product.getQuantity() >= quantity) {
                double total = product.getPrice() * quantity;
                Order order = new Order(orderId, customerId, productId, quantity, total);
                orders.add(order);

                // Update inventory quantity
                product.setQuantity(product.getQuantity() - quantity);

                System.out.println("Order placed successfully for " + product.getName() + " (Qty: " + quantity + ")");
            } else {
                System.out.println("Insufficient stock for Product ID: " + productId);
            }
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    public void listOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order History:");
            orders.forEach(System.out::println);
        }
    }
}
