package com.inventory;

import com.inventory.controller.InventoryController;
import com.inventory.controller.SupplierController;
import com.inventory.controller.CustomerController;
import com.inventory.controller.OrderController;
import com.inventory.model.Inventory;
import com.inventory.router.InventoryRoutes;

public class App {
    public static void main(String[] args) {
        // Initialization
        Inventory inventory = new Inventory();
        InventoryController inventoryController = new InventoryController(inventory);
        SupplierController supplierController = new SupplierController();
        CustomerController customerController = new CustomerController();
        OrderController orderController = new OrderController(inventory);

        InventoryRoutes router = new InventoryRoutes(
                inventoryController, supplierController, customerController, orderController);

        System.out.println("--- Welcome to the MVC Business Management System ---");

        // 1. Setup Inventory & Suppliers
        System.out.println("\n[Action: Initializing Inventory]");
        router.route("add", "P001", "Laptop", 1200.0, 10);
        router.route("add", "P002", "Mouse", 25.0, 100);

        System.out.println("\n[Action: Initializing Suppliers]");
        router.route("add_supplier", "S001", "TechLogistics", "+94 11 222 3344", "Electronics");

        // 2. Manage Customers
        System.out.println("\n[Action: Registering Customers]");
        router.route("add_customer", "C001", "John Doe", "john@example.com", "0771234567");
        router.route("add_customer", "C002", "Jane Smith", "jane@example.com", "0779876543");

        router.route("list_customers");

        // 3. Process Orders
        System.out.println("\n[Action: Placing Orders]");
        router.route("place_order", "ORD001", "C001", "P001", 2); // John buys 2 Laptops
        router.route("place_order", "ORD002", "C002", "P002", 5); // Jane buys 5 Mice

        // 4. Final Review
        System.out.println("\n[Action: Final Status Review]");
        router.route("list"); // Check remaining stock
        router.route("list_orders"); // View order history

        System.out.println("\n--- Session Finished ---");
    }
}
