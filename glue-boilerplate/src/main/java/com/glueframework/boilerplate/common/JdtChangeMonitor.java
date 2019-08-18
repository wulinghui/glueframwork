package com.glueframework.boilerplate.common;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

import org.apache.commons.io.FileUtils;

public abstract class JdtChangeMonitor extends FileChangeMonitor {
	protected final IJdtConvert jdtConvert;
	 
	/**
	 * @param directory
	 * @param fileFilter
	 */
	public JdtChangeMonitor(File directory, FileFilter fileFilter , IJdtConvert jdtConvert) {
		super(directory, fileFilter);
		this.jdtConvert = jdtConvert;
	}

	/**
	 * @param directory
	 */
	public JdtChangeMonitor(File directory) {
		this(directory,null,IJdtConvert.DEFAULT);
	}

	@Override
	protected void doHandleFile(File file) {
		// TODO 2次修改文件,采用策略模式,封装一个数组。
		// 修改后的内容，怎么处理? 1. 替换 2.新文件保存 3. 内存保存
		// 先采用‘内存保存’
		super.logger.info("doHandleFile-path={}",file);
		try {
			  String srcInner =  FileUtils.readFileToString( file ,"UTF-8");
			  // 交给编译器处理，编译。
			  afterDo(file , srcInner);
		} catch (Exception e) {
			super.logger.info(e);
		}// jdtConvert.doHandle(file);
	}
	
	protected abstract void afterDo(File file , String srcInner);
	
}