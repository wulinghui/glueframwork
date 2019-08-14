package com.glueframework.boilerplate;



import java.io.File;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class TestConfig {
	private static ILogger logger = LogMSG.getLogger();
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
	            System.out.println(propConfig.getString("log4j.appender.CONSOLE.Target"));
	            //System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
	           // System.out.println(propConfig.getString("test"));
		}
		catch (ConfigurationException cex)
		{
		   logger.info("出错了");
		}
	}
	public static void main(String[] args) {
		new TestConfig().test();
	}
	
}
