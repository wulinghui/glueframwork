package com.glueframework.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wulinghui
 * 实现的示例。
 */
public class LogUpLevelFactory implements ILogFactory {
	private final int who;
	
	public LogUpLevelFactory() {
		this(2);
	}
	public LogUpLevelFactory(int who) {
		super();
		this.who = who;
	}
	@Override
	public ILogger newLogger() {
		Logger logger = getLogger();
		return new LogUUID( new LogUpLevel(new LogBase(logger)) );
	}
	protected Logger getLogger() {
		return LoggerFactory.getLogger(getName());
	}
	protected String getName() {
		return Thread.currentThread().getStackTrace()[who].getClassName();
	}
//	public static void main(String[] args) {
//		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
//		for (StackTraceElement string : stackTrace) {
//			System.out.println(string);
//		}
//	}
}
