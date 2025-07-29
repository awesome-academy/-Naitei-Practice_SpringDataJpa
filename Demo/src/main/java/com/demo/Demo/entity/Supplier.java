package com.demo.Demo.entity;

import java.util.*;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "suppliers")
@Data
@NoArgsConstructor
public class Supplier{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	
	@OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();
	
	public Supplier(String name) {
		this.name = name;
	}
	public String toString() {
		return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';		
	}
	public void addProduct(Product product) {
		this.products.add(product);
		product.setSupplier(this);
	}
	public void removeProduct(Product product) {
		this.products.remove(product);
		product.setSupplier(null);
	}
}