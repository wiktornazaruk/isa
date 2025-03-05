package org.example.model;

public class ProductDto {
    private final String id;
    private final String name;
    private final double price;
    private final String warehouseName;

    public ProductDto(String id, String name, double price, String warehouseName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.warehouseName = warehouseName;
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

    public String getWarehouseName() {
        return warehouseName;
    }
    
    @Override
    public String toString() {
        return "ProductDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", warehouseName='" + warehouseName + '\'' +
                '}';
    }
}