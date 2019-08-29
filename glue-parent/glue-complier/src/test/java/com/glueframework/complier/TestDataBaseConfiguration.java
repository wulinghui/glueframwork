package com.glueframework.complier;

import static org.junit.Assert.fail;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import org.junit.Test;

import com.glueframework.common.util.PropertyUtils;
import com.glueframework.commons.DBTools;
import com.glueframework.confinger.ConfigerBeanSuper;
import com.glueframework.confinger.DataBaseConfiguration;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class TestDataBaseConfiguration {
	private static ILogger logger = LogMSG.getLogger();
	DataBaseConfiguration configuration =
			new DataBaseConfiguration("com.glueframework","complier");
	ConfigerBeanSuper bean = new ConfigerBeanSuper();
	@Test
	public void testAddPropertyDirectStringObject() throws SQLException {
		bean.set_version_("111");
		bean.set_value_("111");
		String key = "wode"; 
		configuration.addProperty(key, bean);
		ConfigerBeanSuper configerBeanSuper = configuration.get(ConfigerBeanSuper.class, key);
		System.out.println( configerBeanSuper );
		System.out.println( configuration.getInt(key) );
		configuration.clearProperty(key);
		System.out.println( configuration.get(ConfigerBeanSuper.class, key)  );
		configerBeanSuper = DBTools.getInstance().getBeanByIntrospector(configerBeanSuper.tableHistoryName(), DataBaseConfiguration.WHERE_ARGS, configerBeanSuper, bean.getClass());
		System.out.println( configerBeanSuper );
	}

	@Test
	public void testClearPropertyDirectString() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetKeysInternal() {
		fail("Not yet implemented");
	}

	@Test
	public void testContainsKeyInternalString() throws SQLException {
		DBTools.getInstance().insertByIntrospector(bean, bean.tableName());
	}  

	@Test
	public void testGetPropertyInternalString() {
		
		bean.set_key_("111111");  
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean,Object.class);
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();
			try {
				logger.trace("name=[%s] value=[%s]" , name , propertyDescriptor.getReadMethod().invoke(bean));
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				logger.warn(e);
			}
		}
	}

}
