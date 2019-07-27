package com.glueframework.common.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DebugClass {
	public static void printAllGetMethod(Object obj){
		Class<? extends Object> class1 = obj.getClass();
		StringBuilder sb = new StringBuilder("\nprintAllGetMethod ============= \n");
		for (Method element : class1.getMethods()) {
			String name = element.getName();
			if( name.startsWith("get") ){
				try {
					Object invoke = element.invoke(obj);
					sb.append(String.format("methode=[%s] , value =[%s]\n", name ,invoke ));
					
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					sb.append(String.format("methode=[%s] , Exception =[%s]\n", name ,e.getMessage() ));
					e.printStackTrace();
				}
			}
		}
		System.out.println( sb  );
	}
	public static void printAllMethod(Object obj){
		Class<? extends Object> class1 = obj.getClass();
		StringBuilder sb = new StringBuilder("\nprintAllGetMethod ============= \n");
		for (Method element : class1.getMethods()) {
			String name = element.getName();
			if( true ){
				try {
					Object invoke = element.invoke(obj);
					sb.append(String.format("methode=[%s] , value =[%s]\n", name ,invoke ));
					
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					sb.append(String.format("methode=[%s] , Exception =[%s]\n", name ,e.getMessage() ));
					e.printStackTrace();
				}
			}
		}
		System.out.println( sb  );
	}
	public static void main(String[] args) {
		printAllGetMethod(new DebugClass()); 
	}
}
