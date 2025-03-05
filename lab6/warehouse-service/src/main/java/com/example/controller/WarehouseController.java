package com.example.controller;

import com.example.event.WarehouseEvent;
import com.example.event.WarehouseEventPublisher;
import com.example.model.Warehouse;
import com.example.dto.*;
import com.example.service.WarehouseService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final WarehouseEventPublisher eventPublisher;

    public WarehouseController(WarehouseService warehouseService, WarehouseEventPublisher eventPublisher) {
        this.warehouseService = warehouseService;
        this.eventPublisher = eventPublisher;
    }

    @PutMapping
    public ResponseEntity<WarehouseReadDTO> createWarehouse(@RequestBody WarehouseCreateOrUpdateDTO wdto) {
        Warehouse w = new Warehouse(
                wdto.getName(),
                wdto.getLocation()
        );
        Warehouse saved = warehouseService.save(w);
        eventPublisher.notifyProductService(new WarehouseEvent("CREATED", saved.getId(), saved.getName(), saved.getLocation()));
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new WarehouseReadDTO(
                        saved.getId(),
                        saved.getName(),
                        saved.getLocation()
                )
        );
    }

    @GetMapping
    public ResponseEntity<List<WarehouseReadCollectionDTO>> getAllWarehouses() {
        List<WarehouseReadCollectionDTO> warehouses = warehouseService.findAll().stream()
                .map(w -> new WarehouseReadCollectionDTO(w.getId(), w.getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(warehouses);
    }
    
    @GetMapping("/{name}")
    public ResponseEntity<WarehouseReadDTO> getWarehouseByName(@PathVariable String name) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        return ResponseEntity.ok(new WarehouseReadDTO(
                w.getId(),
                w.getName(),
                w.getLocation()
        ));
    }

    @PatchMapping("/{name}")
    public ResponseEntity<WarehouseReadDTO> updateWarehouse(@PathVariable String name, @RequestBody WarehouseCreateOrUpdateDTO w) {
        try {
            // Find existing warehouse
            Warehouse existingWarehouse = warehouseService.findByName(name)
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Warehouse not found with name: " + name
                    ));

            // Update name if provided
            if (w.getName() != null) {
                if (!w.getName().equals(name)) {
                    if (warehouseService.warehouseNameIsTaken(w.getName())) {
                        throw new ResponseStatusException(
                                HttpStatus.BAD_REQUEST,
                                "Warehouse name is already taken"
                        );
                    }
                    existingWarehouse.setName(w.getName());
                }
            }

            // Update location if provided
            Optional.ofNullable(w.getLocation())
                    .ifPresent(existingWarehouse::setLocation);

            // Save updated warehouse
            Warehouse updatedWarehouse = warehouseService.save(existingWarehouse);

            // Notify Product service about warehouse update
            eventPublisher.notifyProductService(new WarehouseEvent("UPDATED", updatedWarehouse.getId(), updatedWarehouse.getName(), updatedWarehouse.getLocation()));
            return ResponseEntity.ok(new WarehouseReadDTO(
                    updatedWarehouse.getId(),
                    updatedWarehouse.getName(),
                    updatedWarehouse.getLocation()
            ));

        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error updating warehouse: " + e.getMessage()
            );
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteWarehouse(@PathVariable String name) {
        Warehouse w = warehouseService.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Warehouse not found"));
        // Notify Product service about warehouse deletion
        eventPublisher.notifyProductService(new WarehouseEvent("DELETED", w.getId(), w.getName(), w.getLocation()));
        warehouseService.deleteById(w.getId());
        return ResponseEntity.noContent().build();
    }

}