package com.glueframework.common.lang;


public abstract class Constant {
	public final static String environment = SystemUtil.getAndClearProperty("com.glueframework.environment", "dev");
	
	
	/**是否是开发环境。
	 * @return
	 */
	public static boolean isDevEnvironment() {
		return "dev".equals(environment);
	}
}
