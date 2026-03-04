package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.model.Product;
import java.util.List;

public class InventoryController {
    private Inventory inventory;

    public InventoryController(Inventory inventory) {
        this.inventory = inventory;
    }

    public void addProduct(String id, String name, double price, int quantity) {
        Product product = new Product(id, name, price, quantity);
        inventory.addProduct(product);
        System.out.println("Product added successfully: " + name);
    }

    public void removeProduct(String id) {
        inventory.removeProduct(id);
        System.out.println("Product removed successfully: " + id);
    }

    public void listProducts() {
        List<Product> products = inventory.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current Inventory:");
            products.forEach(System.out::println);
        }
    }
}
