package com.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.api.core.Constants;
import com.api.core.Executor;
import com.api.es.ESClient;
import com.google.gson.Gson;

@Path("/categories")
public class CategoryController {
	
	@POST @Path("/save") @Produces(MediaType.APPLICATION_JSON) @Consumes("application/json")
	public String createCategory(Map inputMap) {
		
		Executor exec = new Executor();
		exec.start(inputMap);
		
		
		try {
			exec.addStep(Constants.Steps.CREATE_CATEGORY_WITH_AGGREGATIONS.className());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String response =(String) exec.getFromContext(Constants.ContextHelper.RESPONSE.getId());
		exec.stop();
		return response;
		
	}
	
	
	
	@GET @Path("/get") @Produces(MediaType.APPLICATION_JSON)
	public String getCategory(@Context UriInfo uriInfo) {
		Map response = null;
		Executor exec = new Executor();
		exec.start(uriInfo);
		try {
			
			exec.addStep(Constants.Steps.FETCH_CATEGORY.className());
			response = (Map) exec.getFromContext(Constants.ContextHelper.ALL_CATEGORIES.getId());
			
			//responseList =  (List<String>) exec.getFromContext(Constants.ContextHelper.FORMATTED_SEARCH_RESPONSE.getId());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exec.stop();
		Gson gson = new Gson();
		System.out.println(response);
		System.out.println(gson.toJson(response));
		return gson.toJson(response);
		
	}

}
