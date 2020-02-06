package com.api.rnd;

import java.util.ArrayList;
import java.util.List;

import com.api.model.business.Address;
import com.api.model.business.Customer;
import com.api.model.business.CustomerProfile;
import com.api.model.business.Order;
import com.api.model.business.ProductInCart;
import com.google.gson.Gson;

public class Test {
	
	private Customer getCustomer() {
		
		Customer customer = new Customer();
		CustomerProfile profile = new CustomerProfile();
		profile.setContactNo(new Long("919920476463"));
		profile.setEmailId("sandip@gmail.com");
		profile.setFirstName("Sandip");
		profile.setLastName("Patil");
		profile.setPassowrd("Test@123");
		customer.setProfile(profile);
		
		List<Address> addresses = new ArrayList<Address>();
		Address address1 = new Address();
		address1.setAddressLine1("Address Line 1");
		address1.setAddressLine2("Address Line 2");
		address1.setAddressLine3("Address Line 3");
		address1.setFirstName("Address First Name");
		address1.setLastName("Address Last Name");
		address1.setPincode(400708);
		address1.setState("MH");
		addresses .add(address1);
		customer.setAddresses(addresses);
		List<Order> orders = new ArrayList<Order>();
		Order order = new Order();
		Address billingAddress = address1;
		order.setBillingAddress(billingAddress );
		order.setOrderValue(9999.00);
		order.setPaymentCollectionBy("Seller");
		order.setPaymentOption("COD");
		order.setPaymentStatus("Not Paid");
		List<ProductInCart> products = new ArrayList<ProductInCart>();
		ProductInCart productInCart = new ProductInCart();
		productInCart.setProductId("PROD_ELE_MOB_ASUS_V500");
		productInCart.setProductPrice(12599.00);
		productInCart.setUnitPrice(12599.00);
		productInCart.setUnits(1);
		products.add(productInCart);
		order.setProducts(products );
		Address shippingAddress = billingAddress;
		order.setShippingAddress(shippingAddress);
		order.setVendorId("SELLER_01");
		orders.add(order );
		customer.setOrders(orders);
		List<Order> returns = new ArrayList<Order>();
		returns.add(order);
		customer.setReturns(returns);
		Order saveForLater = order;
		customer.setSaveForLater(saveForLater);
		Order shoppingCart = order;
		customer.setShoppingCart(shoppingCart);
		
		return customer;
	}

	
	
	

	public static void main(String[] args) {
		
		Test test = new Test();
		Customer customer = test.getCustomer();
		
		Gson gson = new Gson();
		System.out.println(gson.toJson(customer));
		/*
		 * String str =
		 * "<form name=\"Mobiles\" class=\"col s8 \"><div id=\"replaceMe\"></div>";
		 * String json =gson.toJson(str); System.out.println(json);
		 */
		/*
		 * Gson gson = new Gson();
		 * 
		 * Map<String, List<String>> filters = new HashMap<String, List<String>>();
		 * Map<String, Map<Integer, Integer>> range = new HashMap<String,
		 * Map<Integer,Integer>>();
		 * 
		 * ESSearchRequest req = new ESSearchRequest(); req.setCategory("mobile");
		 * req.setSearchTerm("apple"); req.setSortBy("asc");
		 * 
		 * List<String> list1 = new ArrayList<String>(); list1.add("4x");
		 * list1.add("12x");
		 * 
		 * List<String> list2 = new ArrayList<String>(); list2.add("4K");
		 * list2.add("UHD");
		 * 
		 * filters.put("zoom",list1); filters.put("display",list2);
		 * 
		 * req.setFilters(filters); Map range1 = new HashMap(); range1.put(10000,
		 * 14999); range1.put(15000, 24999);
		 * 
		 * range.put("price", range1); range.put("price2", range1); req.setRange(range);
		 * System.out.println(gson.toJson(req)); Map res = gson.fromJson(
		 * "{\"Filter\":{\"display\":[\"4K\",\"FHD\"],\"sound\":[\"20W\",\"SurroundSound\"]}}",
		 * Map.class); res.size();
		 */
	}
}
