package com.glueframework.log;

import java.util.Map;

/**
 * @author wulinghui
 *	系统开关-支持长期存储
 *	为了方便ELK采集，所以需要写文件和库。
 *  这里采用linux权限的方式采用判断写几个日志
 *  应用场景
 *  1. 上线后追踪偶现的bug-如线程引起的。
 *  2. 存放重要的日志信息。
 */
public class LogStore extends LoggerAdapt {
	private ILogger iLoggerStore; 
	/**
	 * 1                  1 
	 * iLogger输出		iLoggerStore输出
	 */
	public static final String FLAG = "LogStore-FLAG";
	public static ThreadLocal<Map> THREAD_LOCAL;
	
	/**
	 * @param iLogger
	 * @param iLoggerStore  // 用于存储的Log，具体怎么存-自定义
	 */
	public LogStore(ILogger iLogger ,ILogger iLoggerStore) {
		super(iLogger); 
		this.iLoggerStore = iLoggerStore;
	}
	private int getFlag(){
		if( THREAD_LOCAL == null ) return -1;
		Map map = THREAD_LOCAL.get();
		if (map != null ){
			 String string = map.get(FLAG).toString();
			 try {
				return Integer.valueOf(string);
			} catch (NumberFormatException e) {
				return -1;
			}
		}else{
			return -1;
		}
	}
	public boolean debug(String arg0, Object... arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.debug(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.debug(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean debug(String arg0, Throwable arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.debug(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.debug(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean debug(String arg0) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.debug(arg0);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.debug(arg0);
		}
		return re1 || re2;
	}

	public boolean error(String arg0, Object... arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.error(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.error(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean error(String arg0, Throwable arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.error(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.error(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean error(String arg0) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.error(arg0);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.error(arg0);
		}
		return re1 || re2;
	}

	public boolean info(String arg0, Object... arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.info(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.info(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean info(String arg0, Throwable arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.info(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.info(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean info(String arg0) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.info(arg0);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.info(arg0);
		}
		return re1 || re2;
	}

	public boolean trace(String arg0, Object... arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.trace(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.trace(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean trace(String arg0, Throwable arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.trace(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.trace(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean trace(String arg0) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.trace(arg0);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.trace(arg0);
		}
		return re1 || re2;
	}

	public boolean warn(String arg0, Object... arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.warn(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.warn(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean warn(String arg0, Throwable arg1) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.warn(arg0, arg1);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.warn(arg0, arg1);
		}
		return re1 || re2;
	}

	public boolean warn(String arg0) {
		int flag2 = getFlag();
		boolean re1 = false;
		boolean re2 = false;
		if( flag2%10 == 1 ){
			re1 = super.warn(arg0);
		}
		if(  flag2/10 == 1  ){
			re2 = iLoggerStore.warn(arg0);
		}
		return re1 || re2;
	}
}
