package com.glueframework.common.lang;

import org.apache.commons.configuration2.SystemConfiguration;

public class SystemOneConfiguration extends SystemConfiguration {
	public static final SystemOneConfiguration SINGLE = new SystemOneConfiguration();
	protected SystemOneConfiguration() {
		
	}
	@Override
	protected Object getPropertyInternal(String key) {
		Object propertyInternal = super.getPropertyInternal(key);
		super.clearProperty(key);
		return propertyInternal;
	}

    /**通过类名获得。
     * @param key
     * @return
     */
    public String getString(Class<?> key,String def)
    {
        return getString(key.getName() , def);
    }
}
