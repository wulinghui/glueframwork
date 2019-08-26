package com.glueframework.log;


/**
 * @author wulinghui
 * 这个管理工厂，
 * 其实用户可以自己用个常量类来管理。
 * 这样子工厂是安全的。而这个管理是不安全的。
 */
@Deprecated
public abstract class LogMSG {
	public static ILogger getLogger() {
		return new LogUUID( new LogUpLevel(new LogBase()) );
	}
}
