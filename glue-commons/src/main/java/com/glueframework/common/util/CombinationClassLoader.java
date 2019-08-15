package com.glueframework.common.util;

import com.glueframework.common.lang.ArrayUtils;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;


public class CombinationClassLoader  extends ClassLoader{
	ClassLoader [] args;
	protected static ILogger logger = LogMSG.getLogger();
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
				logger.debug(e);  
			}
		}
		return super.loadClass(name);
	}
	
}
