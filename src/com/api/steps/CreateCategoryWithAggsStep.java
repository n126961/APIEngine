package com.api.steps;

import java.util.Map;

import com.api.cache.CategoryCache;
import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;
import com.api.es.ESClient;
import com.google.gson.Gson;

public class CreateCategoryWithAggsStep implements ExecutionSteps {

	@Override
	public void execute(Context context) throws Exception {
		Map inputMap = (Map) context.get(Constants.ContextHelper.REQUEST_INPUT.getId());
		
		/*
		 * for (String key : paras.keySet()) { System.out.println(key + ":" +
		 * paras.getFirst(key)); String val = paras.getFirst(key); //res =
		 * gson.fromJson(val, ESSearchRequest.class);
		 * 
		 * }
		 */

		// String category = paras.getFirst(arg0);

		String response = createIndex(inputMap, context);
		
		context.put(Constants.ContextHelper.RESPONSE.getId(), response);
		CategoryCache.refresh();
	}
/**
 * "aggs": {
    "Brands": {
      "terms": {
        "field": "brand",
        "size": 50
      }
    },
    "Screen Sizes": {
      "terms": {
        "field": "screen_size",
        "size": 10
      }
    },
    "Display Resoulation": {
      "terms": {
        "field": "display_resolution",
        "size": 10
      }
    },
    "Price": {
      "range": {
        "field": "price",
        "ranges" : [
                    { "to" : 10000.0 },
                    { "from" : 10000.0, "to" : 15000.0 },
                    { "from" : 15000.0 }
                ]
      }
    }
  }
 * @param inputMap
 * @param context
 * @return
 */
	//{0={indexName=0, indexLabel=1}, 1={filter=5, fieldName=2, range3=, range4=, range1=6, fieldLabel=3, range2=7, inputType=4}, 2={filter=15, fieldName=11, range3=, range4=, range1=, fieldLabel=12, range2=18, inputType=13}}
	
	//
	
	private String createIndex(Map<String, Map<String,String>> inputMap, Context context) {
		StringBuilder sb = new StringBuilder();
		StringBuilder aggs = new StringBuilder("{")   ;
		StringBuilder form = new StringBuilder("<div class=\"row\">");
		String commoa = "";
		String aggsCommoa = "\\\\\\\"aggs\\\\\\\": {";
		String indexName = null;
		String createForm = null;
		String createAggs = null;
		String createIndex = null;
		String response = null;
		sb.append("{\"settings\" : {\"number_of_shards\" : "+Constants.NO_OF_SHARDS+"},\"mappings\" : {\"properties\": {");
		for(String idObj:inputMap.keySet()) {
			String id = idObj;
			
			if(id != null) {
				Map<String, String> childMap = inputMap.get(id);
				if(id.equalsIgnoreCase("0")) {
					//Fetch index name
					indexName = childMap.get("indexName");
					aggs.append("\"indexName\":\""+indexName+"\",\"indexLabel\":\""+childMap.get("indexLabel")+"\",\"aggs\":\"\\\"");
					form.append("<h2>"+childMap.get("indexLabel")+"</h2><form name=\""+indexName+"\" class=\"col s8 \"><div id=\"replaceMe\"></div>");
					
					createForm=childMap.get("form");
					createAggs=childMap.get("filtersAggs");
					createIndex=childMap.get("createIndex");
				}else {
					//Fetch index properties					
					sb.append(commoa+"\""+childMap.get("fieldName")+"\": {\"type\": \""+childMap.get("inputType")+"\"");
					if(childMap.get("indexMe")!=null && childMap.get("indexMe").equalsIgnoreCase("n")) {
						sb.append(",\"index\" : false");
					}
					sb.append("}");
					form.append("<div class=\"row\"><div class=\"input-field col s8\">");
					form.append("<input id=\""+childMap.get("fieldName")+"\" type=\"text\"> <label for=\""+childMap.get("fieldName")+"\">"+childMap.get("fieldLabel")+" ("+childMap.get("fieldType")+")</label>");
					form.append("</div></div>");
					if(childMap.get("filter") != null && childMap.get("filter").equalsIgnoreCase("y")) {
						if(childMap.get("range1") != null && childMap.get("range1").length()>0) {
							aggs.append(aggsCommoa+"\\\\\\\""+childMap.get("fieldLabel")+"\\\\\\\": {\\\\\\\"range\\\\\\\": {\\\\\\\"field\\\\\\\": \\\\\\\""+childMap.get("fieldName")+"\\\\\\\",\\\\\\\"ranges\\\\\\\" : [");
							aggs.append("{ \\\\\\\"to\\\\\\\" : "+childMap.get("range1")+" }");
							if(childMap.get("range2") != null && childMap.get("range2").length()>0) {
								aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range1")+", \\\\\\\"to\\\\\\\" : "+childMap.get("range2")+" }");
								if(childMap.get("range3") != null && childMap.get("range3").length()>0) {
									aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range2")+", \\\\\\\"to\\\\\\\" : "+childMap.get("range3")+" }");
									if(childMap.get("range4") != null && childMap.get("range4").length()>0) {
										aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range3")+", \\\\\\\"to\\\\\\\" : "+childMap.get("range4")+" }");
									}else {
										aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range3")+" }]}}");
									}
								}else {
									aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range2")+" }]}}");
								}
							}else {
								aggs.append(",{ \\\\\\\"from\\\\\\\" : "+childMap.get("range1")+" }]}}");
							}
						}else {
							aggs.append(aggsCommoa+"\\\\\\\""+childMap.get("fieldLabel")+"\\\\\\\": {\\\\\\\"terms\\\\\\\": {\\\\\\\"field\\\\\\\": \\\\\\\""+childMap.get("fieldName")+"\\\\\\\",\\\\\\\"size\\\\\\\": 50}}");
						}
						if(aggsCommoa.length() <=15)
							aggsCommoa = ",";
					}
					if(commoa.length()==0)
						commoa = ",";
				}
			}
			
			
		}
		aggs.append("}\\\"\"");
		if(createForm !=null && createForm.equalsIgnoreCase("\"true\"")) {
			aggs.append(",\"form\":");
			Gson gson = new Gson();
			form.append("</div>");
			String fromStr = gson.toJson(form);
			aggs.append(fromStr);
			
			aggs.append("");
		}
		
		aggs.append("}");
		//Save in Map and ES
		sb.append("}}}");
		
		
		
		try {
			System.out.println(sb);
			if(createAggs !=null && createAggs.equalsIgnoreCase("\"true\""))
				ESClient.executePost(Constants.AGGREGATIONS_INDEX+"_doc/", aggs);
			if(createIndex !=null && createIndex.equalsIgnoreCase("\"true\"") ) {
				response = ESClient.executePut(indexName, sb.toString());
			}
			if(response == null)
				response = "Successful Execution";
			return response;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "{\"response\":\"Something went wrong, please connect with technical team.\"}";
	}

}
