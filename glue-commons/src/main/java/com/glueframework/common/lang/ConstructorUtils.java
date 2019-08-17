package com.glueframework.common.lang;


import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class ConstructorUtils extends org.apache.commons.lang3.reflect.ConstructorUtils{
	protected static ILogger logger = LogMSG.getLogger();
	
	public static Object newInstanceFromSytem( String key ,Class defaultValue  ){
		// "com.glueframework.commons.DBTools"
		Class orDefault = (Class) System.getProperties()
				.getOrDefault( key, defaultValue );
		try {
			return org.apache.commons.lang3.reflect.ConstructorUtils.invokeExactConstructor(orDefault);
		} catch (Exception e) {
			logger.debug(e);
			return null;
		}
	}
	
	
}
