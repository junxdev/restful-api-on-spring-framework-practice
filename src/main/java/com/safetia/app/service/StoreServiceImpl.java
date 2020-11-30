package com.safetia.app.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetia.app.model.StoreDAO;
import com.safetia.app.model.SyncGroupDAO;
import com.safetia.app.model.entity.Product;
import com.safetia.app.model.entity.Store;

@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public Store findBy(Long storeId) throws SQLException {
		return sqlSession.getMapper(StoreDAO.class).selectOne(storeId);
	}

}
