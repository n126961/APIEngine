package com.api.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.ws.rs.core.UriInfo;

public class Executor {
	
	private Context context=null;
	public Executor(){
		context = new Context();
	}

	public void start(Object obj) {
		if(obj != null) {
			context.put(Constants.ContextHelper.REQUEST_INPUT.getId(), obj);
		}
	}

	public void addStep(String stepName) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class targetClass = FrameworkCache.getClass(stepName);
		
		Method execute = targetClass.getDeclaredMethod("execute", new Class[]{Context.class});
		Object o = targetClass.newInstance();
		execute.invoke(o, context);
        
	}
	
	public Object getFromContext(Integer id) {
		return this.context.get(id);
	}

	public void stop() {
		context.clear();
		
	}

}
