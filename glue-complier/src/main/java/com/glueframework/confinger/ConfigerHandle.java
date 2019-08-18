package com.glueframework.confinger;

public class ConfigerHandle {
	ConfigerService service;
	
	public Object to(){
		service.getBean().getFlagValue();
		return null;
	}
	
	public void add(ConfigerBeanSuper beanSuper){
		
	}
	
	public void delete( ConfigerBeanSuper beanSuper){
		
	}
	public void update( ConfigerBeanSuper beanSuper){
		
	}
	
	
	public ConfigerService getService() {
		return service;
	}
	
	public void setService(ConfigerService service) {
		this.service = service;
	}
}
