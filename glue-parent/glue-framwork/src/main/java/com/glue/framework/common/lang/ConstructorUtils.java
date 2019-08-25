package com.glue.framework.common.lang;


import org.apache.commons.lang3.ClassUtils;

import com.glue.framework.common.exception.ConvertRunException;

public class ConstructorUtils extends org.apache.commons.lang3.reflect.ConstructorUtils{
	
	public static Object invokeConstructor(String className, Object... args) {
		Object handle = null;
		try {
			handle = invokeConstructor(ClassUtils.getClass(className, false),
					args);
		} catch (Exception e) {
			throw new ConvertRunException(e);
		}
		return handle;
	}
	public static Object newInstanceFromSytem( String key ,Class defaultValue  ){
		// "com.glueframework.commons.DBTools"
		Class orDefault = (Class) System.getProperties()
				.getOrDefault( key, defaultValue );
		try {
			return org.apache.commons.lang3.reflect.ConstructorUtils.invokeExactConstructor(orDefault);
		} catch (Exception e) {
			throw new ConvertRunException(e);
		}
	}
	
	
}
