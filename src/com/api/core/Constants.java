package com.api.core;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String ES_HOST = "http://localhost:9200/";
	public static final String NO_OF_SHARDS = "1";
	public static final String AGGREGATIONS_INDEX = "all_aggs_names/";
	public static final String CUSTOMER_INDEX = "customers/";
	
	
	// parameters for search 
	public enum SearchHelper{
		
		CATEGORY("category","c"),
		SEARCH_KEYWORD("term","q"),
		DIRECTION("sort","s"),
		NARROW("narrow","f"),
		ORDER_BY("order","o"),
		SELECTED_CATEGORY("category","o");
		
		private String queryParameter;
		private String facetTerm;
		
		SearchHelper(String queryParameter, String facetTerm){
			this.queryParameter = queryParameter;
			this.facetTerm = facetTerm;
		}
		
		public String queryParameter(){
			return this.queryParameter;
		}
		public String facetTerm() {
			return facetTerm;
		}
		
	}
	
	
	public enum ContextHelper{
		
		REQUEST_INPUT("QueryStringForGet",1),
		SEARCH_RESULT_ES("SearchResultInESFormat",2),
		FORMATTED_SEARCH_RESPONSE("FormattedSearchResponse",3),
		RESPONSE("response",4),
		ALL_CATEGORIES("allCats",4), 
		USERNAME("username",5),
		PASSWORD("pass",6),
		CUSTOMER_PROFILE("profile",7);
		
		private String key;
		private Integer id;
		
		ContextHelper(String key, Integer id){
			this.key = key;
			this.id = id;
		}
		
		public String getKey() {
	    	return key;
	    }

		public Integer getId() {
			// TODO Auto-generated method stub
			return id;
		}
		
		
	}

	public enum Steps{

	    SEARCH_BUILD_QUERY("SearchQueryBuilder",1),
	    FORMAT_SEARCH_RESPONSE("FormatSearchResponse",2), 
	    FETCH_CATEGORY("FetchAllCategoriesStep",3),
	    CREATE_CATEGORY_WITH_AGGREGATIONS("CreateCategoryWithAggsStep",4),
	    CREATE_CUSTOMER("CreateCustomerStep",4), 
	    VALIDATE_CUSTOMER_LOGIN("ValidateCustomerStep",5);

	    private Integer stepId;
	    private String className;

	    Steps(String className,Integer stepId) {
	        this.stepId = stepId;
	        this.className = className;
	    }

	    public Integer stepId() {
	        return stepId;
	    }
	    public String className() {
	    	return className;
	    }
	    
	    public static Integer getStepIdFromName(String className) {
	    	for (Steps step : Steps.values()) {
				if(step.className().equalsIgnoreCase(className))
					return step.stepId();
			}
	    	return 0;
	    }
	
	}
	
	public enum DisplayAs{
		
		TEXT("text"),
		UPLOAD("upload"),
		DROPDOWN("dropdown"),
		DATE("date"),
		CHECKBOX("checkbox"),
		TEXTAREA("textarea");
		
		private String displayAs;
		DisplayAs(String displayAs){
			this.setDisplayAs(displayAs);
		}
		public String getDisplayAs() {
			return displayAs;
		}
		public void setDisplayAs(String displayAs) {
			this.displayAs = displayAs;
		}
		
	}
	
	public enum Cache{

	    ALL_CATEGORY_NAMES("allCategories",1),
	    FORMAT_SEARCH_RESPONSE("FormatSearchResponse",2), 
	    FETCH_CATEGORY("FetchCategoriesStep",3),
	    CREATE_CATEGORY_WITH_AGGREGATIONS("CreateCategoryWithAggsStep",4);

	    private Integer stepId;
	    private String name;

	    Cache(String name,Integer stepId) {
	        this.stepId = stepId;
	        this.name = name;
	    }

	    public Integer id() {
	        return stepId;
	    }
	    public String cacheName() {
	    	return name;
	    }
	    
	    public static Integer getCacheIdFromName(String name) {
	    	for (Cache step : Cache.values()) {
				if(step.cacheName().equalsIgnoreCase(name))
					return step.id();
			}
	    	return 0;
	    }
	    
	    public static List<Integer> getAllCacheIds(){
	    	List<Integer> ints = new ArrayList();
	    	for (Cache cache : Cache.values()) {
	    		ints.add(cache.id());
			}
	    	
	    	return ints;
	    }
	
	}
}
