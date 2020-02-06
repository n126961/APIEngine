package com.api.model.business;

import java.util.List;

public class Customer {

	private CustomerProfile profile;
	private List<Address> addresses;
	private List<Order> orders;
	private List<Order> returns;
	private Order shoppingCart;
	private Order saveForLater;
	
	public CustomerProfile getProfile() {
		return profile;
	}
	public void setProfile(CustomerProfile profile) {
		this.profile = profile;
	}
	public List<Address> getAddresses() {
		return addresses;
	}
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	public List<Order> getReturns() {
		return returns;
	}
	public void setReturns(List<Order> returns) {
		this.returns = returns;
	}
	public Order getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(Order shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public Order getSaveForLater() {
		return saveForLater;
	}
	public void setSaveForLater(Order saveForLater) {
		this.saveForLater = saveForLater;
	}
	
}
