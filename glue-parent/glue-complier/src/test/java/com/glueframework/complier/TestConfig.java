package com.glueframework.complier;



import java.io.File;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.SystemConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.combined.CombinedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.Test;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class TestConfig {
	private static ILogger logger = LogMSG.getLogger();
	@Test
	public void test() {
Configurations configs = new Configurations();
		
		try
		{   
		    //Configuration config = configs.properties(new File("database.properties"));
		    // access configuration properties
		  //  String dbHost = config.getString("database.host");
		  //  String dbUser = config.getString("database.user");
		   // String dbPassword = config.getString("database.password", "secret");  // provide a default
		   // long dbTimeout = config.getLong("database.timeout");
		  //  logger.info(dbHost+"..."+dbPort+"..."+dbUser+".."+dbPassword);
			  
	            // setDefaultEncoding是个静态方法,用于设置指定类型(class)所有对象的编码方式。
	            // 本例中是PropertiesConfiguration,要在PropertiesConfiguration实例创建之前调用。
	            FileBasedConfigurationBuilder.setDefaultEncoding(PropertiesConfiguration.class, "UTF-8");
	            PropertiesConfiguration propConfig = configs.properties(this.getClass().getClassLoader().getResource("database.properties"));
	            System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
	            System.out.println(propConfig.getString("log4j.appender.CONSOLE.Target"));
	           // System.out.println(propConfig.getString("test"));
	            
//	            BeanUtils.populate(bean, map);
//	            ConvertUtils.
		}
		catch (ConfigurationException cex)
		{  
		   logger.info("出错了");
		}
	}
	@Test
	public  void main() throws Exception {
//		 Parameters params = new Parameters();
//		 CombinedConfigurationBuilder builder =
//		         new CombinedConfigurationBuilder().configure(params.fileBased()
//		                 .setFile(new File("config.properties")));
//		 PropertiesConfiguration config = builder.getConfiguration();
	}
	
}