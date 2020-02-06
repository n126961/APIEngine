package com.api.steps;

import java.io.IOException;

import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;
import com.api.es.ESClient;
import com.api.model.business.CustomerProfile;
import com.google.gson.Gson;

public class ValidateCustomerStep implements ExecutionSteps{

	@Override
	public void execute(Context context) throws Exception {
		String userName = (String)context.get(Constants.ContextHelper.USERNAME.getId());
		String pass = (String)context.get(Constants.ContextHelper.USERNAME.getId());
		String response = validate(userName,pass);
		context.put(Constants.ContextHelper.RESPONSE.getId(), response);
	}

	private String validate(String userName, String pass) throws IOException {
		StringBuilder query = new StringBuilder("{\"query\": {\"bool\": {\"must\": [{ \"match\": { \"emailId\": \""+userName+"\" } }");
		query.append("]}}}");
		String response = ESClient.executePost(Constants.CUSTOMER_INDEX+"_search?filter_path=hits.hits.Customer.CustomerProfile", query);
		Gson gson = new Gson();
		CustomerProfile profile = gson.fromJson(response, CustomerProfile.class);
		if(profile == null) {
			profile = new CustomerProfile();
			profile.setMessage("Email ID not registered with us, Please SignUp to use the Email ID.");			
		}else if(profile.getPassowrd().equals(pass)) {
			profile.setMessage("Logged In.");
			
		}else {
			profile.setMessage("Invalid Credentials");
		}
		return gson.toJson(profile);
	}

}
