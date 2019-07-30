package com.glueframework.complier;

import java.io.File;
import java.io.IOException;

public class ComplierStartUp {
	
	
	public static void run1(String[] src , String[] jar  , String [] target ) throws Exception{
		// 2次修改
		for (String pathStr : src) {
			CompilerChangeMonitor changeMonitor = new CompilerChangeMonitor( new File (pathStr) );
			changeMonitor.setJar(jar);
			changeMonitor.setTarget(target);
			changeMonitor.fileUpWather();
		}
	}
	
}