package com.example.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "warehouse_info")
public class WarehouseInfo {
    @Id
    private String id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "location")
    private String location;

    // Constructors, getters, setters
    public WarehouseInfo() {
    }

    public WarehouseInfo(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}