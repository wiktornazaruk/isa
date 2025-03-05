package com.example.repository;

import com.example.model.WarehouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseInfoRepository extends JpaRepository<WarehouseInfo, String> {
    @Query("SELECT w FROM WarehouseInfo w WHERE w.name = :name")
    Optional<WarehouseInfo> findByName(@Param("name") String name);

}