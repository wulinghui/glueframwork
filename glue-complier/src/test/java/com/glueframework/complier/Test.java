package com.glueframework.complier;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.reflect.MethodUtils;

import com.glueframework.common.lang.ConstructorUtils;

public class Test {
private Integer id;
private String name;

/**
 * @param id
 */
public Test(Integer id) {
	super();
	this.id = id;
}

/**
 * @param id
 * @param name
 */
public Test(Integer id, String name) {
	super();
	this.id = id;
	this.name = name;
}

/**
 * 
 */
public Test() {
	super();
}

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
	public static void main(String[] args) throws Throwable, IllegalAccessException, InvocationTargetException {
		String className = "com.glueframework.complier.Test";
		Test invokeConstructor = (Test) ConstructorUtils.invokeConstructor(className, 4 , "wode");
		System.out.println(invokeConstructor);
		invokeConstructor = (Test) ConstructorUtils.invokeConstructor(className, 1);
		System.out.println(invokeConstructor);
		
//		invokeConstructor.setId(5555);
		//
		String methodName = "setId";
		    
		System.out.println(invokeConstructor.getId());  
		Object invokeExactMethod = MethodUtils.invokeExactMethod(invokeConstructor, methodName , 4);
		System.out.println(invokeExactMethod);
		System.out.println(invokeConstructor.getId());  
		
	}

	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + "]";
	}
	
}
