package com.glueframework.confinger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.lang3.ClassUtils;

import com.glueframework.common.lang.Constant;
import com.glueframework.commons.DBTools;
import com.glueframework.commons.DateTools;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class DataBaseConfiguration extends AbstractConfiguration {
	private static ILogger logger = LogMSG.getLogger();
	public final String environment ;//= Constant.environment;//SystemOneConfiguration.SINGLE.getString(DataBaseConfiguration.class);

	public final String groupId;
	
	public final String artifactId;
	
	/**
	 * @param environment
	 * @param groupId
	 * @param artifactId
	 */
	public DataBaseConfiguration(String environment, String groupId,
			String artifactId) {
		super();
		this.environment = environment;
		this.groupId = groupId;
		this.artifactId = artifactId;
	}


	public DataBaseConfiguration( String groupId, String artifactId) {
		this(Constant.ENVIRONMENT_VALUE,groupId,artifactId);
	}
	

	@Override
	public  void addPropertyDirect(String key, Object value) {
		if( value instanceof ConfigerBean) {
			ConfigerBean bean = (ConfigerBean) value;
			if( ! containsKeyInternal(key) ){
				//  update  ...
				QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
				ConfigerHandle handle = getHandle(bean.getFlagClass());
				String inner = handle.add(bean.getInner());
				String createTime=bean.getCreateTime();
				String updateTime="";
				String flagValue=bean.getFlagValue();
				String version=bean.getVersion();
				String flagClass=    bean.getFlagClass();//"com.glueframework.confinger.ConfigerHandle";
				try { 
					updateTime = DateTools.date2Str(new Date(), DateTools.DATE_FORMAT_SEC);
					// version -- select history max()
					queryRunner.update("insert into GLUE_CONFIGER values(?,?,?,?,"
							+ "?,?,?,?,?,?)", key ,environment
							,groupId,artifactId,createTime,updateTime,flagClass,flagValue,version,inner);
					
				} catch (Exception e) {
					
					logger.debug(e);
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
		ConfigerBean bean = null;
		try {
		//	back
			queryRunner.update("insert into GLUE_CONFIGER_HISTORY select * from GLUE_CONFIGER where _key=?"
					+ " and _environment=? and _groupId=? and _artifactId=?",key,environment,groupId,artifactId);
			queryRunner.update("delete from GLUE_CONFIGER where _key=?"
			+ " and _environment=? and _groupId=? and _artifactId=?",key,environment,groupId,artifactId);
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
		String[] arraysStrings= {environment,groupId,artifactId}; 
		ResultSetHandler<ConfigerBean> hand=new BeanHandler<ConfigerBean>(ConfigerBean.class);
		try {
			bean=queryRunner.query("select * from GLUE_CONFIGER where _key=? and "
					+ " and _environment=? and _groupId=? and _artifactId=?", hand,arraysStrings);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	/**
	 * 测试通过即ok。
	 * @param args
	 */
	public static void main(String[] args) {
		DataBaseConfiguration configuration =
				new DataBaseConfiguration("com.glue", "test");
		String key = "shuzu";
		// add
		ConfigerBean bean = new ConfigerBean();
		String value = "123456";
		bean.setInner(value);
		configuration.addProperty(key, bean);
		// select
		int int1 = configuration.getInt(key);
		System.out.println("========"+int1);
		// clear
		configuration.clearProperty(key);
		// select
		int1 = configuration.getInt(key);
		System.out.println("========"+int1);
	}
}
