package com.api.cache;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.api.core.Constants;
import com.api.es.ESClient;
import com.api.model.es.ESSearchResponse;
import com.google.gson.Gson;

public class CategoryCache {
	
	private static Map<String, String> categories = null;
	private static Map<String, String> aggsForCategories = null;
	
	public static Map<String, String> getAllCategories() throws IOException{
		if(categories == null || categories.isEmpty())
			refresh();
		return categories;
	}
	
	public static String getAggsForCategory(String categoryName) throws IOException{
		if(aggsForCategories == null || aggsForCategories.isEmpty())
			refresh();
		return aggsForCategories.get(categoryName);
	}
	
	public static void refresh() throws IOException {
		if(categories == null)
			categories = new ConcurrentHashMap<String, String>();
		if(aggsForCategories==null) aggsForCategories = new ConcurrentHashMap<String, String>();
		aggsForCategories.clear();
		categories.clear();
		
		categories = buildCategoriesAndAggs();
		System.out.println("NEXT");
	}
	
	public static void printAllAggs() {
		System.out.println("_____________________________________");
		for(String key:aggsForCategories.keySet()) {
			System.out.println(aggsForCategories.get(key));
		}
	}

	private static Map<String, String> buildCategoriesAndAggs() throws IOException {
		String response = ESClient.searchAll(Constants.AGGREGATIONS_INDEX);
		Gson gson = new Gson();
		ESSearchResponse esReponse = gson.fromJson(response, ESSearchResponse.class);
		aggsForCategories =  esReponse.getAggsForIndex();
		
		return esReponse.getIndexNames();
		
	}
	
	

}
