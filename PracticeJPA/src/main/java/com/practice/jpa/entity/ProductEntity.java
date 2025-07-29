package com.practice.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name="products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private double importPrice;
    private double sellingPrice;
    private Integer stockQuantity;
    private String imageUrl;
    private Boolean isFeatured;

    @ManyToOne
    @JoinColumn(name = "category_id") // tên cột trong bảng products
    private CategoryEntity category;

    @Column(columnDefinition = "TEXT")
    private String  description;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Boolean getFeatured() {
        return isFeatured;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
