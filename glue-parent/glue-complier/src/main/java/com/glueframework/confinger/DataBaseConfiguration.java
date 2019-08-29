package com.glueframework.confinger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.configuration2.AbstractConfiguration;
import org.apache.commons.configuration2.convert.ConversionHandler;
import org.apache.commons.configuration2.interpol.ConfigurationInterpolator;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.ObjectUtils;

import com.glueframework.common.lang.ArrayUtils;
import com.glueframework.common.lang.Constant;
import com.glueframework.common.lang.ConstructorUtils;
import com.glueframework.commons.DBTools;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

/**
 * @author Administrator
 * 
 */
public class DataBaseConfiguration extends AbstractConfiguration {
	private static final String SELECT_COUNT_1_FROM = "SELECT COUNT(1)";
	private static final ILogger logger = LogMSG.getLogger();
	public static final String[] WHERE_ARGS = new String[]{
			"_key_","_environment_","_groupId_","_artifactId_"
	};
	private static final DBTools dbTool = DBTools.getInstance();
	public final String environment ;//= Constant.environment;//SystemOneConfiguration.SINGLE.getString(DataBaseConfiguration.class);

	public final String groupId;
	
	public final String artifactId;
	public final QueryRunner queryRunner;
	public final Class< ? extends ConfigerBeanSuper> type;
	
	public DataBaseConfiguration( String groupId, String artifactId ) {
		this(groupId,artifactId, dbTool.getQueryRunner()  , ConfigerBeanSuper.class);
	}
	public DataBaseConfiguration( String groupId, String artifactId, Class< ? extends ConfigerBeanSuper> type ) {
		this(groupId,artifactId, dbTool.getQueryRunner()  , type);
	}
	/**
	 * @param environment
	 * @param groupId
	 * @param artifactId
	 * @param queryRunner
	 * @param type
	 */
	public DataBaseConfiguration(String environment, String groupId,
			String artifactId, QueryRunner queryRunner, Class< ? extends ConfigerBeanSuper> type) {
		super();
		this.environment = environment;
		this.groupId = groupId;
		this.artifactId = artifactId;
		this.queryRunner = queryRunner;
		this.type = type;
		final ConversionHandler conversionHandler = super.getConversionHandler();
		this.setConversionHandler(new ConversionHandler() {
			
			@Override
			public <T> void toCollection(Object src, Class<T> elemClass,
					ConfigurationInterpolator ci, Collection<T> dest) {
				try {
					conversionHandler.toCollection(src, elemClass, ci, dest);
				} catch (Exception e) {
					ConfigerBeanSuper bean =  (ConfigerBeanSuper) src;
					conversionHandler.toCollection(bean.get_value_(), elemClass, ci, dest);
				}
			}
			
			@Override
			public Object toArray(Object src, Class<?> elemClass,
					ConfigurationInterpolator ci) {
				Object array = null;
				try {
					array = conversionHandler.toArray(src, elemClass, ci);
				} catch (Exception e) {
					ConfigerBeanSuper bean = (ConfigerBeanSuper) src;
					array = conversionHandler.toArray(bean.get_value_(),
							elemClass, ci);
				}
				return array;
			}
			
			@Override
			public <T> T to(Object src, Class<T> targetCls, ConfigurationInterpolator ci) {
				T array = null;
				try {
					array = conversionHandler.to(src,targetCls, ci);
				} catch (Exception e) {
					ConfigerBeanSuper bean = (ConfigerBeanSuper) src;
					array = conversionHandler.to(bean.get_value_(),targetCls,
							 ci);
				}
				return array;
			}
		});
	}
	public DataBaseConfiguration( String groupId, String artifactId , QueryRunner queryRunner , Class< ? extends ConfigerBeanSuper> type) {
		this(Constant.ENVIRONMENT_VALUE,groupId,artifactId, queryRunner , type);
	}
	

