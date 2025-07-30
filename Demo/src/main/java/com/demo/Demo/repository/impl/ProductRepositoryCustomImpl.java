package com.demo.Demo.repository.impl;
import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;
import com.demo.Demo.entity.Product;
import com.demo.Demo.repository.*;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override 
	public List<Product> findByPriceAndSupplier(Long supplierId, Double minPrice, Double maxPrice){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = cb.createQuery(Product.class);
		Root<Product> productRoot = query.from(Product.class);
		List<Predicate> predicates = new ArrayList<>();
		if(supplierId != null) {
			predicates.add(cb.equal(productRoot.get("supplier").get("id"), supplierId));
		}
		if(minPrice != null) {
			predicates.add(cb.ge(productRoot.get("price"), minPrice));
		}
		if(maxPrice != null) {
			predicates.add(cb.le(productRoot.get("price"), maxPrice));
		}
		
		query.where(cb.and(predicates.toArray(new Predicate[0])));
		TypedQuery<Product> typedQuery = entityManager.createQuery(query);
		return typedQuery.getResultList();
	}
}