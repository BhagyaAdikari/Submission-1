package com.inventory.controller;

import com.inventory.model.Inventory;
import com.inventory.model.Product;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Behaviour-Driven Development (BDD) syntax implementation - it23241732
 * This class also demonstrates Assertions - IT23241732
 */
public class BDDInventoryTest {

    private Inventory inventory;
    private InventoryController inventoryController;

    @BeforeClass
    public void setup() {
        // Fixtures or setup/teardown implementation - it23243644
        inventory = new Inventory();
        inventoryController = new InventoryController(inventory);
    }

    @Test
    public void givenAnEmptyInventory_whenAProductIsAdded_thenTheTotalCountShouldIncrease() {
        // BDD syntax - it23241732

        // Given
        int initialCount = inventory.getAllProducts().size();

        // When
        inventoryController.addProduct("P100", "Smartphone", 800.0, 5);

        // Then
        int finalCount = inventory.getAllProducts().size();

        // Assertions - IT23241732
        Assert.assertEquals(finalCount, initialCount + 1, "Inventory count should increase by 1");

        Product addedProduct = inventory.getProductById("P100").orElse(null);
        Assert.assertNotNull(addedProduct, "Added product should exist in inventory");
        Assert.assertEquals(addedProduct.getName(), "Smartphone");
    }

    @Test
    public void givenInventoryWithProduct_whenProductIsRemoved_thenItShouldNoLongerExist() {
        // BDD syntax - it23241732

        // Given
        inventoryController.addProduct("P200", "Tablet", 400.0, 10);
        Assert.assertTrue(inventory.getProductById("P200").isPresent(), "Product should be present before removal");

        // When
        inventoryController.removeProduct("P200");

        // Then
        boolean exists = inventory.getProductById("P200").isPresent();

        // Assertions - IT23241732
        Assert.assertFalse(exists, "Product should not be present after removal");
    }
}
