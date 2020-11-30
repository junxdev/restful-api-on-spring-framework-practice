package com.safetia.app.service;

import java.sql.SQLException;
import java.util.List;

import com.safetia.app.model.entity.Product;

public interface ProductService {

	Product findBy(Long id, Long storeId) throws SQLException;
	List<Product> findAll(Long storeId) throws SQLException;
	void register(Product product, Long storeId) throws SQLException;
	Product update(Long id, Product product, Long storeId) throws SQLException;
	int delete(Long id, Long storeId) throws SQLException;
	
}
