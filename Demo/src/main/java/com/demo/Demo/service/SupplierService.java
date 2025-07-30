package com.demo.Demo.service;

import java.util.List;
import java.util.Optional;

import com.demo.Demo.entity.Supplier;


public interface SupplierService{
	Supplier save(Supplier supplier);
	List<Supplier> findAll();
	Optional<Supplier> findByName(String name);
	void deleteById(Long id);
	void deleteAll();
}