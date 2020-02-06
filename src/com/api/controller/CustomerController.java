package com.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.api.core.Constants;
import com.api.core.Executor;
import com.api.model.business.CustomerProfile;

@Path("/customer")
public class CustomerController {
	
	@POST @Path("/validate") @Produces(MediaType.APPLICATION_JSON)
	public String validateCustomer(Map inputMap) throws Exception{
		Executor exec = new Executor();
		exec.start(inputMap);
		exec.addStep(Constants.Steps.VALIDATE_CUSTOMER_LOGIN.name());
		String response = (String) exec.getFromContext(Constants.ContextHelper.RESPONSE.getId());
		return response;
	}

	@POST @Path("/save") @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
	public String createCategory(CustomerProfile profile) {
		Executor exec = new Executor();
		exec.start(profile);
		try {
			exec.addStep(Constants.Steps.CREATE_CUSTOMER.name());
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		exec.stop();
		String response = (String) exec.getFromContext(Constants.ContextHelper.RESPONSE.getId());
		return response;
		
	}
}
