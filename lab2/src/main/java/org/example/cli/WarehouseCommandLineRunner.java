package org.example.cli;

import org.example.model.Product;
import org.example.model.Warehouse;
import org.example.service.ProductService;
import org.example.service.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

@Component
public class WarehouseCommandLineRunner implements CommandLineRunner {
    private final ProductService productService;
    private final WarehouseService warehouseService;
    private final Scanner scanner;

    @Autowired
    public WarehouseCommandLineRunner(ProductService productService, WarehouseService warehouseService) {
        this.productService = productService;
        this.warehouseService = warehouseService;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run(String... args) throws Exception {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> listAllWarehouses();
                case 2 -> listAllProducts();
                case 3 -> listProductsInWarehouse();
                case 4 -> listProductsInPriceRange();
                case 5 -> addNewProduct();
                case 6 -> deleteProduct();
                case 7 -> {
                    System.out.println("Exiting application...");
                    exit(0);
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Warehouse Management System ---");
        System.out.println("1. List All Warehouses");
        System.out.println("2. List All Products");
        System.out.println("3. List Products in specific Warehouse");
        System.out.println("4. List Products in Price Range");
        System.out.println("5. Add New Product");
        System.out.println("6. Delete Product");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private void listAllWarehouses() {
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        for (Warehouse warehouse : warehouses) {
            System.out.println(warehouse);
        }
    }

    private void listAllProducts() {
        List<Product> products = productService.findAllProducts();
        for (Product product : products) {
            System.out.println(product);
        }
    }

    private void addNewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        // List available warehouses
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        System.out.println("Select a Warehouse:");
        for (int i = 0; i < warehouses.size(); i++) {
            System.out.println((i + 1) + ". " + warehouses.get(i).getName());
        }

        // Get warehouse selection
        System.out.print("Enter the number to select: ");
        int warehouseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate warehouse selection
        if (warehouseChoice < 1 || warehouseChoice > warehouses.size()) {
            System.out.println("Invalid warehouse selection.");
            return;
        }

        Warehouse selectedWarehouse = warehouses.get(warehouseChoice - 1);

        Product newProduct = new Product(name, price, quantity);
        newProduct.setWarehouse(selectedWarehouse);
        productService.saveProduct(newProduct);
        System.out.println("Product added successfully.");
    }

    private void deleteProduct() {
        List<Product> products = productService.findAllProducts();

        System.out.println("Select a Product to Delete:");
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            System.out.println((i + 1) + ". " + product.getName() +
                    " (ID: " + product.getId() +
                    ", Warehouse: " + product.getWarehouse().getName() + ")");
        }

        System.out.print("Enter the number to delete: ");
        int productChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (productChoice < 1 || productChoice > products.size()) {
            System.out.println("Invalid product selection.");
            return;
        }

        Product productToDelete = products.get(productChoice - 1);
        productService.deleteProduct(productToDelete.getId());
        System.out.println("Product '" + productToDelete.getName() + "' deleted successfully.");
    }

    private void listProductsInPriceRange() {
        System.out.print("Enter low price bound: ");
        double lowBound = scanner.nextDouble();

        System.out.print("Enter high price bound: ");
        double highBound = scanner.nextDouble();

        List<Product> products = productService.findProductsInPriceRange(lowBound, highBound);

        if (products.isEmpty()) {
            System.out.println("No products found in this price range.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
    
    private void listProductsInWarehouse() {
        // List available warehouses
        List<Warehouse> warehouses = warehouseService.findAllWarehouses();
        System.out.println("Select a Warehouse:");
        for (int i = 0; i < warehouses.size(); i++) {
            System.out.println((i + 1) + ". " + warehouses.get(i).getName());
        }

        // Get warehouse selection
        System.out.print("Enter the number to select: ");
        int warehouseChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Validate warehouse selection
        if (warehouseChoice < 1 || warehouseChoice > warehouses.size()) {
            System.out.println("Invalid warehouse selection.");
            return;
        }

        Warehouse selectedWarehouse = warehouses.get(warehouseChoice - 1);
        
        List<Product> products = productService.findProductsByWarehouse(selectedWarehouse.getId());
        for (Product product : products) {
            System.out.println(product);
        }
    }
}