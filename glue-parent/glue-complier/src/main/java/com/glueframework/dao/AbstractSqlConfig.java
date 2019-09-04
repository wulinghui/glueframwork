package com.glueframework.dao;

import java.util.Map;

import com.wlh.aop.factory.JavaUtilFactory;
import com.wlh.config.SystemConfig;
import com.wlh.config.WrapEntity;
import com.wlh.ioc.IocManage;
import com.wlh.util.MapUtils;

/**
 * @author wulinghui
 * * 这个类，在new的时候默认加载系统级的配置
 * setId的时候加载配置文件的配置
 */
public class AbstractSqlConfig implements SqlConfig {
	
//	private static final Map<String,Object> SYSTEM_MAP = MapUtils.invertMap(
//			SystemConfig.get().get(Map.class, SQL_DEFAULT_CONFIG)) ;
	private static final WrapEntity<Map<String,Object>> SYSTEM_MAP = WrapEntity.getWrapEntityBySystemConfig(SQL_DEFAULT_CONFIG);
	protected  Map<String,Object> config = IocManage.getBean(
			Map.class,JavaUtilFactory.SELECT_OF_METHOD);
	public AbstractSqlConfig(String id) {
		init(id);
	}
	protected void init(String id) {
		config.putAll(getSystemMap()); // 在new的时候默认加载系统级的配置
		setConfig( SQL , id);
		Map map = SystemConfig.get().get(Map.class, id); //加载配置文件的配置
		if( MapUtils.isNotEmpty(map)) config.putAll(map);
	}
	public Map<String,Object> getSystemMap(){
		return SYSTEM_MAP.getWrapObj();
	}
	@Override
	public SqlConfig setConfig(String key, Object value) {
		config.put(key, value);
		return this;
	}

	@Override
	public <T> T getConfig(String key) {
		return (T) config.get(key);
	}

}
