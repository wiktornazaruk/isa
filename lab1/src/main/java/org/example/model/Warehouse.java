package org.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Serializable {
    private final String id;
    private final String name;
    private final String location;
    private final List<Product> products = new ArrayList<>();

    public Warehouse(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.setWarehouse(this);
    }

    public List<Product> getProducts() {
        return products;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
