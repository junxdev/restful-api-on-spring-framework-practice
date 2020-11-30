package com.safetia.app.service;

import java.sql.SQLException;

import com.safetia.app.model.entity.Store;

public interface StoreService {

	Store findBy(Long storeId) throws SQLException;

}
