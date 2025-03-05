package com.example.service;

import com.example.model.Warehouse;
import com.example.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    @Autowired
    public WarehouseService(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Optional<Warehouse> findById(String id) {
        return warehouseRepository.findById(id);
    }

    public Optional<Warehouse> findByName(String name) {
        return warehouseRepository.findByName(name);
    }

    public List<Warehouse> findAll() { return warehouseRepository.findAll();}

    public boolean warehouseNameIsTaken(String name) {
        Iterable<Warehouse> warehouses = findAll();
        for (Warehouse warehouse : warehouses) {
            if (warehouse.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteById(String id) {
        warehouseRepository.deleteById(id);
    }
}