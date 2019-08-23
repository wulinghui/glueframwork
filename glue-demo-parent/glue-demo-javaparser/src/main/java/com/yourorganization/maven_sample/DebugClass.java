package com.yourorganization.maven_sample;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.ArrayUtils;


public class DebugClass {
	public static void printAllGetMethod(Object obj){
		printMethod(obj,(element) -> {
			String name = element.getName();
			return name.startsWith("get");});
	}
	public static void printAllMethod(Object obj){
		printMethod(obj,(element) -> {return true;});
	}
	/**无参而且不在Object里面。
	 * @param obj
	 */
	public static void printNoAgeAndNoObjectMethod(Object obj){
		printMethod(obj,(element) -> {
			return element.getParameterCount() ==0 && !ArrayUtils.contains(OBJECT_ARRAY, element.getName());});
	}
	public static void printNoAgeMethod(Object obj){
		printMethod(obj,(element) -> {
			return element.getParameterCount() ==0;});
	}
	public static void printgetClassMethod(Object obj){
		printOneMethod(obj,"getClass");
	}
	public static void printOneMethod(Object obj,final String one){
		printMethod(obj,getOne(one));
	}
	protected static FileterMethod getOne(final String one) {
		return (element) -> {return one.equals( element.getName());};
	}
	
	public static void printNoOneMethod(Object obj,final String one){
		printNoListMethod(obj , new String[]{one});
	}
	final static String[] OBJECT_ARRAY = new String[]{"notifyAll","finalize","wait","notify","clone","equals","hashCode","registerNatives"};
	public static void printNoObjectMethod(Object obj){
		printNoListMethod(obj , OBJECT_ARRAY);
	}
	/**排除某个些方法
	 * @param obj
	 * @param one
	 */
	public static void printNoListMethod(Object obj,final String[] array){
		printMethod(obj,getNoList(array));
	}
	protected static FileterMethod getNoList(final String[] array) {
		return (element) -> {
			return !ArrayUtils.contains(array, element.getName());
		};
	}
	
	private static void printMethod(Object obj,FileterMethod... fileterMethods){
		Class<? extends Object> class1 = obj.getClass();
		StringBuilder sb = new StringBuilder();
		boolean flag = false;
		for (Method element : class1.getMethods()) {
			for (FileterMethod fileterMethod : fileterMethods) {
				if( fileterMethod.access(element)){
					flag = true;
					break;
				}
			}
			sb.append(" printMethod start============= \n");
			if( flag ){
				String name = element.getName();
				try {
					Object invoke = element.invoke(obj);
					sb.append(String.format("methode=[%s] , value =[%s]\n", name ,invoke ));
					
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					sb.append(String.format("methode=[%s] , Exception =[%s]\n", name ,e.getMessage() ));
				}
			}
		}
		sb.append("=============printMethod end=============");
		System.out.println( sb  );
	}
	
	
	public static void main(String[] args) {
		printAllGetMethod(new DebugClass()); 
	}
	
	
	
	private static interface FileterMethod{
public abstract boolean access(Method element);
	}
}
