package com.glueframework.confinger;

import java.util.List;

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
		ConfigerBean select = select(key);
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
		// delete
		
		ConfigerHandle handle = getHandle();
		handle.update(beanSuper);
	}
	public ConfigerBean select(String key){
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		return null;
	}
	public List<ConfigerBean> selectHistory(String key){
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		return null;
	}
	protected void back(QueryRunner queryRunner) {
		// insert into  TABLE_NAME_HISTORY
	}
	
	
	protected ConfigerHandle getHandle() {
		ConfigerHandle handle = null;
		try {
			String flagClass = bean.getFlagClass();
			handle = (ConfigerHandle) ClassUtils.getClass(flagClass, false).newInstance();
			 handle.setService(this);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			logger.debug(e);
		}
		return handle;
	}
}