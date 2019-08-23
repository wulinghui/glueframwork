package com.glueframework.log;

import java.util.Map;

/**
 * uuid在发送时就添加。可能跨服务和多线程。
 * @author wulinghui
 */
public class LogUUID extends LoggerAdapt {
	public static final String FLAG = "LogUUID-FLAG";
	public static ThreadLocal<Map> THREAD_LOCAL;
	
	public LogUUID(ILogger iLogger) { 
		super(iLogger);
	}
	private String getUUID(){  
		if(THREAD_LOCAL == null) return "";
		Map map = THREAD_LOCAL.get(); 
		String flag = "";
		if( map != null){
			flag = (String) map.get(FLAG);
		}
		return flag;
	}
	@Override
	public boolean debug(String arg0, Object... arg1) {
		return super.debug( getUUID() +arg0, arg1);
	}

	@Override
	public boolean debug(String arg0, Throwable arg1) {
		return super.debug( getUUID() +arg0, arg1);
	}

	@Override
	public boolean debug(String arg0) {
		return super.debug( getUUID() +arg0);
	}

	@Override
	public boolean error(String arg0, Object... arg1) {
		return super.error( getUUID() +arg0, arg1);
	}

	@Override
	public boolean error(String arg0, Throwable arg1) {
		return super.error( getUUID() +arg0, arg1);
	}

	@Override
	public boolean error(String arg0) {
		return super.error( getUUID() +arg0);
	}

	@Override
	public boolean info(String arg0, Object... arg1) {
		return super.info( getUUID() +arg0, arg1);
	}

	@Override
	public boolean info(String arg0, Throwable arg1) {
		return super.info( getUUID() +arg0, arg1);
	}

	@Override
	public boolean info(String arg0) {
		return super.info( getUUID() +arg0);
	}

	@Override
	public boolean trace(String arg0, Object... arg1) {
		return super.trace( getUUID() +arg0, arg1);
	}

	@Override
	public boolean trace(String arg0, Throwable arg1) {
		return super.trace( getUUID() +arg0, arg1);
	}

	@Override
	public boolean trace(String arg0) {
		return super.trace( getUUID() +arg0);
	}

	@Override
	public boolean warn(String arg0, Object... arg1) {
		return super.warn( getUUID() +arg0, arg1);
	}

	@Override
	public boolean warn(String arg0, Throwable arg1) {
		return super.warn( getUUID() +arg0, arg1);
	}

	@Override
	public boolean warn(String arg0) {
		return super.warn( getUUID() +arg0);
	}
	
}
