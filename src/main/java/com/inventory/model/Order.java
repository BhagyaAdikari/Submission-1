package com.inventory.model;

import java.util.Date;

public class Order {
    private String id;
    private String customerId;
    private String productId;
    private int quantity;
    private double totalAmount;
    private Date orderDate;

    public Order(String id, String customerId, String productId, int quantity, double totalAmount) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.totalAmount = totalAmount;
        this.orderDate = new Date();
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{id='" + id + "', customerId='" + customerId + "', productId='" + productId +
                "', qty=" + quantity + ", total=" + totalAmount + ", date=" + orderDate + "}";
    }
}
