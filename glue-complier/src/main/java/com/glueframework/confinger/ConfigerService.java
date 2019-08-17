package com.glueframework.confinger;

import java.util.List;

import org.apache.commons.lang3.ClassUtils;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public final class ConfigerService {
	private static ILogger logger = LogMSG.getLogger();
	final ConfigerBean bean;
	public ConfigerBean getBean() {
		return bean;
	}
	/**
	 * @param bean
	 */
	public ConfigerService(ConfigerBean bean) {
		super();
		this.bean = bean;
	}

	public void add(String key , String value , ConfigerBeanSuper beanSuper){
		String flagClass = bean.getFlagClass();
		ConfigerHandle handle = getHandle(flagClass);
		handle.add(beanSuper);
	}
	
	public void delete(String key , ConfigerBeanSuper beanSuper){
		String flagClass = bean.getFlagClass();
		ConfigerHandle handle = getHandle(flagClass);
		handle.delete(beanSuper);
	}
	public void update(String key,String value , ConfigerBeanSuper beanSuper){
		String flagClass = bean.getFlagClass();
		ConfigerHandle handle = getHandle(flagClass);
		handle.update(beanSuper);
	}
	public ConfigerBean select(String key){
		return null;
	}
	public List<ConfigerBean> selectHistory(String key){
		return null;
	}
	protected ConfigerHandle getHandle(String flagClass) {
		ConfigerHandle handle = null;
		try {
			handle = (ConfigerHandle) ClassUtils.getClass(flagClass, false).newInstance();
			 handle.setService(this);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			logger.debug(e);
		}
		return handle;
	}
}
