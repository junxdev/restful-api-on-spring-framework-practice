package com.safetia.app.service;

import java.sql.SQLException;
import java.util.List;

import com.safetia.app.model.entity.Product;
import com.safetia.app.model.entity.SyncGroup;

public interface SyncGroupService {

	boolean isMaster(Long storeId) throws SQLException;
	List<SyncGroup> getSyncGroup(Long storeId) throws SQLException;
	void syncCreate(Product product, Long storeId) throws SQLException;
	void syncUpdate(Long productId, Product product, Long storeId) throws SQLException;
	void syncDelete(Long productId, Long storeId) throws SQLException;

}
