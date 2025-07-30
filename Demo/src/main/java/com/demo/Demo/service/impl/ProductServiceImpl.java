package com.demo.Demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.Demo.entity.Product;
import com.demo.Demo.repository.ProductRepository;
import com.demo.Demo.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	public final ProductRepository productRepository;
	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	@Override
	@Transactional
	public Product save(Product product) {
		return productRepository.save(product);
	}
	@Override
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	@Override
	public Optional<Product> findById(Long id){
		return productRepository.findById(id);
	}
	@Override 
	@Transactional
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}
	@Override
	@Transactional
	public void deleteAll() {
		productRepository.deleteAll();
	}
	@Override
	@Transactional
	public void deleteInBulkBySupplierId(Long supplierId) {
		productRepository.deleteInBulkBySupplierId(supplierId);
	}
	@Override
	public List<Product> findByPriceAndSupplier(Long supplierId, Double minPrice, Double maxPrice){
		return productRepository.findByPriceAndSupplier(supplierId, minPrice, maxPrice);
	}
}