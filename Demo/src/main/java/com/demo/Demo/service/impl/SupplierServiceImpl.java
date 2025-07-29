package com.demo.Demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.Demo.entity.Supplier;
import com.demo.Demo.repository.SupplierRepository;
import com.demo.Demo.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	private final SupplierRepository supplierRepository;
	@Autowired
	public SupplierServiceImpl(SupplierRepository supplierRepository) {
		this.supplierRepository = supplierRepository;
	}
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}
	public List<Supplier> findAll(){
		return supplierRepository.findAll();
	}
	public Optional<Supplier> findByName(String name){
		return supplierRepository.findByName(name);
	}
	public void deleteById(Long id) {
		supplierRepository.deleteById(id);
	}
	public void deleteAll() {
		supplierRepository.deleteAll();
	}
}