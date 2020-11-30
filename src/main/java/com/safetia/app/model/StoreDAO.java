package com.safetia.app.model;

import java.sql.SQLException;
import java.util.List;

import com.safetia.app.model.entity.Store;

public interface StoreDAO {
	
	Store selectOne(Long storeId) throws SQLException;

}
