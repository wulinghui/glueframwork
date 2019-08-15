package com.glueframework.test.agent;
 
/**
 * @author Administrator
 * https://www.cnblogs.com/stateis0/p/9062199.html
 * 探秘 Java 热部署二（Java agent premain）
 *  * Tomcat热部署的实现原理
 * java –javaagent:agent.jar
 * -javaagent:Z:\Virtual-Machines-Files-Writer\workpace\glueframwork1\glue-agent\target\glue-agent-0.0.1-SNAPSHOT-jar-with-dependencies.jar
 * eclipse -> window -> Preferences→Java→Installed JREs→选择有问题的JRE和编辑默认VM参数。
 */
public  class  Test {
    public  static  void  main(String[] args)throws  InterruptedException {
    	
    	Bean1  c1=new  Bean1();
       while(true){     
           c1.test1();
           Thread.sleep(5000);
       }
    }
}