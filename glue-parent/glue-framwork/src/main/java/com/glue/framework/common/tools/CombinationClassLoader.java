package com.glue.framework.common.tools;

import com.glue.framework.common.exception.ConvertRunException;
import com.glue.framework.common.lang.ArrayUtils;


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
			} catch (Throwable e) {
				throw new ConvertRunException(e);
			}
		}
		return super.loadClass(name);
	}
	
}
