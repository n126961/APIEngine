package com.api.core;

import java.util.concurrent.ConcurrentHashMap;

public class Context {
	ConcurrentHashMap<Integer, Object> context  = null;
	public Context() {
	context	= new ConcurrentHashMap<Integer, Object>();
	}
	

	public ConcurrentHashMap<Integer, Object> start() {
		return context;
	}
	
	public void put(Integer key, Object value) {
		context.put(key, value);
	}
	
	public Object get(Integer key) {
		return context.get(key);
	}
	
	public void clear() {
		context = null;
	}
}
