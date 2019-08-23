package com.glueframework.confinger;

public class ConfigerHandle {
	
	public Object to(String value){
		return value;
	}
	
	public String add(Object beanSuper){
		return beanSuper.toString();
	}
	
	public void delete(String key){}
}
