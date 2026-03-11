package com.inventory.controller;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CustomerControllerTest {
    private CustomerController customerController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    // Fixtures or setup/teardown implementation - IT23243644
    @BeforeMethod
    public void setUp() {
        // GIVEN: A new CustomerController is initialized
        customerController = new CustomerController();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddCustomer() {
        // WHEN: A new customer is added to the system
        customerController.addCustomer("C001", "John Doe", "john@example.com", "0771234567");

        // THEN: The system should confirm the customer was added successfully
        String output = outContent.toString();
        // Assertions implementation - IT23171992
        Assert.assertTrue(output.contains("Customer added successfully: John Doe"),
                "Expected success message for adding customer 'John Doe'");
    }

    @Test
    public void testListCustomers() {
        // GIVEN: A customer is already added
        customerController.addCustomer("C001", "John Doe", "john@example.com", "0771234567");
        outContent.reset(); // Clear output from add operation

        // WHEN: The list of customers is requested
        customerController.listCustomers();

        // THEN: The system should display the customer details
        String output = outContent.toString();
        Assert.assertTrue(output.contains("John Doe"), "Output should contain the customer name 'John Doe'");
        Assert.assertTrue(output.contains("john@example.com"), "Output should contain the customer email");
    }

    @Test
    public void testEmptyCustomerList() {
        // GIVEN: No customers are added (empty system)

        // WHEN: The list of customers is requested
        customerController.listCustomers();

        // THEN: The system should notify that no customers are registered
        String output = outContent.toString();
        Assert.assertTrue(output.contains("No customers registered."),
                "Expected 'No customers registered.' message for empty list");
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
        System.setOut(originalOut);
    }
}
