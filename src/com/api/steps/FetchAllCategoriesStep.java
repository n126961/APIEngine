package com.api.steps;

import java.util.Map;

import com.api.cache.CategoryCache;
import com.api.core.Constants;
import com.api.core.Context;
import com.api.core.ExecutionSteps;

public class FetchAllCategoriesStep implements ExecutionSteps{

	@Override
	public void execute(Context context) throws Exception {
		/*UriInfo uriInfo = (UriInfo) context.get(Constants.ContextHelper.REQUEST_INPUT.getId());
		MultivaluedMap<String, String> paras = uriInfo.getQueryParameters();
		String category = paras.getFirst(Constants.SearchHelper.CATEGORY.queryParameter());
		if(category == null || category.isEmpty() || category.equalsIgnoreCase("all")) {*/
		Map<String, String> map = CategoryCache.getAllCategories();
		CategoryCache.printAllAggs();
		context.put(Constants.ContextHelper.ALL_CATEGORIES.getId(), map);
		//}
	}

}
