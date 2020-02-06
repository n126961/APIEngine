package com.api.model.business;

import java.util.Date;
import java.util.List;

public class BrowseHistory {
	
	private String customerId;
	private List<BrowseProduct> latestVisitedProducts;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public List<BrowseProduct> getLatestVisitedProducts() {
		return latestVisitedProducts;
	}
	public void setLatestVisitedProducts(List<BrowseProduct> latestVisitedProducts) {
		this.latestVisitedProducts = latestVisitedProducts;
	}
}

class BrowseProduct{
	private Product product;
	private Date visitedOn;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Date getVisitedOn() {
		return visitedOn;
	}
	public void setVisitedOn(Date visitedOn) {
		this.visitedOn = visitedOn;
	}
	
	
}
