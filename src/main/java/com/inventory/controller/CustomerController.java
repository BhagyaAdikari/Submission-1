package com.inventory.controller;

import com.inventory.model.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;

    public CustomerController() {
        this.customers = new ArrayList<>();
    }

    public void addCustomer(String id, String name, String email, String phone) {
        Customer customer = new Customer(id, name, email, phone);
        customers.add(customer);
        System.out.println("Customer added successfully: " + name);
    }

    public void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers registered.");
        } else {
            System.out.println("Registered Customers:");
            customers.forEach(System.out::println);
        }
    }
}
