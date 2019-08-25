package com.glueframework.log;

import java.util.logging.Level;
import java.util.logging.LogRecord;

/**
 * @author wulinghui
 * 基础类，适配slf4j类。只多了一个return 判断是否输出。
 * 如果没有输出的话，flase。
 */
public class LogBase implements ILogger {
	
	private String name;
	private String SUPER;
	private String SELF ;
	java.util.logging.Logger logger  = null;
	
	public LogBase() {
		this( LogBase.class );
	}
	
	public java.util.logging.Logger getLogger() {
		return logger;
	}

	public LogBase(String name) {
		this(name, LogBase.class);
	}
	
	public LogBase( Class<?> sELF) {
		this(  StackTraceUtil.getUpper(sELF.getName()).getClassName()  ,  sELF);
	}
	public LogBase(String name, Class<?> sELF) {
		this(name, Object.class, LogBase.class);
	}
	/**
	 * @param name
	 * @param sUPER
	 * @param sELF
	 */
	public LogBase(String name, Class<?> sUPER, Class<?> sELF) {
		this.name = name;
		SUPER = sUPER.getName();
		SELF = sELF.getName();
		logger = java.util.logging.Logger.getLogger(name);
	}
	
//	public void log(Level level, String msg , Object ... args) {
//		log(level, msg, null, args);
//	}
//	public void log(Level level, Throwable t , Object ... args) {
//		log(level, null, t, args);
//	}
    private boolean log(String self,Level level, String msg, Throwable t , Object ... args) {
    	if (logger.isLoggable(level)) {
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
        StackTraceElement[] steArray = new Throwable().getStackTrace();

        int selfIndex = -1;
        for (int i = 0; i < steArray.length; i++) {
            final String className = steArray[i].getClassName();
            if (className.equals(callerFQCN) || className.equals(SUPER)) {
                selfIndex = i;
                break;
            }
        }

        int found = -1;
        for (int i = selfIndex + 1; i < steArray.length; i++) {
            final String className = steArray[i].getClassName();
            if (!(className.equals(callerFQCN) || className.equals(SUPER))) {
                found = i;
                break;
            }
        }

        if (found != -1) {
            StackTraceElement ste = steArray[found];
            // setting the class name has the side effect of setting
            // the needToInferCaller variable to false.
            record.setSourceClassName(ste.getClassName());
            record.setSourceMethodName(ste.getMethodName());
        }
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
