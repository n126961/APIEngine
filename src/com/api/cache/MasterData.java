package com.api.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.model.es.ESSearchResponse;

public class MasterData {
	
	Map<Integer, Object> dataMap = null;
	
	void init(Map<Integer, Object> dataMap) {
		this.dataMap = dataMap;
	}
	
	void add(Integer key, Object val) {
		dataMap.put(key, val);
	}
	
	Object get(Integer key) {
		return dataMap.get(key);
	}
	
	void refresh(Integer key) {
		
	}
	
	public static void main(String[] args) {
		Map<Integer, Object> dataMap = new ConcurrentHashMap<Integer, Object>();
		dataMap.put(1, new String ("11"));
		dataMap.put(1, new String ("113"));
		
		System.out.println(dataMap.get(1));
	}
		
}

class CategoryMasterCache extends MasterData{
	
	
	@Override
	void init(Map<Integer, Object> dataMap) {
		// TODO Auto-generated method stub
		super.init(dataMap);
		
	}
	
	@Override
	void add(Integer key, Object val) {
		// TODO Auto-generated method stub
		super.add(key, val);
	}
	
	@Override
	Object get(Integer key) {
		// TODO Auto-generated method stub
		return super.get(key);
	}
}
