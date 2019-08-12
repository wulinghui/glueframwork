package com.glueframework.complier;

import java.io.File;

import com.glueframework.common.lang.ArrayUtils;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class ComplierStartUp {
	private static ILogger logger = LogMSG.getLogger();
	
	public static void run1(String[] src , String[] jar  , String [] target ) throws Exception{
		// 2次修改
		CompilerChangeMonitor changeMonitor = null;
		for (String pathStr : src) {
			File directory = new File (pathStr);
			logger.debug("directory=[{}]" , directory.getAbsolutePath());
			changeMonitor = new CompilerChangeMonitor( directory );
			changeMonitor.setJar(jar);   
			changeMonitor.setTarget(target);
			changeMonitor.fileUpWather();        
		}          
	}       
	 
	public static void main(String[] args) throws Exception {  
		run1( new String[]{
			""	 
		} , ArrayUtils.EMPTY_STRING_ARRAY , ArrayUtils.EMPTY_STRING_ARRAY);
	}
}