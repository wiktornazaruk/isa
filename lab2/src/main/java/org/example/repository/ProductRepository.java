package org.example.repository;

import org.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    @Query("SELECT p FROM Product p JOIN FETCH p.warehouse")
    List<Product> findAllWithWarehouse();

    @Query("SELECT p FROM Product p JOIN FETCH p.warehouse WHERE p.warehouse.id = :warehouseId")
    List<Product> findByWarehouse_Id(String warehouseId);

    @Query("SELECT p FROM Product p JOIN FETCH p.warehouse WHERE p.price BETWEEN :lowBound AND :highBound")
    List<Product> findProductsByPriceRange(double lowBound, double highBound);
}