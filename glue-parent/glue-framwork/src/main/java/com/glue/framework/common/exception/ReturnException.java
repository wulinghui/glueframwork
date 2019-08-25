package com.glue.framework.common.exception;

public class ReturnException extends RuntimeException {

	Object object;

	public ReturnException(Object object) {
		super();
		this.object = object;
	}
	public <T> T getObject(){
		return  (T) object;
	}
}
