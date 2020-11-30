package com.safetia.app.model.entity;

public class SyncGroup {

	private Long syncGroupId;
	private Long storeId;
	private int syncCreate;
	private int syncUpdate;
	private int syncDelete;
	
	public Long getSyncGroupId() {
		return syncGroupId;
	}
	
	public void setSyncGroupId(Long syncGroupId) {
		this.syncGroupId = syncGroupId;
	}
	
	public Long getStoreId() {
		return storeId;
	}
	
	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}
	
	public boolean isSyncCreate() {
		return syncCreate == 1;
	}
	
	public void setSyncCreate(int syncCreate) {
		this.syncCreate = syncCreate;
	}
	
	public boolean isSyncUpdate() {
		return syncUpdate == 1;
	}
	
	public void setSyncUpdate(int syncUpdate) {
		this.syncUpdate = syncUpdate;
	}
	
	public boolean isSyncDelete() {
		return syncDelete == 1;
	}
	
	public void setSyncDelete(int syncDelete) {
		this.syncDelete = syncDelete;
	}
	
	public boolean isMaster() {
		return !isSyncCreate() && !isSyncUpdate() && !isSyncDelete(); 
	}
}
