package com.glueframework.common.lang;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.configuration2.convert.ConversionHandler;

public class CombinedConfiguration extends AbstractConfiguration {
	
	AbstractConfiguration [] configurations = null;
	
	
	/**
	 * @param configurations
	 */
	public CombinedConfiguration(AbstractConfiguration... configurations) {
		super();
		if( ArrayUtils.isEmpty(configurations) )
			throw new NullPointerException("configurations isEmpty  ");
		this.configurations = configurations;
		int length = configurations.length;
		final ConversionHandler [] conversionHandler = new ConversionHandler[length];
		for (int i = 0; i < length ; i++) {
			conversionHandler[i] = configurations[i].getConversionHandler();
		}
		this.setConversionHandler(new ConversionCombinedHandler(conversionHandler) );
	}
	
	
	@Override
	protected void addPropertyDirect(String key, Object value) {
		for (AbstractConfiguration element : configurations) {
			element.addProperty(key, value);
		}
	}

	@Override
	protected void clearPropertyDirect(String key) {
		for (AbstractConfiguration element : configurations) {
			element.clearProperty(key);
		}
	}

	@Override
	protected Iterator<String> getKeysInternal() {
		List<String> list =new ArrayList<>();
		for (AbstractConfiguration element : configurations) {
			CollectionUtils.addAll(list, element.getKeys());
		}
		return list.iterator();
	}

	@Override
	protected Object getPropertyInternal(String key) {
		Object property = null;
		for (AbstractConfiguration element : configurations) {
			property = element.getProperty(key);
			if( property != null) 
				return property;
		}
		return null;
	}

	@Override
	protected boolean isEmptyInternal() {
		for (AbstractConfiguration element : configurations) {
			// 不是空的。
			if( !element.isEmpty() )
				return false;
		}
		return true;
	}

	@Override
	protected boolean containsKeyInternal(String key) {
		for (AbstractConfiguration element : configurations) {
			// 不是空的。
			if( element.containsKey(key) )
				return true;
		}
		return false;
	}
	
}
