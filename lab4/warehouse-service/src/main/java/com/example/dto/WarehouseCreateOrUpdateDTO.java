package com.example.dto;

public class WarehouseCreateOrUpdateDTO {
    private String id;
    private String name;
    private String location;

    public WarehouseCreateOrUpdateDTO() {}

    public WarehouseCreateOrUpdateDTO(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public WarehouseCreateOrUpdateDTO(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
}