package com.api.steps;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;

import com.api.cache.CategoryCache;
import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;
import com.api.es.ESClient;

public class SearchQueryBuilderStep implements ExecutionSteps {

	@Override
	public void execute(Context context) throws Exception {

		// Fetch URIInfo and generate query string
		UriInfo uriInfo = (UriInfo) context.get(Constants.ContextHelper.REQUEST_INPUT.getId());
		MultivaluedMap<String, String> paras = uriInfo.getQueryParameters();
		
		/*
		 * for (String key : paras.keySet()) { System.out.println(key + ":" +
		 * paras.getFirst(key)); String val = paras.getFirst(key); //res =
		 * gson.fromJson(val, ESSearchRequest.class);
		 * 
		 * }
		 */

		// String category = paras.getFirst(arg0);

		extractAndExecute(paras, context);

	}

	

	/**
	 * Parse the input to take out query parameters category = product category
	 * filter = filters for the query term = search term group = aggregations for
	 * facets order = order by - relevancy, price high low
	 * 
	 * if category is not selected then don't display group if have only one
	 * category in search result then display the group
	 * @throws Exception 
	 */
	private void extractAndExecute(MultivaluedMap<String, String> paras, Context context) throws Exception {
		String orderBy = paras.getFirst(Constants.SearchHelper.ORDER_BY.queryParameter());
		String direction = paras.getFirst(Constants.SearchHelper.DIRECTION.queryParameter());
		String category = paras.getFirst(Constants.SearchHelper.CATEGORY.queryParameter());
		String filter = paras.getFirst(Constants.SearchHelper.NARROW.queryParameter());
		String query = paras.getFirst(Constants.SearchHelper.SEARCH_KEYWORD.queryParameter());
		String response = buildSearchQuery(category, query, filter, orderBy, direction);
		context.put(Constants.ContextHelper.SEARCH_RESULT_ES.getId(), response);
		System.out.println("After Execution:"+response);
	}

	/**
	 * Applicable only when category is selected
	 * 
	 * Working on below request format for URI of 
	 * 
	 * 
	 * POST mob1/_search
	 *  
	 * <pre>{
  "query": {
    
    "bool": {
      "should": {
        "match": {
          "formatted": {
            "query": "search text",
            "fuzziness":"2"
          }
        }
      },
      "filter": {
        "query_string": {
          "query": "(brand:(Samsung OR Oppo)) AND (screen_size:5.5 OR screen_size:4.8) AND (price:[10000 TO 25000])"
        }
      }
      
    }
  },
  "sort":[{"price" : {"order" : "desc"}}]
      
}
</pre>
	 * @param category
	 * @param query
	 * @param filter
	 * @param orderBy
	 * @throws Exception 
	 */
	private String buildSearchQuery(String category, String query, String filter, String orderBy, String direction) throws Exception {
		String response = null;
		StringBuilder sb = new StringBuilder("{\"query\":{\"bool\":{");

		sb.append(getSearchQueryPart(query));
		
		if (!StringUtils.isEmpty(filter)) {
			
			sb.append(getFilterPart(filter));
		} 
		sb.append("}}");
		if (!StringUtils.isEmpty(orderBy)) {
			
			sb.append(getSortPart(orderBy, direction));
		} 
		sb.append("}");
		String aggs = CategoryCache.getAggsForCategory(category);
		if(!aggs.isEmpty()) {
			sb.append(",").append(aggs);	
		}
		if(StringUtils.isEmpty(category)) {
			response = ESClient.executeSearch("all", sb);
		} else {
			response = ESClient.executeSearch(category, sb);
		}
		return response;
	}



	private StringBuilder getSortPart(String orderBy, String direction) {
		// TODO Auto-generated method stub
		return new StringBuilder(",\"sort\":[{\"").append(orderBy).append("\":{\"order\":\"").append(direction).append("\"}}]");
	}



	private Object getFilterPart(String filter) {
		// TODO Auto-generated method stub
		return new StringBuilder(",\"filter\":{\"query_string\":{\"query\": \"").append(filter).append("\"}}");
	}



	private Object getSearchQueryPart(String query) {
		// TODO Auto-generated method stub
		return new StringBuilder("\"should\":{\"match\":{\"formatted\":{\"query\": \"").append(query).append("\",\"fuzziness\":\"2\"}}}");
		
	}



	

}
