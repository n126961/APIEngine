package com.api.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * <pre>
 *  String displayAs() default "text";
	boolean required() default false ;
	int minLength() default 0;
	int maxLength() default 8000;
	String dataType() default "String";
	boolean analyze() default false;
	</pre>
 * @author 10635582
 *
 */

@Target(ElementType.FIELD)
public @interface Field {

	public String displayAs() default "text";
	public boolean required() default false ;
	public int minLength() default 0;
	public int maxLength() default 8000;
	public String dataType() default "String";
	public boolean analyze() default false;
	
}
