package org.example;

import org.example.model.Warehouse;
import org.example.service.WarehouseService;
import org.example.util.SerializationUtil;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        WarehouseService service = new WarehouseService();

        // Initialize data
        List<Warehouse> warehouses = service.initializeData();

        // Print all categories and elements
        System.out.println("All Warehouses and Products:");
        service.printAll(warehouses);

        // Create and print Set
        System.out.println("\nSet of All Products:");
        service.createAndPrintSet(warehouses);

        // Filter, sort, and print
        System.out.println("\nFiltered and Sorted Products:");
        service.filterSortAndPrint(warehouses);

        // Transform to DTO and print
        System.out.println("\nDTO Transformed and Sorted Products:");
        service.transformToDtoAndPrint(warehouses);

        // Serialization
        System.out.println("\nSerialized and Deserialized Warehouses:");
        String filename = "warehouses.dat";
        SerializationUtil.serialize(warehouses, filename);
        List<Warehouse> deserialized = SerializationUtil.deserialize(filename);
        service.printAll(deserialized);
    }
}
