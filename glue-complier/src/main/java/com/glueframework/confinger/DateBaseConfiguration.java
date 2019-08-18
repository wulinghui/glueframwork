package com.glueframework.confinger;

import java.util.Iterator;

import org.apache.commons.configuration2.AbstractConfiguration;

public class DateBaseConfiguration extends AbstractConfiguration {
	 
	ConfigerService configerService;
	
	@Override
	protected void addPropertyDirect(String key, Object value) {
		// TODO Auto-generated method stub
		configerService.add(key, value);
	}

	@Override
	protected void clearPropertyDirect(String key) {
		// TODO Auto-generated method stub
		throw new RuntimeException("no such clear");
	}

	@Override
	protected Iterator<String> getKeysInternal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object getPropertyInternal(String key) {
		return configerService.select(key);
	}

	@Override
	protected boolean isEmptyInternal() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean containsKeyInternal(String key) {
		return configerService.select(key) == null;
	}

}
