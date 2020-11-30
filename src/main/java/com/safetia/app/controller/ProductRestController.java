package com.safetia.app.controller;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.safetia.app.model.ProductDAO;
import com.safetia.app.model.entity.Product;
import com.safetia.app.service.ProductRestService;

@Controller
@RequestMapping(path = "/stores/{storeId}/products")
public class ProductRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class); 

	@Autowired
	ProductRestService sus01Service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Map<Integer, Product>> getAll(@PathVariable Long storeId) throws SQLException {
		return new ResponseEntity<Map<Integer, Product>>(sus01Service.getProducts(storeId), HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{productId}", method = RequestMethod.GET)
	public ResponseEntity<Product> getOne(@PathVariable Long storeId, @PathVariable Long productId) throws SQLException {
		Product product = sus01Service.getProduct(productId, storeId);
		if(product == null) {
			return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createOne(@PathVariable Long storeId, @RequestBody Product product){
		try {
			sus01Service.registerProduct(product, storeId);
		} catch (SQLException e) {
			return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
		} catch (DuplicateKeyException e) {
			return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{productId}", method = RequestMethod.PATCH)
	public ResponseEntity<Product> updateOne(@PathVariable Long storeId, @PathVariable Long productId, @RequestBody Product product) throws SQLException {
		product = sus01Service.updateProduct(productId, product, storeId);
		if(product == null) {
			return new ResponseEntity<Product>(product, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(path = "/{productId}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteOne(@PathVariable Long storeId, @PathVariable Long productId) throws SQLException {
		if(sus01Service.deleteProduct(productId, storeId) == 0) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Product>(HttpStatus.OK);
	}

}
