package org.example.model;

import java.io.Serializable;

public class Product implements Comparable<Product>, Serializable {
    private final String id;
    private final String name;
    private final double price;
    private final int quantity;
    private Warehouse warehouse;

    public Product(String id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    @Override
    public int compareTo(Product other) {
        int result = this.name.compareTo(other.name);
        if(result==0){
            if(this.hashCode()!=other.hashCode()){
                return 0;
            }
            else {
                if(this.hashCode()>other.hashCode()){
                    return 1;
                }
                else {
                    return -1;
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", warehouse=" + (warehouse != null ? warehouse.getName() : null) +
                '}';
    }
}