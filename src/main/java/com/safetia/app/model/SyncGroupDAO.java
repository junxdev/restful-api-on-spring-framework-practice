package com.safetia.app.model;

import java.sql.SQLException;
import java.util.List;

import com.safetia.app.model.entity.Store;
import com.safetia.app.model.entity.SyncGroup;

public interface SyncGroupDAO {
	
	List<SyncGroup> selectGroup(Long storeId) throws SQLException;
	SyncGroup selectOne(Long storeId) throws SQLException;

}
