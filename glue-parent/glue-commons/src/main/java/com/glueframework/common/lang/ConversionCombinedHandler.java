package com.glueframework.common.lang;

import java.util.Collection;

import org.apache.commons.configuration2.convert.ConversionHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;

public class ConversionCombinedHandler implements ConversionHandler{

	
	private ConversionHandler[] conversionHandlers;
	
	public ConversionCombinedHandler(ConversionHandler... conversionHandler) {
		super();
		this.conversionHandlers = conversionHandler;
	}

	@Override
	public <T> void toCollection(Object src, Class<T> elemClass,
			ConfigurationInterpolator ci, Collection<T> dest) {
		for (ConversionHandler t : conversionHandlers) {
			try {
				t.toCollection(src, elemClass, ci, dest);
			} catch (Exception e) {
				
			}
		}
	}
	
	@Override
	public Object toArray(Object src, Class<?> elemClass,
			ConfigurationInterpolator ci) {
		Object obj = null;
		for (ConversionHandler t : conversionHandlers) {
			try {
				obj = t.toArray(src, elemClass, ci );
				break;
			} catch (Exception e) {
				
			}
		}
		return obj;
	}
	
	@Override
	public <T> T to(Object src, Class<T> targetCls, ConfigurationInterpolator ci) {
		T array = null;
		for (ConversionHandler t : conversionHandlers) {
			try {
				array = t.to(src, targetCls, ci);
				break;
			} catch (Exception e) {
				
			}
		}
		return array;
	}
}
