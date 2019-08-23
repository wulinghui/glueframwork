package com.glueframework.log;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wulinghui
 * 这个管理工厂，
 * 其实用户可以自己用个常量类来管理。
 * 这样子工厂是安全的。而这个管理是不安全的。
 */
@Deprecated
public abstract class LogMSG {
	//反射修改。
	static Map factoryMap = new HashMap();
	
	
	public static final String DEFAULT_NAME = "FACTORY-DEFAULT-NAME";
	static{
		factoryMap.put(DEFAULT_NAME, new LogUpLevelFactory(6));
	}
//	private Logs(){};
	public static ILogger getLogger(String factoryName) {
		return  ( (ILogFactory)factoryMap.get(factoryName) ) .newLogger();
	}
	public static ILogger getLogger() {
		return getLogger(DEFAULT_NAME) ;
	}
	public static void setFactory(String factoryName,ILogFactory factory) {
		factoryMap.put(factoryName, factory); 
	}
}
