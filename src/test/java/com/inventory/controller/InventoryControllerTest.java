package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.model.Product;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public class InventoryControllerTest {

    // Mocking or stubbing implementation - IT23360396
    @Mock
    private Inventory inventory;

    // Inject the mocked inventory into the InventoryController
    @InjectMocks
    private InventoryController inventoryController;

    // Capture system output to verify console messages
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    // Fixtures or setup/teardown implementation - it23243644
    @BeforeMethod
    public void setUp() {
        // Initialize Mockito annotations
        MockitoAnnotations.openMocks(this);
        // Redirect System.out to our captor
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void testAddProduct() {
        // Arrange: Prepare test data
        String id = "P001";
        String name = "Laptop";
        double price = 1500.0;
        int quantity = 10;

        // Act: Call the method under test
        inventoryController.addProduct(id, name, price, quantity);

        // Assert: Verify that inventory.addProduct was called once with any Product
        // object
        ArgumentCaptor<Product> productCaptor = ArgumentCaptor.forClass(Product.class);
        verify(inventory, times(1)).addProduct(productCaptor.capture());

        // Verify the properties of the Product object passed to the mock
        Product addedProduct = productCaptor.getValue();
        // Assertions implementation - IT23241732
        Assert.assertEquals(addedProduct.getId(), id);
        Assert.assertEquals(addedProduct.getName(), name);
        Assert.assertEquals(addedProduct.getPrice(), price);
        Assert.assertEquals(addedProduct.getQuantity(), quantity);

        // Check if the success message was printed to the console
        Assert.assertTrue(outputStreamCaptor.toString().contains("Product added successfully: Laptop"));
    }

    @Test
    public void testRemoveProduct() {
        // Arrange
        String id = "P001";

        // Act
        inventoryController.removeProduct(id);

        // Assert: Verify the mock was called with the correct ID
        verify(inventory, times(1)).removeProduct(id);
        Assert.assertTrue(outputStreamCaptor.toString().contains("Product removed successfully: P001"));
    }

    @Test
    public void testListProductsWhenEmpty() {
        // Arrange: Stub the mock to return an empty list
        when(inventory.getAllProducts()).thenReturn(Collections.emptyList());

        // Act
        inventoryController.listProducts();

        // Assert: Verify interaction and console output
        verify(inventory, times(1)).getAllProducts();
        Assert.assertTrue(outputStreamCaptor.toString().contains("Inventory is empty."));
    }

    @Test
    public void testListProductsWithData() {
        // Arrange: Stub the mock to return a list with one product
        Product product = new Product("P001", "Laptop", 1500.0, 10);
        List<Product> productList = List.of(product);
        when(inventory.getAllProducts()).thenReturn(productList);

        // Act
        inventoryController.listProducts();

        // Assert: Verify interaction and console output
        verify(inventory, times(1)).getAllProducts();
        Assert.assertTrue(outputStreamCaptor.toString().contains("Current Inventory:"));
        Assert.assertTrue(outputStreamCaptor.toString().contains("Laptop"));
    }
}
