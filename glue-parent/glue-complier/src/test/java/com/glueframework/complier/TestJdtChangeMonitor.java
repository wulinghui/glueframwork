package com.glueframework.complier;
import java.io.File;

import org.junit.After;
import org.junit.Test;

import com.glueframework.boilerplate.common.FileChangeMonitor;
import com.glueframework.boilerplate.common.JdtChangeMonitor;
//import com.glueframework.boilerplate.javassist.SampleLoader;
public class TestJdtChangeMonitor {
	@After
	public void sleep() throws InterruptedException{
		Thread.sleep(1000000);
	}
	@Test
	public void fileChangeMonitor() throws Exception{
		FileChangeMonitor monitor = new FileChangeMonitor(new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-complier")){};
		monitor.fileUpWather();       
	}   
	@Test   
	public void jdtChangeMonitor() throws Exception{
		JdtChangeMonitor monitor = new JdtChangeMonitor(new File("Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-complier")){
			@Override
			protected void afterDo(File file, String srcInner) {
				// TODO Auto-generated method stub
				
			}  
		};
		monitor.fileUpWather();    
	}
	
	@Test
	public void compilerChangeMonitor() throws Exception{
		//SampleLoader.setDefaultContextClassLoaderOfCurrentThread();
		CompilerChangeMonitor monitor = new CompilerChangeMonitor(new File(
				"Z:\\Virtual-Machines-Files-Writer\\workpace\\glueframwork1\\glue-complier\\src\\test\\java"));
		monitor.fileUpWather(); 
		while(true){
			Thread.sleep(5000);  
			new TestJdtConvertDefault().hello();     
		}    
	}  
	
}
   