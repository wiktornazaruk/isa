package com.example.repository;

import com.example.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, String> {
    @Query("SELECT w FROM Warehouse w WHERE w.name = :name")
    Optional<Warehouse> findByName(String name);
}