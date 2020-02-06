package com.api.cache;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.core.Constants;

public class CacheManager {
	/*
	 * 1) all category names - Map<1,Map<indexName, indexLabel>>
	 * 2) aggs for category - Map<aggId, Map<indexName, String>>
	 * 
	 * */
	private static Map<Integer, Map<String,Object>> masterCache = new ConcurrentHashMap<Integer, Map<String,Object>>();
	
	public static void refreshAll() {
		masterCache.clear();
		buildAll();
	}

	private static void buildAll() {
		
		List<Integer> allCacheIds = Constants.Cache.getAllCacheIds();
		for(Integer id:allCacheIds) {
			refreshCacheFor(id);
		}
	}

	private static void refreshCacheFor(Integer id) {
		
	}
	
	public static Map<String,Object> getCache(Integer id){
		return masterCache.get(id);
	}

}
