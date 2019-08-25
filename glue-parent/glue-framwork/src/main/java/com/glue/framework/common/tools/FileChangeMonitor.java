package com.glue.framework.common.tools;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import com.glue.framework.log.ILogger;
import com.glue.framework.log.LogMSG;

/**
 * 文件变更
 * <p>
 * Created by yihui on 2017/4/28.
 */
public abstract class FileChangeMonitor {
	protected static ILogger logger = LogMSG.getLogger();
	final File directory;
	final FileFilter fileFilter;
	
	/**
	 * @param directory
	 */
	public FileChangeMonitor(File directory) {
		super();
		this.directory = directory;
		this.fileFilter = null;
	}

	/**
	 * @param directory 监控目录
	 * @param fileFilter
	 */
	public FileChangeMonitor(File directory, FileFilter fileFilter) {
		super(); 
		this.directory = directory;    
		this.fileFilter = fileFilter;
	}


	public void fileUpWather() throws Exception {
		// 轮询间隔 1 秒 
        long interval = TimeUnit.SECONDS.toMillis(1);  
        // 创建一个文件观察器用于处理文件的格式 
        FileAlterationObserver observer = new FileAlterationObserver(directory,fileFilter);
        // 设置文件变化监听器
        observer.addListener(new FileAlterationListenerAdaptor(){

			@Override
			public void onDirectoryCreate(File directory) {
				doHandleDirectory(directory);
			}

			@Override
			public void onDirectoryChange(File directory) {
				doHandleDirectory(directory);
			}

			@Override
			public void onDirectoryDelete(File directory) {
				doHandleDirectory(directory);
			}

			@Override
			public void onFileCreate(File file) {
				doHandleFile(file);
			}

			@Override
			public void onFileChange(File file) {
				doHandleFile(file);
			}

			@Override
			public void onFileDelete(File file) {
				doHandleFile(file);
			}
        	
        });  
     // 创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
	}
	
	/**处理文件夹的变化。
	 * @param file
	 */
	protected  void doHandleDirectory(File file){
		logger.debug("文件夹发生变化,不做处理={}" , file);
	}
	/**处理文件的变化。   
	 * 子类不满足，重写该方法。
	 * @param kind
	 * @param fileName
	 */
	protected void doHandleFile(File file){
		logger.debug("文件发生变化={}" , file);
	}
}
