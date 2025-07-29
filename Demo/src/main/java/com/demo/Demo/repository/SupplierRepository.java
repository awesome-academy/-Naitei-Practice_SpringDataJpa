package com.demo.Demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.Demo.entity.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long>{

	Optional<Supplier> findByName(String name);
	
}