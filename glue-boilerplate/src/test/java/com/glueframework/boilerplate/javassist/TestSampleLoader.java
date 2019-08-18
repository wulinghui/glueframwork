package com.glueframework.boilerplate.javassist;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

public class TestSampleLoader {
//	@Test
	@Before
	public void classBefor(){
		System.setProperty(SampleLoader.class.getName(), "true111111"); 
		SampleLoader.setDefaultContextClassLoaderOfCurrentThread();
	}
	@Test 
    public  void main() throws Throwable {        
//        SampleLoader s = new SampleLoader();
//        Class c = s.loadClass("MyApp");
//        c.getDeclaredMethod("main", new Class[] { String[].class })
//         .invoke(null, new Object[] { args });
		
        while (true) {   
        	ClassLoader loader = new SampleLoader(){};
        	Thread.sleep(6000);  
        	String name = "com.glueframework.boilerplate.javassist.Hello";
            Class<?> clazz =  
            		//Class.forName(name);  
					loader.loadClass(name);   
            Method method = clazz.getMethod("say");  
            System.out.println(method.invoke( clazz.newInstance()) );  
            // 每隔3秒钟重新加载  
            //  
//            new Hello().say();
        }
    }
	
	@Test 
    public  void main1() throws Throwable {       
		while (true) {  
			Hello hello = new Hello(); 
			hello.say();     
			Runtime.getRuntime().gc();     
            // 每隔3秒钟重新加载    
            Thread.sleep(6000);   
        }
	}
	public static void main(String[] args)  throws Throwable {  
		TestSampleLoader testSampleLoader = new TestSampleLoader();
		System.out.println( TestSampleLoader.class.getClassLoader() );
		testSampleLoader.classBefor();
		testSampleLoader.main(); 
	}
	public static void main3(String[] args)  throws Throwable {       
		while (true) {  
			Thread thread = new Thread(){
				@Override
				public void run() {
					
					ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
					Hello hello = new Hello(); 
					hello.say();     
					Class<?> clazz;
					try {
						clazz = contextClassLoader.loadClass("com.glueframework.boilerplate.javassist.Hello");
						Method method = clazz.getMethod("say");  
						method.invoke( clazz.newInstance()) ; 
						System.out.println(   contextClassLoader );
						method = null;
					} catch (Exception e) {
						e.printStackTrace();
					}  
					contextClassLoader = null;
					Thread.currentThread().setContextClassLoader(null);
					hello = null;
					clazz = null;
					Runtime.getRuntime().gc();      
				}  
			};
			Thread.sleep(10000);   
			thread.setContextClassLoader( new SampleLoader() );
			thread.start();
            // 每隔3秒钟重新加载    
        }
	}
	public static void main2(String[] args)  throws Throwable {       
		while (true) {  
			SampleLoader.setDefaultContextClassLoaderOfCurrentThread();
			Hello hello = new Hello(); 
			hello.say();     
			Runtime.getRuntime().gc();      
			//   
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
			Class<?> clazz = contextClassLoader.loadClass("com.glueframework.boilerplate.javassist.Hello");  
            Method method = clazz.getMethod("say");  
            method.invoke( clazz.newInstance()) ; 
            System.out.println(   contextClassLoader );
            // 每隔3秒钟重新加载    
            Thread.sleep(6000);   
                    
        }
	}
}
