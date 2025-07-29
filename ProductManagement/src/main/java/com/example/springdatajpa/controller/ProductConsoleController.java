package com.example.springdatajpa.controller;

import com.example.springdatajpa.model.Product;
import com.example.springdatajpa.service.ProductService;
import com.example.springdatajpa.service.TagService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class ProductConsoleController {

    private final ProductService productService;
    private final TagConsoleController tagConsoleController;
    private final Scanner scanner;

    public ProductConsoleController(ProductService productService, TagService tagService) {
        this.productService = productService;
        this.tagConsoleController = new TagConsoleController(tagService);
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- PRODUCT MANAGEMENT ---");
            System.out.println("1. Display all products");
            System.out.println("2. Add a new product");
            System.out.println("3. Update a product");
            System.out.println("4. Find products by name");
            System.out.println("5. Delete a product");
            System.out.println("6. Manage Tags");
            System.out.println("0. Exit");
            System.out.print("Your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1: displayAllProducts(); break;
                    case 2: addNewProduct(); break;
                    case 3: updateProduct(); break;
                    case 4: findProductsByName(); break;
                    case 5: deleteProduct(); break;
                    case 6: tagConsoleController.run(); break;
                    case 0:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (Exception e) {
                System.err.println("An error occurred: " + e.getMessage());
            }
        }
    }

    private void addNewProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter product description: ");
        String description = scanner.nextLine();
        System.out.print("Enter category ID: ");
        Long categoryId = Long.parseLong(scanner.nextLine());
        System.out.print("Enter tags (comma-separated, e.g., tag1,tag2): ");
        String tagsInput = scanner.nextLine();
        Set<String> tags = new HashSet<>(Arrays.asList(tagsInput.split(",")));

        Product newProduct = productService.createProduct(name, price, description, categoryId, tags);
        System.out.println("Product created successfully with ID: " + newProduct.getId());
    }

    private void displayAllProducts() {
        List<Product> products = productService.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("\n--- ALL PRODUCTS ---");
            products.forEach(p -> System.out.printf("ID: %d, Name: %s, Price: %.2f, Category: %s\n",
                    p.getId(), p.getName(), p.getPrice(), p.getCategory().getName()));
        }
    }

    private void updateProduct() {
        System.out.print("Enter product ID to update: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("Enter new product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new product price: ");
        double price = Double.parseDouble(scanner.nextLine());

        productService.updateProduct(id, name, price);
        System.out.println("Product with ID " + id + " updated successfully.");
    }

    private void findProductsByName() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine();
        List<Product> products = productService.findProductsByName(keyword);
        if (products.isEmpty()) {
            System.out.println("No products found with name containing '" + keyword + "'");
        } else {
            System.out.println("\n--- SEARCH RESULTS ---");
            products.forEach(p -> System.out.printf("ID: %d, Name: %s\n", p.getId(), p.getName()));
        }
    }

    private void deleteProduct() {
        System.out.print("Enter product ID to delete: ");
        Long id = Long.parseLong(scanner.nextLine());
        productService.deleteProduct(id);
        System.out.println("Product with ID " + id + " deleted successfully.");
    }

}