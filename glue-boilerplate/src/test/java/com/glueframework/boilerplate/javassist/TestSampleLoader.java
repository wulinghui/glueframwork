package com.glueframework.boilerplate.javassist;

import java.lang.reflect.Method;

import org.junit.Test;

public class TestSampleLoader {
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
}
