package com.glueframework.log;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author wulinghui
 * 基础类，适配slf4j类。只多了一个return 判断是否输出。
 * 如果没有输出的话，flase。
 */
public class LogBase implements ILogger {
	
	private String name;
	private String SELF ;
	java.util.logging.Logger logger  = null;
	
	public LogBase() {
		this(  LogBase.class );
	}
	
	public java.util.logging.Logger getLogger() {
		return logger;
	}

	// LogUUID
	public LogBase( Class<?> nameClass ) {
		this.name = StackTraceUtil.getUpperOfPackage(nameClass.getPackage().getName()).getClassName();
		SELF = nameClass.getPackage().getName();
		logger = java.util.logging.Logger.getLogger(name);
	}
	
//	public void log(Level level, String msg , Object ... args) {
//		log(level, msg, null, args);
//	}
//	public void log(Level level, Throwable t , Object ... args) {
//		log(level, null, t, args);
//	} java.util.logging.SimpleFormatter
    private boolean log(String self,Level level, String msg, Throwable t , Object ... args) {
    	if (logger.isLoggable(level)) {
    		if( msg == null) msg = "";
    		if( args == null ) args = new Object[0];
    		// millis and thread are filled by the constructor
    		LogRecord record = new LogRecord(level, String.format(msg, args));
    		record.setLoggerName( name );
    		record.setThrown(t);
    		// Note: parameters in record are not set because SLF4J only
    		// supports a single formatting style
    		fillCallerData(self, record);
    		logger.log(record);
    		return true;
    	}
    	return false;
    }
    private void fillCallerData(String callerFQCN, LogRecord record) {
    	StackTraceElement upper = StackTraceUtil.getUpperOfPackage(SELF);
    	record.setSourceClassName(upper.getClassName());
    	record.setSourceMethodName(upper.getMethodName());
    	record.setSequenceNumber(upper.getLineNumber());
    }

    @Override
	public String getName() {
		return name;
	}

    
    /**
     * Is this logger instance enabled for the FINEST level?
     * 
     * @return True if this Logger is enabled for level FINEST, false otherwise.
     */
    public boolean isDebugEnabled() {
        return logger.isLoggable(Level.FINEST);
    }
	@Override
	public boolean debug(String arg0, Object... arg1) {
    	return log(SELF, Level.FINEST, arg0, null , arg1);
	}

	@Override
	public boolean debug(String arg0, Throwable arg1) {
		return log(SELF, Level.FINEST, arg0, arg1);
	}

	@Override
	public boolean debug(Throwable arg1) {
		return log(SELF, Level.FINEST, null, arg1);
	}

	@Override
	public boolean debug(String arg0) {
		return log(SELF, Level.FINEST, arg0 , null);
	}

	/**
     * Is this logger instance enabled for the FINEST level?
     * 
     * @return True if this Logger is enabled for level FINEST, false otherwise.
     */
    public boolean isErrorEnabled() {
        return logger.isLoggable(Level.FINEST);
    }
	@Override
	public boolean error(String arg0, Object... arg1) {
    	return log(SELF, Level.FINEST, arg0, null , arg1);
	}

	@Override
	public boolean error(String arg0, Throwable arg1) {
		return log(SELF, Level.FINEST, arg0, arg1);
	}

	@Override
	public boolean error(Throwable arg1) {
		return log(SELF, Level.FINEST, null, arg1);
	}

	@Override
	public boolean error(String arg0) {
		return log(SELF, Level.FINEST, arg0 , null);
	}

	/**
     * Is this logger instance enabled for the FINEST level?
     * 
     * @return True if this Logger is enabled for level FINEST, false otherwise.
     */
    public boolean isInfoEnabled() {
        return logger.isLoggable(Level.FINEST);
    }
	@Override
	public boolean info(String arg0, Object... arg1) {
    	return log(SELF, Level.FINEST, arg0, null , arg1);
	}

	@Override
	public boolean info(String arg0, Throwable arg1) {
		return log(SELF, Level.FINEST, arg0, arg1);
	}

	@Override
	public boolean info(Throwable arg1) {
		return log(SELF, Level.FINEST, null, arg1);
	}

	@Override
	public boolean info(String arg0) {
		return log(SELF, Level.FINEST, arg0 , null);
	}

	/**
     * Is this logger instance enabled for the FINEST level?
     * 
     * @return True if this Logger is enabled for level FINEST, false otherwise.
     */
    public boolean isTraceEnabled() {
        return logger.isLoggable(Level.FINEST);
    }
	@Override
	public boolean trace(String arg0, Object... arg1) {
    	return log(SELF, Level.FINEST, arg0, null , arg1);
	}

	@Override
	public boolean trace(String arg0, Throwable arg1) {
		return log(SELF, Level.FINEST, arg0, arg1);
	}

	@Override
	public boolean trace(Throwable arg1) {
		return log(SELF, Level.FINEST, null, arg1);
	}

	@Override
	public boolean trace(String arg0) {
		return log(SELF, Level.FINEST, arg0 , null);
	}

	/**
     * Is this logger instance enabled for the FINEST level?
     * 
     * @return True if this Logger is enabled for level FINEST, false otherwise.
     */
    public boolean isWarnEnabled() {
        return logger.isLoggable(Level.FINEST);
    }
	@Override
	public boolean warn(String arg0, Object... arg1) {
    	return log(SELF, Level.FINEST, arg0, null , arg1);
	}

	@Override
	public boolean warn(String arg0, Throwable arg1) {
		return log(SELF, Level.FINEST, arg0, arg1);
	}

	@Override
	public boolean warn(Throwable arg1) {
		return log(SELF, Level.FINEST, null, arg1);
	}

	@Override
	public boolean warn(String arg0) {
		return log(SELF, Level.FINEST, arg0 , null);
	}

 

}
