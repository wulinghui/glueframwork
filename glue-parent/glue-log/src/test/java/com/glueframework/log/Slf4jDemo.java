package com.glueframework.log;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * https://www.cnblogs.com/bwcode/p/7159624.html
 * @author Administrator
 *
 */
public class Slf4jDemo {
	public static void main(String[] args) {
		while(true){
			try {
				Thread.sleep(1000);
				System.out.println(11111);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static Logger log = LoggerFactory.getLogger(Slf4jDemo.class);
/*    public static void main() {
    	System.out.println(THREAD_LOCAL1 == THREAD_LOCAL);
     
    	log.info(10%10 + "");
        log.info("info321");
        log.error("error321");
        log.debug("debug123");
        log.trace("trace123");     
    }*/
    @Test
	public void run(){
		 log.debug("debug");
	        log.info("info");
	        log.error("error");
	}
    @Test
   	public void runLogUpLevelFactory(){   
       	LogUpLevelFactory factory = new LogUpLevelFactory();
       	ILogger newLogger = factory.newLogger();
       	newLogger.trace("wode %s","wlh"); 
       	// 提升等级
       	LogUpLevel.THREAD_LOCAL = new ThreadLocal<>();
       	LogUUID.THREAD_LOCAL = new ThreadLocal<>();
       	Map value = new HashMap<>();
       	value.put(LogUpLevel.FLAG, LogUpLevel.WARN);
       	value.put(LogUUID.FLAG, LogUpLevel.WARN + "\tuuid\t");
   		LogUpLevel.THREAD_LOCAL.set(value );
   		LogUUID.THREAD_LOCAL.set(value); 
       	newLogger.trace("wode %s","wlh");  
   	}
    @Test
   	public void runLogUpLevelFactoryByLogMSG(){   
    	ILogger newLogger = LogMSG.getLogger();
       	newLogger.debug(newLogger.getName()); 
       	newLogger.trace("wode %s","wlh"); 
       	// 提升等级
       	LogUpLevel.THREAD_LOCAL = new ThreadLocal<>();
       	LogUUID.THREAD_LOCAL = new ThreadLocal<>();
       	Map value = new HashMap<>();
       	value.put(LogUpLevel.FLAG, LogUpLevel.WARN);
       	value.put(LogUUID.FLAG, LogUpLevel.WARN + "\tuuid\t");
   		LogUpLevel.THREAD_LOCAL.set(value );
   		LogUUID.THREAD_LOCAL.set(value); 
       	newLogger.trace("wode %s","wlh");  
   	}
}	
