package com.demo.Demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.Demo.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, ProductRepositoryCustom{
	@Modifying
	@Query("delete from Product p where p.supplier.id = ?1")
	void deleteInBulkBySupplierId(Long supplierId);
	
}