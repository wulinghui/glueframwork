package com.glueframework.boilerplate.javassist;

import java.lang.reflect.Method;

import org.junit.Test;

import com.glueframework.common.util.CombinationClassLoader;

public class TestSampleLoader {
	@Test
	public void classBefor(){
		SampleLoader.setDefaultContextClassLoaderOfCurrentThread();
	}
	static{
		SampleLoader.setDefaultContextClassLoaderOfCurrentThread();
	}
	@Test 
    public  void main() throws Throwable {        
//        SampleLoader s = new SampleLoader();
//        Class c = s.loadClass("MyApp");
//        c.getDeclaredMethod("main", new Class[] { String[].class })
//         .invoke(null, new Object[] { args });
        
        while (true) {  
        	SampleLoader loader = new SampleLoader();  
            Class<?> clazz = loader.loadClass("com.glueframework.boilerplate.javassist.Hello");  
            Method method = clazz.getMethod("say");  
            System.out.println(method.invoke( clazz.newInstance()) );  
            // 每隔3秒钟重新加载  
            Thread.sleep(6000);  
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
