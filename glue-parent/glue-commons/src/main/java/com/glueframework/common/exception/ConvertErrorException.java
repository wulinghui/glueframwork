package com.glueframework.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class ConvertErrorException extends Error {
	
	Throwable throwable;

	public ConvertErrorException(Throwable throwable) {
		super();
		this.throwable = throwable;
	}

	public int hashCode() {
		return throwable.hashCode();
	}

	public boolean equals(Object obj) {
		return throwable.equals(obj);
	}

	public String getMessage() {
		return throwable.getMessage();
	}

	public String getLocalizedMessage() {
		return throwable.getLocalizedMessage();
	}

	public Throwable getCause() {
		return throwable.getCause();
	}
	@Override
	public Throwable initCause(Throwable cause) {
		return throwable.initCause(cause);
	}

	public String toString() {
		return throwable.toString();
	}

	public void printStackTrace() {
		throwable.printStackTrace();
	}

	public void printStackTrace(PrintStream s) {
		throwable.printStackTrace(s);
	}

	public void printStackTrace(PrintWriter s) {
		throwable.printStackTrace(s);
	}


	public StackTraceElement[] getStackTrace() {
		return throwable.getStackTrace();
	}

	public void setStackTrace(StackTraceElement[] stackTrace) {
		throwable.setStackTrace(stackTrace);
	}
	
}
