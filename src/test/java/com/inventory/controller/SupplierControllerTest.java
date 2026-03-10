package com.inventory.controller;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SupplierControllerTest {
    private SupplierController supplierController;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeMethod
    public void setUp() {
        // GIVEN: A new SupplierController is initialized
        supplierController = new SupplierController();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testAddSupplier() {
        // WHEN: A new supplier is added to the system
        supplierController.addSupplier("S001", "Tech Supplies Inc", "tech@supplies.com", "Electronics");

        // THEN: The system should confirm the supplier was added successfully
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Supplier added successfully: Tech Supplies Inc"),
                "Expected success message for adding supplier 'Tech Supplies Inc'");
    }

    @Test
    public void testRemoveSupplier() {
        // GIVEN: A supplier is already added
        supplierController.addSupplier("S001", "Tech Supplies Inc", "tech@supplies.com", "Electronics");
        outContent.reset(); // Clear output from add operation

        // WHEN: The supplier is removed
        supplierController.removeSupplier("S001");

        // THEN: The system should confirm the supplier was removed successfully
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Supplier removed successfully: S001"),
                "Expected success message for removing supplier 'S001'");
    }

    @Test
    public void testListSuppliers() {
        // GIVEN: Multiple suppliers are added
        supplierController.addSupplier("S001", "Tech Supplies Inc", "tech@supplies.com", "Electronics");
        supplierController.addSupplier("S002", "Office Depot", "contact@officedepot.com", "Stationery");
        outContent.reset(); // Clear output from add operations

        // WHEN: The list of suppliers is requested
        supplierController.listSuppliers();

        // THEN: The system should display the suppliers details
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Tech Supplies Inc"), "Output should contain the supplier name 'Tech Supplies Inc'");
        Assert.assertTrue(output.contains("Office Depot"), "Output should contain the supplier name 'Office Depot'");
        Assert.assertTrue(output.contains("Stationery"), "Output should contain the supplier category 'Stationery'");
    }

    @Test
    public void testEmptySupplierList() {
        // GIVEN: No suppliers are added (empty system)

        // WHEN: The list of suppliers is requested
        supplierController.listSuppliers();

        // THEN: The system should notify that no suppliers are registered
        String output = outContent.toString();
        Assert.assertTrue(output.contains("No suppliers registered."),
                "Expected 'No suppliers registered.' message for empty list");
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalOut);
    }
}
