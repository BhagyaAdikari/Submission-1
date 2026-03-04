package com.inventory.router;

import com.inventory.controller.InventoryController;
import com.inventory.controller.SupplierController;
import com.inventory.controller.CustomerController;
import com.inventory.controller.OrderController;

public class InventoryRoutes {
    private InventoryController inventoryController;
    private SupplierController supplierController;
    private CustomerController customerController;
    private OrderController orderController;

    public InventoryRoutes(InventoryController inventoryController, SupplierController supplierController,
            CustomerController customerController, OrderController orderController) {
        this.inventoryController = inventoryController;
        this.supplierController = supplierController;
        this.customerController = customerController;
        this.orderController = orderController;
    }

    /**
     * Simulates routing a request to the controller.
     */
    public void route(String action, Object... params) {
        switch (action.toLowerCase()) {
            // Inventory Actions
            case "add":
                inventoryController.addProduct((String) params[0], (String) params[1], (Double) params[2],
                        (Integer) params[3]);
                break;
            case "remove":
                inventoryController.removeProduct((String) params[0]);
                break;
            case "list":
                inventoryController.listProducts();
                break;

            // Supplier Actions
            case "add_supplier":
                supplierController.addSupplier((String) params[0], (String) params[1], (String) params[2],
                        (String) params[3]);
                break;
            case "list_suppliers":
                supplierController.listSuppliers();
                break;

            // Customer Actions
            case "add_customer":
                customerController.addCustomer((String) params[0], (String) params[1], (String) params[2],
                        (String) params[3]);
                break;
            case "list_customers":
                customerController.listCustomers();
                break;

            // Order Actions
            case "place_order":
                orderController.placeOrder((String) params[0], (String) params[1], (String) params[2],
                        (Integer) params[3]);
                break;
            case "list_orders":
                orderController.listOrders();
                break;

            default:
                System.out.println("Unknown action: " + action);
        }
    }
}
