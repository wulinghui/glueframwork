package com.glueframework.confinger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.ClassUtils;

import com.glueframework.commons.DBTools;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class DataBaseConfiguration extends AbstractConfiguration {
	private static ILogger logger = LogMSG.getLogger();
	public final static String environment = SystemOneConfiguration.SINGLE.getString(DataBaseConfiguration.class);

	public final String groupId;
	
	public final String artifactId;
	
	public DataBaseConfiguration( String groupId, String artifactId) {
		this.groupId = groupId;
		this.artifactId = artifactId;
	}

	@Override
	protected void addPropertyDirect(String key, Object value) {
		if( value instanceof ConfigerBean) {
			ConfigerBean bean = (ConfigerBean) value;
			if( ! containsKeyInternal(key) ){
				//  update  ...
				QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
				ConfigerHandle handle = getHandle(bean.getFlagClass());
				String inner = handle.add(bean.getInner());
				try { 
					// version -- select history max()
					queryRunner.execute("insert into GLUE_CONFIGER values(key , inner)", key ,inner);
					
				} catch (SQLException e) {
					
					e.printStackTrace();
				}
				// add sql 
//			ConfigerBean.TABLE_NAME;
//			queryRunner.execute(sql, params);
			}
		}
		// 先
		// 
	}

	@Override
	protected void clearPropertyDirect(String key) {

		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		
		//	back
		
		// select * from  .....   Inner
		ConfigerBean bean = null;
		// TODO Auto-generated method stub  
		// delete 
		try {
			queryRunner.execute("insert into name value(key , inner)", key ,bean.getInner().toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ConfigerHandle handle = getHandle(bean.getFlagClass());
		handle.delete(bean.getInner().toString());
	}

	@Override
	protected Iterator<String> getKeysInternal() {
		// TODO 
		return null;
	}

	

	@Override
	protected boolean isEmptyInternal() {
		//  sql   count(1) == 0
		return false;
	}

	@Override
	protected boolean containsKeyInternal(String key) {
		return false;
	}
	
	
	
	
	
	@Override
	protected Object getPropertyInternal(String key) {
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		//TODO  select * from GLUE_CONFIGER where key ......
		ConfigerBean bean = null;
		
		ConfigerHandle handle = getHandle(bean.getFlagClass());
		List list = new ArrayList<>();
		Object e = handle.to(bean.getInner().toString());
		return e;
	}

	
	
	protected ConfigerHandle getHandle(String flagClass) {
		ConfigerHandle handle = null;
		try {
			handle = (ConfigerHandle) ClassUtils.getClass(flagClass, false).newInstance();
//			handle.setService(this);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			logger.debug(e);
		}
		return handle;
	}

}
