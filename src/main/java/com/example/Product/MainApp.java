package com.example.Product;

import com.example.Product.config.AppConfig;
import com.example.Product.config.H2ServerConfig;
import com.example.Product.entity.Product;
import com.example.Product.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class, H2ServerConfig.class);

        ProductService productService = context.getBean(ProductService.class);

        // 1. Tạo sản phẩm
        Product p1 = new Product("Laptop", "Gaming Laptop",
                new BigDecimal("1200.00"), "Electronics", 10);
        productService.createProduct(p1);

        Product p2 = new Product("MacBook", "Gaming Laptop",
                new BigDecimal("1200.00"), "Electronics", 10);
        productService.createProduct(p2);

        // 2. Lấy tất cả sản phẩm
        System.out.println("=== Danh sách sản phẩm ===");
        productService.getAllProducts().forEach(System.out::println);

        // 3. Cập nhật sản phẩm
        p1.setPrice(new BigDecimal("1000.00"));
        productService.updateProduct(p1.getId(), p1);

        // 4. Tìm theo ID
        System.out.println("=== Tìm sản phẩm ID = " + p1.getId() + " ===");
        productService.getProductById(p1.getId())
                .ifPresent(System.out::println);

        // 5. Xoá sản phẩm
        productService.deleteProduct(p1.getId());
        System.out.println("=== Sau khi xoá ===");
        productService.getAllProducts().forEach(System.out::println);
        /*Xem kết quả database tại http://localhost:8082
        JDBC URL: jdbc:h2:mem:testdb
        Username: sa
        Password: (để trống)*/
    }
}
