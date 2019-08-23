package com.glueframework.boilerplate.common;

import java.util.concurrent.TimeUnit;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.junit.Test;

/**
 * Title:文件监控之测试类 Created by xg.chen on 2016/9/8.
 */
public class WatchMainClass {
    /**
     * 文件监控之测试类
     * 
     * @throws Exception
     */
    private static void FileListenterClassTest() throws Exception {
        // 监控目录
        String rootDir = "Y://Virtual-Machines-Files-Writer//workpace//glueframwork//glue-boilerplate//src//main//java";
        // 轮询间隔 1 秒 
        long interval = TimeUnit.SECONDS.toMillis(1);  
        // 创建一个文件观察器用于处理文件的格式 
        FileAlterationObserver observer = new FileAlterationObserver(rootDir);
        // 设置文件变化监听器
        observer.addListener(new TestFileListener());  
        // 创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }

    /**
     * 测试模块
     * 
     * @param args
     */
    @Test
    public  void main0() throws Exception {
        new Thread(new Runnable() {
            @Override 
            public void run() {
                try {
                    System.out.println("-----init file-----");
                    FileListenterClassTest();
                } catch (Exception e) {  
                    System.err.print(e);
                }
            }
        }).start();
    }
    @Test
    public  void main1() throws Exception {
    	 FileListenterClassTest();
    }
    public static void main(String[] args) throws Exception {
//    	new WatchMainClass().main();
    	// 不用在单写线程。
    	new WatchMainClass().main1();
    	System.out.println("-----init file-----");
	} 
}