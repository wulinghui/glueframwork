package com.glueframework.common.util;

import java.io.File;
import java.io.IOException;

public class PathUtil {
	public static String getClassPath() {
		return Thread.currentThread().getContextClassLoader().getResource("").getFile();//	/E:/workSpace/Util5/bin/test/
	}
	public static String getWorkPath() throws IOException {
		return new File("").getCanonicalPath();//	E:\workSpace\Util5
	}
	public static String getJarPath() {
		//E:\workSpace\Util5\bin;F:\xvxm\32\eclipse\plugins\org.junit_4.12.0.v201504281640\junit.jar;F:\xvxm\32\eclipse\plugins\org.hamcrest.core_1.3.0.v201303031735.jar;F:\xvxm\32\eclipse\configuration\org.eclipse.osgi\413\0\.cp;F:\xvxm\32\eclipse\configuration\org.eclipse.osgi\412\0\.cp
		return System.getProperty("java.class.path");
	}
}
