package com.glueframework.boilerplate.common;

import java.nio.file.Path;
import java.nio.file.WatchEvent.Kind;

public abstract class JdtChangeMonitor extends FileChangeMonitor {
	final IJdtConvert jdtConvert;
	 String[] jar , target;
	 
	public JdtChangeMonitor(String pathStr ,IJdtConvert jdtConvert ) {
		super(pathStr);
		this.jdtConvert = jdtConvert;
	}

	@Override
	protected final void doHandle(Kind<?> kind, Path fileName) {
		// TODO 2次修改文件,采用策略模式,封装一个数组。
		// 修改后的内容，怎么处理? 1. 替换 2.新文件保存  3. 内存保存
		// 先采用‘内存保存’
		String srcInner = jdtConvert.doHandle(kind, fileName);
		// 交给编译器处理，编译。
		afterDo(srcInner);
	}
	
	protected abstract void afterDo(String srcInner);

	public void setJar(String[] jar) {
		this.jar = jar;
	}

	public void setTarget(String[] target) {
		this.target = target;
	}
	
}