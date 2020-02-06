package com.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import com.api.core.Constants;
import com.api.core.Executor;
import com.google.gson.Gson;


@Path("/search")
public class SearchController {
	
	@GET @Path("/hello") @Produces("application/json")
	public String getHello() {
		
		
		return "<h3>His</h3>\\u003ch3\\u003eHeading 1\\u003c/h3\\u003e";
	}
	
	@GET @Path("/q") @Produces("application/json")
	public String search(@Context UriInfo uriInfo) {
		List<String> responseList = null;
		Executor exec = new Executor();
		exec.start(uriInfo);
		try {
			
			exec.addStep(Constants.Steps.SEARCH_BUILD_QUERY.className());
			exec.addStep(Constants.Steps.FORMAT_SEARCH_RESPONSE.className());
			responseList =  (List<String>) exec.getFromContext(Constants.ContextHelper.FORMATTED_SEARCH_RESPONSE.getId());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		exec.stop();
		Gson gson = new Gson();
		
		return gson.toJson(responseList);
		
	}

}
