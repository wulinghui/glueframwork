package com.glueframework.test.agent;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.javac.util.StringUtils;

import org.junit.Test;
/**
 * @author Administrator
 * https://www.cnblogs.com/stateis0/p/9062201.html
 * 探秘 Java 热部署三（Java agent agentmain
 */
public class JVMTIThread {
	public static void main(String[] args) throws IOException,
			AttachNotSupportedException, AgentLoadException,
			AgentInitializationException, InterruptedException {
		List<VirtualMachineDescriptor> list = VirtualMachine.list();
		for (VirtualMachineDescriptor vmd : list) {
			VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
			virtualMachine  
					.loadAgent(  
							getJar(jarFileName),
							"5");   
			System.out.println("ok");
			virtualMachine.detach();
		} 
		// 线程挂起。
		Thread currentThread = Thread.currentThread();
		currentThread.suspend();
	}  
	static String jarFileName = "glue-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
	@Test
	public void test(){
		String path = getJar(jarFileName);
		System.out.println(path); 
		Map<String, String> getenv = System.getenv();
		System.out.println(getenv);
	}
	
	public static String getJar(String suffix) {
		String path = System.getProperty("java.class.path");
		String[] split = path.split(";");
		for (String string : split) {
			if( string.endsWith(suffix) ){
				return string;
			}
		}
		return null;
	}

}