	@Override
	public  void addPropertyDirect(String key, Object value) {
		if( value instanceof ConfigerBeanSuper) {
			if( !containsKeyInternal(key) ){
				// 覆盖传过来的主键。
				ConfigerBeanSuper bean =  (ConfigerBeanSuper) value;
				fillBean(bean);
				bean.set_key_(key);
				try {
					String value1 = ObjectUtils.defaultIfNull(dbTool.getValueByIntrospector("SELECT MAX(_version_)", bean.tableHistoryName(), WHERE_ARGS, bean), "0").toString();
					value1 = Integer.parseInt(value1) + 1 + "";
					bean.set_version_( value1 );
					value1 = ObjectUtils.defaultIfNull(dbTool.getValueByIntrospector("SELECT MIN(CREATE_TIME)", bean.tableHistoryName(), WHERE_ARGS, bean), bean.getUpdate_Time()).toString();
					bean.setCreate_Time(value1);
					dbTool.insertByIntrospector(bean, bean.tableName());
				} catch (SQLException e) {
					logger.warn(e);
				}
			}
		}
	}
	protected void fillBean(ConfigerBeanSuper bean) {
		bean.set_artifactId_(artifactId);
		bean.set_environment_(environment);
		bean.set_groupId_(groupId);
	}

	@Override
	protected void clearPropertyDirect(String key) {
		try {
			ConfigerBeanSuper bean =  this.get(this.type, key);
			//	back
			String tableName = bean.tableName();
			dbTool.backupByIntrospector(bean, tableName, bean.tableHistoryName(), WHERE_ARGS);
			dbTool.deleteByIntrospector(bean, tableName, WHERE_ARGS);
		} catch (SQLException e) {
			logger.warn(e);
		}
	}

	@Override
	protected Iterator<String> getKeysInternal() {
		// select key from .. where ....;
		List<String> list1 = ListUtils.EMPTY_LIST;
		try {
			ConfigerBeanSuper bean =  ConstructorUtils.invokeConstructor(type);
			fillBean(bean);
			list1 = dbTool.getSetByIntrospector("SELECT "+WHERE_ARGS[0], bean.tableName(), ArrayUtils.subarray(WHERE_ARGS, 1, WHERE_ARGS.length), bean);
		} catch (Exception e) {
			logger.warn(e);
		}
		return list1.iterator();
	}


	@Override
	protected boolean isEmptyInternal() {
		String count = "0";
		try {
			ConfigerBeanSuper bean =  ConstructorUtils.invokeConstructor(type);
			fillBean(bean);
			count = dbTool.getValueByIntrospector(SELECT_COUNT_1_FROM, bean.tableName(), ArrayUtils.subarray(WHERE_ARGS, 1, WHERE_ARGS.length), bean).toString();
		} catch (Exception e) {
			logger.warn(e);
		}
		return Integer.parseInt(count) > 0;
	
	}

	@Override
	protected boolean containsKeyInternal(String key) {
		String count = "0";
		try {
			ConfigerBeanSuper bean =  ConstructorUtils.invokeConstructor(type);
			fillBean(bean);
			bean.set_key_(key);
			count = dbTool.getValueByIntrospector(SELECT_COUNT_1_FROM, bean.tableName(), WHERE_ARGS, bean).toString();
		} catch (Exception e) {
			logger.warn(e);
		}
		return Integer.parseInt(count) > 0;
	}
	
	
	@Override
	protected Object getPropertyInternal(String key) {
		//  sql   count(1) == 0
		ConfigerBeanSuper bean = null;
		try {
			bean =  ConstructorUtils.invokeConstructor(type);
			fillBean(bean);
			bean.set_key_(key);
			bean = dbTool.getBeanByIntrospector(bean.tableName(), WHERE_ARGS, bean, type);
		} catch (Exception e) {
			logger.warn(e);
		}
		return bean;
	}

	
	
	/**
	 * 测试通过即ok。
	 * @param args
	 */
	public static void main(String[] args) {
//		ArrayUtils.reverse(WHERE_ARGS, 1, WHERE_ARGS.length);
//		System.out.println(subarray.length); 
	}  
}
