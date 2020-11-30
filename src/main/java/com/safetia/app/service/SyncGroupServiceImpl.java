package com.safetia.app.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.safetia.app.model.ProductDAO;
import com.safetia.app.model.SyncGroupDAO;
import com.safetia.app.model.entity.Product;
import com.safetia.app.model.entity.SyncGroup;

@Service
public class SyncGroupServiceImpl implements SyncGroupService {
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<SyncGroup> getSyncGroup(Long storeId) throws SQLException {
		return sqlSession.getMapper(SyncGroupDAO.class).selectGroup(storeId);
	}

	@Override
	public boolean isMaster(Long storeId) throws SQLException {
		SyncGroup syncGroup = sqlSession.getMapper(SyncGroupDAO.class).selectOne(storeId);
		return syncGroup.isMaster();
	}

	@Override
	public void syncCreate(Product product, Long storeId) throws SQLException {
		if(isMaster(storeId)) {
			for(SyncGroup syncGroup : getSyncGroup(storeId)) {
				if(syncGroup.isSyncCreate()) {
					System.out.println("**************" + syncGroup.getStoreId() + " syncCreate ");
					sqlSession.getMapper(ProductDAO.class).insertOne(product, syncGroup.getStoreId());
				}
			}
		}
	}
	
	@Override
	public void syncUpdate(Long productId, Product product, Long storeId) throws SQLException {
		if(isMaster(storeId)) {
			for(SyncGroup syncGroup : getSyncGroup(storeId)) {
				if(syncGroup.isSyncUpdate()) {
					System.out.println("**************" + syncGroup.getStoreId() + " syncUpdate ");
					sqlSession.getMapper(ProductDAO.class).updateOne(product, syncGroup.getStoreId());
				}
			}
		}
	}
	
	@Override
	public void syncDelete(Long productId, Long storeId) throws SQLException {
		if(isMaster(storeId)) {
			for(SyncGroup syncGroup : getSyncGroup(storeId)) {
				if(syncGroup.isSyncDelete()) {
					System.out.println("**************" + syncGroup.getStoreId() + " syncDelete ");
					sqlSession.getMapper(ProductDAO.class).deleteOne(productId, syncGroup.getStoreId());
				}
			}
		}
	}
}
