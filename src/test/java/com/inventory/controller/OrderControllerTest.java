package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.model.Product;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class OrderControllerTest {
    private OrderController orderController;
    private Inventory inventory;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    
    /**
     * Fixture that runs once before all test methods in this class.
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("Starting OrderController Test Class...");
    }

    /**
     * Test Fixture: Initial setup before each test execution.
     * This demonstrates the "Fixtures" feature of TestNG (using @BeforeMethod),
     * where we prepare the necessary environment (Inventory and OrderController)
     * with predefined data before running EACH test case.
     */
    @BeforeMethod
    public void setUp() {
        // GIVEN: A fresh inventory with sample products
        inventory = new Inventory();
        inventory.addProduct(new Product("P001", "Laptop", 1500.0, 10));
        inventory.addProduct(new Product("P002", "Mouse", 25.0, 50));

        // GIVEN: An OrderController initialized with that inventory
        orderController = new OrderController(inventory);

        // Capture standard output for testing console print statements
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPlaceOrderSuccess() {
        // WHEN: A valid order is placed
        orderController.placeOrder("OR001", "C001", "P001", 2);

        // THEN: The system should confirm success
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Order placed successfully for Laptop (Qty: 2)"),
                "Expected success message for placing order");

        // THEN: The product quantity in the inventory should be correctly reduced (10 -
        // 2 = 8)
        Assert.assertEquals(inventory.getProductById("P001").get().getQuantity(), 8,
                "The product quantity should be reduced after an order is successfully placed.");
    }

    @Test
    public void testPlaceOrderInsufficientStock() {
        // WHEN: An order is placed for more quantity than available in the fixture (15
        // > 10)
        orderController.placeOrder("OR002", "C001", "P001", 15);

        // THEN: The system should report insufficient stock
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Insufficient stock for Product ID: P001"),
                "Expected an insufficient stock message.");

        // THEN: The quantity should remain unchanged (10)
        Assert.assertEquals(inventory.getProductById("P001").get().getQuantity(), 10,
                "The product quantity should not change if the order fails.");
    }

    @Test
    public void testPlaceOrderProductNotFound() {
        // WHEN: An order is placed for a product ID not in the fixture
        orderController.placeOrder("OR003", "C001", "P999", 5);

        // THEN: The system should report that the product was not found
        String output = outContent.toString();
        Assert.assertTrue(output.contains("Product not found: P999"),
                "Expected a 'Product not found' message.");
    }

    @Test
    public void testListOrders() {
        // GIVEN: Multiple orders are placed
        orderController.placeOrder("OR001", "C001", "P001", 1);
        orderController.placeOrder("OR002", "C001", "P002", 2);
        outContent.reset(); // Clear previous output

        // WHEN: Listing all orders
        orderController.listOrders();

        // THEN: The output should contain details of all orders placed
        String output = outContent.toString();
        Assert.assertTrue(output.contains("OR001"), "Output should contain Order ID OR001.");
        Assert.assertTrue(output.contains("OR002"), "Output should contain Order ID OR002.");
        Assert.assertTrue(output.contains("P001"), "Output should contain Product ID P001.");
        Assert.assertTrue(output.contains("P002"), "Output should contain Product ID P002.");
    }

    /**
     * Clean up fixture: Reset standard output after each test.
     */
    @AfterMethod
    public void tearDown() {
        System.setOut(originalOut);
    }
    
    /**
     * Fixture that runs once after all test methods.
     */
    @AfterClass
    public void afterClass() {
        System.out.println("Finished OrderController Test Class...");
    }
}
