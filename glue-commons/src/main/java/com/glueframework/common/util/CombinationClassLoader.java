package com.glueframework.common.util;

import com.glueframework.common.lang.ArrayUtils;


public class CombinationClassLoader  extends ClassLoader{
	ClassLoader [] args;

	public CombinationClassLoader(ClassLoader... args) {
		super();
		if( ArrayUtils.size(args) > 0 ) 
			this.args = args;
	}

	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> cla = null;
		for (ClassLoader element : args) {
			try {
				cla = element.loadClass(name);
				if( cla == null) 
					return cla;
			} catch (Exception e) {
				
			}
		}
		return super.loadClass(name);
	}
	
}
