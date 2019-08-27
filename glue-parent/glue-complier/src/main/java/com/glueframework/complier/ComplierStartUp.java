package com.glueframework.complier;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.glueframework.common.lang.ArrayUtils;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class ComplierStartUp {
	private static ILogger logger = LogMSG.getLogger();
	
	public static void run1(File[] src , String[] jar  , String [] target ) throws Exception{
		// 2次修改
		CompilerChangeMonitor changeMonitor = null;
		for (File directory : src) { 
			logger.debug("directory=[%s]" , directory.getAbsolutePath());
			changeMonitor = new CompilerChangeMonitor( directory );
			changeMonitor.setJar(jar);   
			changeMonitor.setTarget(target);
			changeMonitor.fileUpWather();        
		}
	}                                                   
	           
	public static void main(String[] args) throws Exception {     
		String userDir = new File("").getAbsolutePath();
		logger.debug(userDir);
		File file1 = FileUtils.getFile(userDir, "pom.xml"); 
				//new File("","pom.xml");
		if ( file1.exists() ){    
			file1 = FileUtils.getFile(userDir, "src" , "main" ,"java");
		}
		run1(new File[]{
				file1	
		}, ArrayUtils.EMPTY_STRING_ARRAY , ArrayUtils.EMPTY_STRING_ARRAY);
	}
}