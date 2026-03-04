package com.inventory.controller;

import com.inventory.model.Supplier;
import java.util.ArrayList;
import java.util.List;

public class SupplierController {
    private List<Supplier> suppliers;

    public SupplierController() {
        this.suppliers = new ArrayList<>();
    }

    public void addSupplier(String id, String name, String contact, String category) {
        Supplier supplier = new Supplier(id, name, contact, category);
        suppliers.add(supplier);
        System.out.println("Supplier added successfully: " + name);
    }

    public void removeSupplier(String id) {
        suppliers.removeIf(s -> s.getId().equals(id));
        System.out.println("Supplier removed successfully: " + id);
    }

    public void listSuppliers() {
        if (suppliers.isEmpty()) {
            System.out.println("No suppliers registered.");
        } else {
            System.out.println("Registered Suppliers:");
            suppliers.forEach(System.out::println);
        }
    }
}
