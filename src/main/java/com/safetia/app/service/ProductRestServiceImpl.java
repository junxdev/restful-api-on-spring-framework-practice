package com.safetia.app.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.safetia.app.model.ProductDAO;
import com.safetia.app.model.entity.Product;
import com.safetia.app.model.entity.Store;

@Component
public class ProductRestServiceImpl implements ProductRestService {

	@Autowired
	StoreService storeService;
	@Autowired
	ProductService productService;
	@Autowired
	SyncGroupService syncGroupService;
	
	private boolean haveStore(Long storeId) throws SQLException {
		return storeService.findBy(storeId) != null;
	}
	
	@Override
	public Product getProduct(Long productId, Long storeId) throws SQLException {
		if(haveStore(storeId)) {
			return productService.findBy(productId, storeId);
		}
		return null;
	}

	@Override
	public Map<Integer, Product> getProducts(Long storeId) throws SQLException {
		List<Product> list = null;
		if(haveStore(storeId)) {
			list = productService.findAll(storeId);
		}
		Map<Integer, Product> map = new HashMap<Integer, Product>();
		for(int i = 0; i < list.size(); i++) {
			map.put(i, list.get(i));
		}
		return map;
	}

	@Override
	public void registerProduct(Product product, Long storeId) throws SQLException {
		if(haveStore(storeId)) {
			productService.register(product, storeId);
			syncGroupService.syncCreate(product, storeId);
		}
	}
	
	@Override
	public Product updateProduct(Long productId, Product product, Long storeId) throws SQLException {
		if(haveStore(storeId)) {
			product = productService.update(productId, product, storeId);
			syncGroupService.syncUpdate(productId, product, storeId);
			return product;
		}
		return null;
	}
	
	@Override
	public int deleteProduct(Long productId, Long storeId) throws SQLException{
		if(haveStore(storeId)) {
			int result = productService.delete(productId, storeId);
			syncGroupService.syncDelete(productId, storeId);
			return result;
		}
		return 0;
	}

}
