package com.example.event;

public class WarehouseEvent {
    private String eventType;
    private String warehouseId;
    private String name;
    private String location;

    // Constructors
    public WarehouseEvent() {}

    public WarehouseEvent(String eventType, String warehouseId, String name, String location) {
        this.eventType = eventType;
        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
    }

    // Getters and setters
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
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
