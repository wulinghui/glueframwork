package com.glueframework.log;

public class LoggerAdapt implements ILogger{
	private ILogger iLogger;
	public LoggerAdapt(ILogger iLogger) {
		this.iLogger = iLogger;
	}

	public boolean debug(String arg0, Object... arg1) {
		return iLogger.debug(arg0, arg1);
	}

	public boolean debug(String arg0, Throwable arg1) {
		return iLogger.debug(arg0, arg1);
	}

	public boolean debug(String arg0) {
		return iLogger.debug(arg0);
	}

	public boolean error(String arg0, Object... arg1) {
		return iLogger.error(arg0, arg1);
	}

	public boolean error(String arg0, Throwable arg1) {
		return iLogger.error(arg0, arg1);
	}

	public boolean error(String arg0) {
		return iLogger.error(arg0);
	}

	public String getName() {
		return iLogger.getName();
	}

	public boolean info(String arg0, Object... arg1) {
		return iLogger.info(arg0, arg1);
	}

	public boolean info(String arg0, Throwable arg1) {
		return iLogger.info(arg0, arg1);
	}

	public boolean info(String arg0) {
		return iLogger.info(arg0);
	}

	public boolean isDebugEnabled() {
		return iLogger.isDebugEnabled();
	}

	public boolean isErrorEnabled() {
		return iLogger.isErrorEnabled();
	}

	public boolean isInfoEnabled() {
		return iLogger.isInfoEnabled();
	}

	public boolean isTraceEnabled() {
		return iLogger.isTraceEnabled();
	}

	public boolean isWarnEnabled() {
		return iLogger.isWarnEnabled();
	}

	public boolean trace(String arg0, Object... arg1) {
		return iLogger.trace(arg0, arg1);
	}

	public boolean trace(String arg0, Throwable arg1) {
		return iLogger.trace(arg0, arg1);
	}

	public boolean trace(String arg0) {
		return iLogger.trace(arg0);
	}

	public boolean warn(String arg0, Object... arg1) {
		return iLogger.warn(arg0, arg1);
	}

	public boolean warn(String arg0, Throwable arg1) {
		return iLogger.warn(arg0, arg1);
	}

	public boolean warn(String arg0) {
		return iLogger.warn(arg0);
	}

	public boolean debug(Throwable arg1) {
		return iLogger.debug(arg1);
	}

	public boolean error(Throwable arg1) {
		return iLogger.error(arg1);
	}

	public boolean info(Throwable arg1) {
		return iLogger.info(arg1);
	}

	public boolean trace(Throwable arg1) {
		return iLogger.trace(arg1);
	}

	public boolean warn(Throwable arg1) {
		return iLogger.warn(arg1);
	}
	
}
