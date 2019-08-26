package com.glueframework.log;


public class StackTraceUtil {

	public static StackTraceElement getUpperOfClassName(String className) {
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
	public static StackTraceElement getUpperOfPackage(String packageName) {
		return getStackTraceElement(new Filter() {
			boolean machFlag;
			@Override
			public int isTrue(StackTraceElement element, int index, int length) {
				if( element.getClassName().startsWith(packageName) ){
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
		Throwable throwable = new Throwable();
		StackTraceElement[] steArray = throwable.getStackTrace();
//		throwable.printStackTrace();
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
