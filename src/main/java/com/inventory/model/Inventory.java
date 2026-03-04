package com.inventory.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Inventory {
    private List<Product> products;

    public Inventory() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    public Optional<Product> getProductById(String id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }
}
