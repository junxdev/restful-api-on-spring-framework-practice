package com.safetia.app.model;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.safetia.app.model.entity.Product;

public interface ProductDAO {
	
	Product selectOne(@Param(value = "productId") Long productId, @Param(value = "storeId") Long storeId) throws SQLException;
	List<Product> selectAll(@Param(value = "storeId") Long storeId) throws SQLException;
	void insertOne(@Param(value = "product") Product product, @Param(value = "storeId") Long storeId) throws SQLException;
	int updateOne(@Param(value = "product") Product product, @Param(value = "storeId") Long storeId) throws SQLException;
	int deleteOne(@Param(value = "productId") Long productId, @Param(value = "storeId") Long storeId) throws SQLException;

}
