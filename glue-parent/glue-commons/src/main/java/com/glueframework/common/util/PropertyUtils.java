package com.glueframework.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import com.glueframework.common.exception.ConvertRunException;

public class PropertyUtils extends org.apache.commons.beanutils.PropertyUtils {
	public static PropertyDescriptor[] getPropertyDescriptors(
			final Class<?> beanClass, final Class<?> stop) {
		PropertyDescriptor[] pds = null;
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(beanClass, stop);
			pds = beanInfo.getPropertyDescriptors();
		} catch (IntrospectionException e) {
			throw new ConvertRunException(e);
		}
		return pds;
	}
	public static PropertyDescriptor[] getPropertyDescriptors(
			final Object bean, final Class<?> stop) {
		return getPropertyDescriptors(bean.getClass(),stop);
	}
}
