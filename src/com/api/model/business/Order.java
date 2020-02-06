package com.api.model.business;

import java.util.List;

public class Order {
	private List<ProductInCart> products;
	private Double orderValue;
	private Address billingAddress;
	private Address shippingAddress;
	private String paymentOption;//COD,UPI,netbanking etc
	private String paymentCollectionBy;//Platform,vendor
	private String paymentStatus;
	private String vendorId;
	public List<ProductInCart> getProducts() {
		return products;
	}
	public void setProducts(List<ProductInCart> products) {
		this.products = products;
	}
	public Double getOrderValue() {
		return orderValue;
	}
	public void setOrderValue(Double orderValue) {
		this.orderValue = orderValue;
	}
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getPaymentOption() {
		return paymentOption;
	}
	public void setPaymentOption(String paymentOption) {
		this.paymentOption = paymentOption;
	}
	public String getPaymentCollectionBy() {
		return paymentCollectionBy;
	}
	public void setPaymentCollectionBy(String paymentCollectionBy) {
		this.paymentCollectionBy = paymentCollectionBy;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public String getVendorId() {
		return vendorId;
	}
	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}
	
	
}

