package com.inventory.model;

public class Supplier {
    private String id;
    private String name;
    private String contact;
    private String productCategory;

    public Supplier(String id, String name, String contact, String productCategory) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.productCategory = productCategory;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    @Override
    public String toString() {
        return "Supplier{id='" + id + "', name='" + name + "', contact='" + contact + "', category='" + productCategory
                + "'}";
    }
}
