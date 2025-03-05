package com.example.service;

import com.example.event.WarehouseEvent;
import com.example.model.WarehouseInfo;
import com.example.repository.WarehouseInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class WarehouseInfoService {
    private final WarehouseInfoRepository warehouseInfoRepository;

    public WarehouseInfoService(WarehouseInfoRepository warehouseInfoRepository) {
        this.warehouseInfoRepository = warehouseInfoRepository;
    }

    public void createWarehouse(WarehouseEvent event) {
        warehouseInfoRepository.save(new WarehouseInfo(event.getWarehouseId(), event.getName(), event.getLocation()));
    }

    public void updateWarehouse(WarehouseEvent event) {
        warehouseInfoRepository.findById(event.getWarehouseId())
                .ifPresent(warehouse -> {
                    warehouse.setName(event.getName());
                    warehouse.setLocation(event.getLocation());
                    warehouseInfoRepository.save(warehouse);
                });
    }

    public boolean warehouseExists(String warehouseName) {
        String id = findByName(warehouseName).getId();
        if (id == null) {
            return false;
        }
        return warehouseInfoRepository.existsById(id);

    }

    public void deleteWarehouse(String warehouseId) {
        warehouseInfoRepository.deleteById(warehouseId);
    }

    public Iterable<WarehouseInfo> findAll() {
        return warehouseInfoRepository.findAll();
    }

    public WarehouseInfo findById(String id) {
        return warehouseInfoRepository.findById(id).orElse(null);
    }

    public WarehouseInfo findByName(String name) {
        return warehouseInfoRepository.findByName(name).orElse(null);
    }
}