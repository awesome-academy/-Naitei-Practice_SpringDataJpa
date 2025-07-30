package com.practice.jpa;


import com.practice.jpa.config.AppConfig;
import com.practice.jpa.service.*;
import com.practice.jpa.entity.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CategoryService categoryService = context.getBean(CategoryService.class);
        ProductService productService = context.getBean(ProductService.class);

        // 1. Tạo danh mục
        CategoryEntity cat = new CategoryEntity();
        cat.setName("Electronics");
        cat.setDescription("All electronic items");
        categoryService.create(cat);

        // 2. Tạo sản phẩm
        ProductEntity p = new ProductEntity();
        p.setName("iPhone");
        p.setDescription("Smartphone from Apple");
        p.setImportPrice(600);
        p.setSellingPrice(999);
        p.setStockQuantity(50);
        p.setImageUrl("https://example.com/iphone15.jpg");
        p.setIsFeatured(true);
        p.setCategory(cat);
        productService.create(p);

        // 3. Xem toàn bộ sản phẩm
        productService.getAll().forEach(prod -> System.out.println(prod.getName()));

//        // 4. Xoá thử 1 sản phẩm
//        productService.delete(p.getId());

    }
}