package com.safetia.app.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.safetia.app.model.entity.Product;

public interface ProductRestService {

	Product getProduct(Long id, Long store) throws SQLException;
	Map<Integer, Product> getProducts(Long store) throws SQLException;
	void registerProduct(Product product, Long store) throws SQLException;
	Product updateProduct(Long id, Product product, Long store) throws SQLException;
	int deleteProduct(Long id, Long store) throws SQLException;
	
}
