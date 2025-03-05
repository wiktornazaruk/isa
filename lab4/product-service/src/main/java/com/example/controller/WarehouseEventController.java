package com.example.controller;

import com.example.event.WarehouseEvent;
import com.example.model.WarehouseInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.service.WarehouseInfoService;
import com.example.service.ProductService;

@RestController
@RequestMapping("/api/warehouse-events")
public class WarehouseEventController {
    private final ProductService productService;
    private final WarehouseInfoService warehouseInfoService;

    public WarehouseEventController(ProductService productService, WarehouseInfoService warehouseInfoService) {
        this.productService = productService;
        this.warehouseInfoService = warehouseInfoService;
    }

    @PostMapping
    public ResponseEntity<Void> handleWarehouseEvent(@RequestBody WarehouseEvent event) {
        switch (event.getEventType()) {
            case "DELETED":
                productService.removeWarehouseProducts(event.getName());
                warehouseInfoService.deleteWarehouse(event.getWarehouseId());
                break;
            case "CREATED":
                warehouseInfoService.createWarehouse(event);
            case "UPDATED":
                warehouseInfoService.updateWarehouse(event);
                break;
        }
        return ResponseEntity.ok().build();
    }
    
    @GetMapping
    public ResponseEntity<Iterable<WarehouseInfo>> getAllWarehouses(){
        Iterable<WarehouseInfo> w = warehouseInfoService.findAll();
        return ResponseEntity.ok(w);
    }
}