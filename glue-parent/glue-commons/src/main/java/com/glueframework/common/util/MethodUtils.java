package com.glueframework.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.glueframework.common.exception.ConvertRunException;


public class MethodUtils extends org.apache.commons.lang3.reflect.MethodUtils {
	public static Object invokeMethod(
            Method method,
            final Object object,
            Object... args){
		try {
			return method.invoke(object, args);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new ConvertRunException(e);
		}
	}
}
