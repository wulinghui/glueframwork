package com.glueframework.agent;
import java.io.File;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
/**
 * @author Administrator
 * https://www.cnblogs.com/freemanabc/p/5618180.html
 * Tomcat热部署的实现原理
 * java –javaagent:agent.jar
 * -javaagent:Z:\Virtual-Machines-Files-Writer\workpace\glueframwork1\glue-agent\target\glue-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 * eclipse -> window -> Preferences→Java→Installed JREs→选择有问题的JRE和编辑默认VM参数。
 */
public  class  HotAgent {
	public static boolean flag ;
    protected  static  Set<String>  clsnames=new TreeSet<String>();
//    protected  static  Map<String,Class>  CLS_NAME_MAP= new HashMap<String,Class>();
    protected  static  Set<String>  PATH_CLASS_SET=new TreeSet<String>();
    private static  Logger logger = Logger.getLogger(HotAgent.class.getName());
    
    protected  static Instrumentation  INST;
    public  static  void  premain(String  agentArgs, Instrumentation  inst)  {
    	   logger.finest("Agent premain called");
   	    logger.finest("agentArgs : " + agentArgs);
   	 flag = false;
    	load(agentArgs, inst);
    }
    public static void agentmain(String agentArgs, Instrumentation inst)
		      throws UnmodifiableClassException {
    	flag = true;
    	   logger.finest("Agent Main called");
    	    logger.finest("agentArgs : " + agentArgs);
    	load(agentArgs, inst);
    }
    
    
	protected static void load(String agentArgs, Instrumentation inst) {
		try {
			// 默认轮询间隔 3 秒 ， agentArgs也可以设置
			int duration = 3;
			if( agentArgs!=null && !agentArgs.isEmpty()){
				logger.finest("agentArgs set duration =" + agentArgs + " s");
				duration = Integer.parseInt(agentArgs);
			}
			long interval = TimeUnit.SECONDS.toMillis(duration);
//		Thread.sleep(interval);
			INST = inst;
			
//			ClassFileTransformer  transformer =new ClassTransform(inst);
//			inst.addTransformer(transformer);
			  
			logger.severe("是否支持类的重定义："+inst.isRedefineClassesSupported());
//        Timer  timer=new  Timer();
//        String path = inst.getClass().getClassLoader().getResource("").getPath();
//        logger.finest(  "path===" + new File("").getAbsolutePath() );  
//        timer.schedule(new ReloadTask(inst),2000,2000);  
			
			Class[]  classes=inst.getAllLoadedClasses();
			ClassLoader classLoader = null;
			// 获得所有监听的classpath路径。
			for(Class  cls:classes){
				classLoader  = cls.getClassLoader();
				if(classLoader==null||!classLoader.getClass().getName().equals("sun.misc.Launcher$AppClassLoader") 
						|| classLoader.getResource("") == null )
					continue;
//			CLS_NAME_MAP.put(cls.getName(), cls);
				String path = classLoader.getResource("").getPath();
				PATH_CLASS_SET.add(path);
			}
			logger.finest(  "PATH_CLASS_SET=="  + PATH_CLASS_SET);
			logger.finer(  "monitor CLASS PATH ="  + PATH_CLASS_SET);
//        logger.finest(  "CLS_NAME_MAP=="  + CLS_NAME_MAP);
			// 
			File directory = null;
			
			for (String path : PATH_CLASS_SET) {
				directory = new File(path);
				FileAlterationObserver observer = new FileAlterationObserver(directory);
				observer.addListener( new FileChangeMonitor(directory) );
				// 创建文件变化监听器
			    FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
			    // 开始监控
			    monitor.start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
