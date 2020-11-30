package com.safetia.app.model.entity;

public class Product {
	
	private Long productId;
	private String productName;
	
	public Long getProductId() {
		return productId;
	}
	
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Product() {
	}
	
	public Product(Long productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + "]";
	}
}
