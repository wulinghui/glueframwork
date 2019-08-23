package com.glueframework.log;

import org.slf4j.Logger;

/**
 * @author wulinghui
 * 基础类，适配slf4j类。只多了一个return 判断是否输出。
 * 如果没有输出的话，flase。
 */
public class LogBase implements ILogger {
	private  Logger log ;
	public LogBase(Logger log) {
		this.log = log;
	}
	

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#debug(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean debug(String arg0, Object... arg1) {
		log.debug(arg0, arg1);
		return isDebugEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#debug(java.lang.String, java.lang.Object)
	 */

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#debug(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public boolean debug(String arg0, Throwable arg1) {
		log.debug(arg0, arg1);
		return isDebugEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#debug(java.lang.String)
	 */
	@Override
	public boolean debug(String arg0) {
		log.debug(arg0);
		return isDebugEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#error(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean error(String arg0, Object... arg1) {
		log.error(arg0, arg1);
		return isErrorEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#error(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public boolean error(String arg0, Throwable arg1) {
		log.error(arg0, arg1);
		return isErrorEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#error(java.lang.String)
	 */
	@Override
	public boolean error(String arg0) {
		log.error(arg0);
		return isErrorEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#getName()
	 */
	@Override
	public String getName() {
		return log.getName();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#info(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean info(String arg0, Object... arg1) {
		log.info(arg0, arg1);
		return isInfoEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#info(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public boolean info(String arg0, Throwable arg1) {
		log.info(arg0, arg1);
		return isInfoEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#info(java.lang.String)
	 */
	@Override
	public boolean info(String arg0) {
		log.info(arg0);
		return isInfoEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#isDebugEnabled()
	 */
	@Override
	public boolean isDebugEnabled() {
		return log.isDebugEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#isErrorEnabled()
	 */
	@Override
	public boolean isErrorEnabled() {
		return log.isErrorEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#isInfoEnabled()
	 */
	@Override
	public boolean isInfoEnabled() {
		return log.isInfoEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#isTraceEnabled()
	 */
	@Override
	public boolean isTraceEnabled() {
		return log.isTraceEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#isWarnEnabled()
	 */
	@Override
	public boolean isWarnEnabled() {
		return log.isWarnEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#trace(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean trace(String arg0, Object... arg1) {
		log.trace(arg0, arg1);
		return isTraceEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#trace(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public boolean trace(String arg0, Throwable arg1) {
		log.trace(arg0, arg1);
		return isTraceEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#trace(java.lang.String)
	 */
	@Override
	public boolean trace(String arg0) {
		log.trace(arg0);
		return isTraceEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#warn(java.lang.String, java.lang.Object)
	 */
	@Override
	public boolean warn(String arg0, Object... arg1) {
		log.warn(arg0, arg1);
		return isWarnEnabled();
	}


	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#warn(java.lang.String, java.lang.Throwable)
	 */
	@Override
	public boolean warn(String arg0, Throwable arg1) {
		log.warn(arg0, arg1);
		return isWarnEnabled();
	}

	/* (non-Javadoc)
	 * @see com.wlh.log.ILogger#warn(java.lang.String)
	 */
	@Override
	public boolean warn(String arg0) {
		log.warn(arg0);
		return isWarnEnabled();
	}


	@Override
	public boolean debug(Throwable arg1) {
		return debug("",arg1);
	}


	@Override
	public boolean error(Throwable arg1) {
		return error("",arg1);
	}


	@Override
	public boolean info(Throwable arg1) {
		return info("",arg1);
	}


	@Override
	public boolean trace(Throwable arg1) {
		return trace("",arg1);
	}


	@Override
	public boolean warn(Throwable arg1) {
		return warn("",arg1);
	}
    
}
