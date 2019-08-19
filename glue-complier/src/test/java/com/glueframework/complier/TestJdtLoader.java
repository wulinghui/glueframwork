package com.glueframework.complier;

import org.junit.Test;

import com.glueframework.common.util.CombinationClassLoader;

public class TestJdtLoader{

	@Test
	public void test() throws Throwable {
		JdtLoader jdtLoader = JdtLoader.getInstance();
		CombinationClassLoader combinationClassLoader = new CombinationClassLoader(jdtLoader,Thread.currentThread().getContextClassLoader());
		Class loadClass = combinationClassLoader.loadClass("org.hamcrest.BaseDescription");
		System.out.println(loadClass); 
	}

}
