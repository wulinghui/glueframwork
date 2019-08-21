package com.glueframework.common.lang;

import org.apache.commons.lang3.SystemUtils;

public class SystemUtil  extends SystemUtils {
	/**get and clear
	 * @param key
	 * @param def
	 * @return
	 */
	public static String getAndClearProperty(String key, String def) {
		String property = System.getProperty(key,def);
		System.clearProperty(key);
        return property;
    }
}
