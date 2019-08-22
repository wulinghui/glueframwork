package com.glueframework.test.agent;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
/**
 * @author Administrator
 * https://www.cnblogs.com/stateis0/p/9062201.html
 * 探秘 Java 热部署三（Java agent agentmain
 */
public class JVMTIThread {
	public static void main(String[] args) throws IOException,
			AttachNotSupportedException, AgentLoadException,
			AgentInitializationException, InterruptedException {
		
		String loadAgentAge = "5";
		if(  args == null && args.length > 1){
			loadAgentAge = args[0];
		}
		reload(loadAgentAge);
		// 线程挂起。
		suspend();// agent
	}

	public static void reload(String loadAgentAge) throws AttachNotSupportedException,
			IOException, AgentLoadException, AgentInitializationException {
		List<VirtualMachineDescriptor> list = VirtualMachine.list();
		for (VirtualMachineDescriptor vmd : list) {
			VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
			String jar = getJar(jarFileName);
			virtualMachine  
					.loadAgent(  
							jar,
							loadAgentAge);   
			virtualMachine.detach();
			System.out.println(String.format("vmd threadId = [%s] , loadAgentAge =[%s]  \n jarFilePath = [%s]", vmd.id()  , loadAgentAge, jar));
		}
	}

	public static void suspend() {
		System.out.println("================Thread suspend yeld============");   
		Thread currentThread = Thread.currentThread();
		currentThread.suspend();
	}
	public static String jarFileName = "glue-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
	
	
	public static String getJar(String suffix) {
		String path = System.getProperty("java.class.path");
		System.out.println("java.class.path:");   
		System.out.println(path);   
		String[] split = path.split(";");
		for (String string : split) {
			if( string.endsWith(suffix) ){
				File file = new File(string);
				System.out.println( String.format("file is exists = [%s] \n  absolutePath = [%s]", file.exists() , file.getAbsolutePath() )  );
				// 必须是绝对路径。
				return file.getAbsolutePath();
			}
		}
		return null;
	}
	
//	@Test
//	public void test(){
//		String path = getJar(jarFileName);
//		System.out.println(path); 
//		Map<String, String> getenv = System.getenv();
//		System.out.println(getenv);
//	}
}
