package com.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.api.core.FrameworkCache;

@ApplicationPath("/")
public class RestApp extends Application{
	
	
	
	public Set<Class<?>> getClasses() {
		List classes = new ArrayList();
		classes.add(SearchController.class);
		classes.add(CategoryController.class);
		classes.add(CustomerController.class);
		return new
		     HashSet<Class<?>>(classes); 
		   } 
	
	
	  static {
	  
	  FrameworkCache.build(); }
	 
}
