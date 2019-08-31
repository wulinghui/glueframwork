package com.glueframework.confinger;

import java.util.Collection;

import org.apache.commons.configuration2.convert.ConversionHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;

public class ConversionBeanHandler implements ConversionHandler{

	
	private ConversionHandler conversionHandler;
	
	public ConversionBeanHandler(ConversionHandler conversionHandler) {
		super();
		this.conversionHandler = conversionHandler;
	}

	@Override
	public <T> void toCollection(Object src, Class<T> elemClass,
			ConfigurationInterpolator ci, Collection<T> dest) {
		try {
			conversionHandler.toCollection(src, elemClass, ci, dest);
		} catch (Exception e) {
			if( src  instanceof ConfigerBeanSuper ){
				ConfigerBeanSuper bean =  (ConfigerBeanSuper) src;
				conversionHandler.toCollection(bean.get_value_(), elemClass, ci, dest);
			}
		}
	}
	
	@Override
	public Object toArray(Object src, Class<?> elemClass,
			ConfigurationInterpolator ci) {
		Object array = null;
		try {
			array = conversionHandler.toArray(src, elemClass, ci);
		} catch (Exception e) {
			if( src  instanceof ConfigerBeanSuper ){
				ConfigerBeanSuper bean = (ConfigerBeanSuper) src;
				array = conversionHandler.toArray(bean.get_value_(),
						elemClass, ci);
			}
		}
		return array;
	}
	
	@Override
	public <T> T to(Object src, Class<T> targetCls, ConfigurationInterpolator ci) {
		T array = null;
		try {
			array = conversionHandler.to(src,targetCls, ci);
		} catch (Exception e) {
			if( src  instanceof ConfigerBeanSuper ){
				ConfigerBeanSuper bean = (ConfigerBeanSuper) src;
				array = conversionHandler.to(bean.get_value_(),targetCls,
						ci);
			}
		}
		return array;
	}

}
