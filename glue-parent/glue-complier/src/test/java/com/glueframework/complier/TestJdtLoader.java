package com.glueframework.complier;

import org.junit.Test;

import com.glueframework.boilerplate.common.JdCoreUtil;
import com.glueframework.common.util.CombinationClassLoader;

public class TestJdtLoader{

	@Test
	public void test() throws Throwable {
		JdtLoader jdtLoader = JdtLoader.getInstance();
		CombinationClassLoader combinationClassLoader = new CombinationClassLoader(jdtLoader,Thread.currentThread().getContextClassLoader());
		Class loadClass = combinationClassLoader.loadClass("org.hamcrest.BaseDescription");
		System.out.println(loadClass); 
	}
	@Test
	public void test111() throws Throwable {
		String javaCodes = JdCoreUtil.decompile("com.glueframework.complier.InitTable".replace(".", "/"));
		System.out.println(javaCodes);  
	}
	@Test
	public void test222() throws Throwable {
		JdtLoader jdtLoader = JdtLoader.getInstance();
		Class loadClass = jdtLoader.loadClass("com.glueframework.complier.InitTable");
		System.out.println(loadClass); 
	}  
}
