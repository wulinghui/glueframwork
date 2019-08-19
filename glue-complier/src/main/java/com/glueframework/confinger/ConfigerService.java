package com.glueframework.confinger;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.ClassUtils;

import com.glueframework.commons.DBTools;
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

	public void add(String key  , ConfigerBeanSuper beanSuper){
		// 先
		Object select = select(key);
		if(select == null ){
			//  update  ...
			QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
			// add sql 
//			ConfigerBean.TABLE_NAME;
//			queryRunner.execute(sql, params);
		}
		// 
		ConfigerHandle handle = getHandle();
		handle.add(beanSuper);
	}
	
	public void delete(String key , ConfigerBeanSuper beanSuper){
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		back(queryRunner);
		
		// delete 
		ConfigerHandle handle = getHandle();
		handle.delete(beanSuper);
	}
	public void update(String key , ConfigerBeanSuper beanSuper){
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		back(queryRunner);
		// update
		
		ConfigerHandle handle = getHandle();
		handle.update(beanSuper);
	}
	public Object select(String key){
		List<Object> selectHistory = selectHistory(key ,ConfigerBean.TABLE_NAME);
		return   CollectionUtils.isEmpty(selectHistory) ? null : selectHistory.get(0);
	}
	public List<Object> selectHistory(String key ){
		return selectHistory(key ,ConfigerBean.TABLE_NAME);
	}
	
	protected List<Object> selectHistory(String key , String tableName){
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		//TODO  select * from GLUE_CONFIGER where key ......
		ConfigerBean bean = null;
		
		ConfigerHandle handle = getHandle(bean);
		List list = new ArrayList<>();
		Object e = handle.to();
		list.add(e);
		return list;
	}
	protected void back(QueryRunner queryRunner) {
		// insert into  TABLE_NAME_HISTORY
	}
	
	
	protected ConfigerHandle getHandle() {
		return getHandle(bean);
	}
	protected ConfigerHandle getHandle(ConfigerBean bean) {
		ConfigerHandle handle = null;
		try {
			String flagClass = bean.getFlagClass();
			handle = (ConfigerHandle) ClassUtils.getClass(flagClass, false).newInstance();
//			handle.setService(this);
			handle.setService(new ConfigerService(bean));
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			logger.debug(e);
		}
		return handle;
	}
}
