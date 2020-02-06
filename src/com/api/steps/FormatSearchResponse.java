package com.api.steps;

import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;
import com.api.model.es.ESSearchResponse;
import com.google.gson.Gson;

public class FormatSearchResponse implements ExecutionSteps {

	@Override
	public void execute(Context context) throws Exception {
		
		String response = (String) context.get(Constants.ContextHelper.SEARCH_RESULT_ES.getId());
		
		Gson gson = new Gson();
		ESSearchResponse esReponse = gson.fromJson(response, ESSearchResponse.class);
		
		context.put(Constants.ContextHelper.FORMATTED_SEARCH_RESPONSE.getId(), esReponse.getFormattedResponse());
	}

}
