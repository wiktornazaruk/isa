package com.example.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product implements Serializable {
    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    @Min(value = 0, message = "Price must be greater than or equal to zero")
    private double price;

    @Column(name = "quantity", nullable = false)
    @Min(value = 0, message = "Quantity must be greater than or equal to zero")
    private int quantity;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "warehouse_name")
    private String warehouseName;

    // Constructors
    public Product() {}

    public Product(String name, double price, int quantity, String category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public Product(String id, String name, double price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    // Add constructor with id and warehouseName
    public Product(String id, String name, double price, int quantity, String category, String warehouseName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.warehouseName = warehouseName;
    }

    // Add constructor with warehouseName
    public Product(String name, double price, int quantity, String category, String warehouseName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.warehouseName = warehouseName;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }


    // Overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}