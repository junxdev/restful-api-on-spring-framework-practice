package com.safetia.app.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.safetia.app.model.ProductDAO;
import com.safetia.app.model.entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public Product findBy(Long productId, Long storeId) throws SQLException {
		return sqlSession.getMapper(ProductDAO.class).selectOne(productId, storeId);
	}
	
	@Override
	public List<Product> findAll(Long storeId) throws SQLException {
		return sqlSession.getMapper(ProductDAO.class).selectAll(storeId);
	}
	
	@Override
	public void register(Product product, Long storeId) throws SQLException {
		sqlSession.getMapper(ProductDAO.class).insertOne(product, storeId);
	}
	
	@Override
	public Product update(Long productId, Product product, Long storeId) throws SQLException {
		if(sqlSession.getMapper(ProductDAO.class).updateOne(product, storeId) == 0) {
			return null;
		}
		return findBy(productId, storeId);
	}
	
	@Override
	public int delete(Long productId, Long storeId) throws SQLException {
		return sqlSession.getMapper(ProductDAO.class).deleteOne(productId, storeId);
	}
}
