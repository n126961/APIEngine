package com.api.steps;

import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;
import com.api.es.ESClient;
import com.api.model.business.CustomerProfile;
import com.google.gson.Gson;

public class CreateCustomerStep implements ExecutionSteps{

	@Override
	public void execute(Context context) throws Exception {
		CustomerProfile userProfile = (CustomerProfile)context.get(Constants.ContextHelper.CUSTOMER_PROFILE.getId());
		String response = createCustomerProfile(userProfile);
		context.put(Constants.ContextHelper.RESPONSE.getId(), response);
		
	}

	private String createCustomerProfile(CustomerProfile userProfile) throws Exception {
		Gson gson = new Gson();
		
		return ESClient.executePut(Constants.CUSTOMER_INDEX+"_doc/", gson.toJson(userProfile));
	}

}
