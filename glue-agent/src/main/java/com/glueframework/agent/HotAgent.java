package com.glueframework.agent;
import java.io.File;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.util.Set;
import java.util.Timer;
import java.util.TreeSet;
/**
 * @author Administrator
 * https://www.cnblogs.com/freemanabc/p/5618180.html
 * Tomcat热部署的实现原理
 * java –javaagent:agent.jar
 * 
 */
public  class  HotAgent {
 
    protected  static  Set<String>  clsnames=new TreeSet<String>();
 
    public  static  void  premain(String  agentArgs, Instrumentation  inst)  throws Exception {
        ClassFileTransformer  transformer =new ClassTransform(inst);
        inst.addTransformer(transformer);
        System.out.println("是否支持类的重定义："+inst.isRedefineClassesSupported());
        Timer  timer=new  Timer();
//        String path = inst.getClass().getClassLoader().getResource("").getPath();
        System.out.println(  "path===" + new File("").getAbsolutePath() );  
        timer.schedule(new ReloadTask(inst),2000,2000);  
    }
}
