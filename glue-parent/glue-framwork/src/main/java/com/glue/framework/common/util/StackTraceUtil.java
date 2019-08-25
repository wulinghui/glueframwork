package com.glue.framework.common.util;


public class StackTraceUtil {

	public static StackTraceElement getUpper(String className) {
		// return getStackTraceElement((e,i,l) -> {
		// if( e.getClassName().equals(className) )
		// return i+1;
		// return -1;
		// } );
		return getStackTraceElement(new Filter() {
			boolean machFlag;
			@Override
			public int isTrue(StackTraceElement element, int index, int length) {
				if( element.getClassName().equals(className) ){
					machFlag = true;
				}else if( machFlag ){
					return index+1;
				}else{
					
				}
				return -1;
			}
		});
	}

	private static StackTraceElement getStackTraceElement(Filter filter) {
		StackTraceElement[] steArray = new Throwable().getStackTrace();
		StackTraceElement x = null;
		for (int i = 0; i < steArray.length; i++) {
			x = steArray[i];
			if (filter.isTrue(x, i, steArray.length) != -1)
				break;
		}
		return x;
	}

	private interface Filter {
		int isTrue(StackTraceElement element, int index, int length);
	}
}
