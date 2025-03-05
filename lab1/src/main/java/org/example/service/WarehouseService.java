package org.example.service;

import org.example.model.Product;
import org.example.model.ProductDto;
import org.example.model.Warehouse;

import java.util.*;
import java.util.stream.Collectors;

public class WarehouseService {

    public List<Warehouse> initializeData() {
        Warehouse warehouse1 = new Warehouse("W1", "Central Warehouse", "Gdansk");
        Warehouse warehouse2 = new Warehouse("W2", "Secondary Warehouse", "Sopot");

        Product p1 = new Product("P1", "Laptop", 1200, 10);
        Product p2 = new Product("P2", "Phone", 800, 50);
        Product p3 = new Product("P3", "Monitor", 300, 20);
        Product p4 = new Product("P4", "Adapter usb c", 100, 10);

        warehouse1.addProduct(p1);
        warehouse1.addProduct(p2);
        warehouse2.addProduct(p3);
        warehouse2.addProduct(p4);

        return Arrays.asList(warehouse1, warehouse2);
    }

    public void printAll(List<Warehouse> warehouses) {
        warehouses.forEach(w -> {
            System.out.println(w);
            w.getProducts().forEach(System.out::println);
        });
    }

    public void createAndPrintSet(List<Warehouse> warehouses) {
        Set<Product> productSet = warehouses.stream()
                .flatMap(w -> w.getProducts().stream())
                .collect(Collectors.toSet());
        productSet.forEach(System.out::println);
    }

    public void filterSortAndPrint(List<Warehouse> warehouses) {
        warehouses.stream()
                .flatMap(w -> w.getProducts().stream())
                .filter(p -> p.getPrice() < 500)
                .sorted(Comparator.comparing(Product::getName))
                .forEach(System.out::println);
    }

    public void transformToDtoAndPrint(List<Warehouse> warehouses) {
        List<ProductDto> dtoList = warehouses.stream()
                .flatMap(w -> w.getProducts().stream()
                        .map(p -> new ProductDto(p.getId(), p.getName(), p.getPrice(), w.getName())))
                .sorted(Comparator.comparing(ProductDto::getName))
                .collect(Collectors.toList());

        dtoList.forEach(System.out::println);
    }

}
