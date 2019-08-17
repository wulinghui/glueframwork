package com.glueframework.complier;

import static org.junit.Assert.*;

import org.junit.Test;

import com.glueframework.common.util.CombinationClassLoader;

public class TestJdtLoader extends JdtLoader {

	@Test
	public void test() throws Throwable {
		JdtLoader jdtLoader = new JdtLoader();
		CombinationClassLoader combinationClassLoader = new CombinationClassLoader(jdtLoader,Thread.currentThread().getContextClassLoader());
		Class loadClass = combinationClassLoader.loadClass("org.hamcrest.BaseDescription");
		System.out.println(loadClass); 
	}

}
