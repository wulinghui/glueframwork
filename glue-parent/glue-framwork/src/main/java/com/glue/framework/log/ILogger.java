package com.glue.framework.log;

public interface ILogger {


	public abstract boolean debug(String arg0, Object... arg1);


	public abstract boolean debug(String arg0, Throwable arg1);
	
	public abstract boolean debug(Throwable arg1);
	
	public abstract boolean debug(String arg0);

	public abstract boolean error(String arg0, Object... arg1);

	public abstract boolean error(String arg0, Throwable arg1);
	public abstract boolean error(Throwable arg1);

	public abstract boolean error(String arg0);

	public abstract String getName();


	public abstract boolean info(String arg0, Object... arg1);


	public abstract boolean info(String arg0, Throwable arg1);
	public abstract boolean info( Throwable arg1);

	public abstract boolean info(String arg0);

	public abstract boolean isDebugEnabled();

	public abstract boolean isErrorEnabled();

	public abstract boolean isInfoEnabled();

	public abstract boolean isTraceEnabled();

	public abstract boolean isWarnEnabled();


	public abstract boolean trace(String arg0, Object... arg1);


	public abstract boolean trace(String arg0, Throwable arg1);
	public abstract boolean trace( Throwable arg1);

	public abstract boolean trace(String arg0);


	public abstract boolean warn(String arg0, Object... arg1);

	public abstract boolean warn(String arg0, Throwable arg1);
	public abstract boolean warn( Throwable arg1);

	public abstract boolean warn(String arg0);

}