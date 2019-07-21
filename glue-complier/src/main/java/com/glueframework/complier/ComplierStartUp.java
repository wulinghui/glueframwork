package com.glueframework.complier;

import java.io.IOException;

import com.glueframework.boilerplate.common.IJdtConvert;

public class ComplierStartUp {
	
	
	public static void run(String[] src , String[] jar  , String [] target ) throws IOException{
		// 2次修改
		for (String pathStr : src) {
			CompilerChangeMonitor changeMonitor = new CompilerChangeMonitor(pathStr, IJdtConvert.DEFAULT );
			changeMonitor.setJar(jar);
			changeMonitor.setTarget(target);
			changeMonitor.fileUpWather();
		}
	}
}