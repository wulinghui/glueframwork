package com.glueframework.complier;



import java.io.File;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.apache.commons.configuration2.DatabaseConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.beanutils.BeanDeclaration;
import org.apache.commons.configuration2.beanutils.BeanHelper;
import org.apache.commons.configuration2.builder.BasicConfigurationBuilder;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.builder.fluent.DatabaseBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.builder.fluent.PropertiesBuilderParameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.junit.Test;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;
import com.glueframework.log.LogUtils;

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
//	            System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
//	            System.out.println(propConfig.getBoolean("log4j.appender.LOGFILE.Append"));
	            System.out.println(propConfig.getString("database"));
	           // System.out.println(propConfig.getString("test"));
	            
//	            BeanUtils.populate(bean, map);
//	            ConvertUtils.
		}
		catch (ConfigurationException cex)
		{  
		   logger.info("出错了");
		}
	}
	// 自己写的没有效果。
	@Test
	public  void properties() throws Exception {
		BasicConfigurationBuilder<PropertiesConfiguration> builder =
			     new BasicConfigurationBuilder<PropertiesConfiguration>(PropertiesConfiguration.class);
			 PropertiesBuilderParameters database = new Parameters().properties();
			 BeanHelper beanHelper = new BeanHelper();
			database.setBeanHelper(null);
//			beanHelper.createBean(null);                
			URL resource = this.getClass().getClassLoader().getResource("database.properties");
			System.out.println(resource);   
			builder.configure(database.setURL(resource).setFile(new File(resource.getFile() ) ) ) ;
			PropertiesConfiguration config = builder.getConfiguration();
			Iterator<String> keys = config.getKeys();
			while( keys.hasNext()){
				String next = keys.next();  
				System.out.println(next);  
			}
			LogUtils.debugArray(keys);
			 String value = config.getString("database");
			 System.out.println(value);  
	}
	
	// Commons-Configuration2简介、使用方式、代码范例 -- 自动重新加载配置文件、监听器、处理器、自定义检测器
	// https://blog.csdn.net/wanghantong/article/details/79072474
	@Test
	public  void propertiesDemo() throws Exception {
		 Parameters params = new Parameters();

	        // Read data from this file
		 URL resource = this.getClass().getClassLoader().getResource("database.properties");
		 
	        File propertiesFile = new File("commons.properties");
	        propertiesFile = new File(resource.getFile() );  
	        System.out.println(propertiesFile.getAbsolutePath());
	        System.out.println(propertiesFile.exists()); 
	        // 参数在这里初始化是不起作用的,或者说暂时还不会用,要想此处配置生效,要使用这个流式配置的返回值然后传入到configure()方法中
	        // params.fileBased()
	        //      .setFile(propertiesFile)
	        //      .setEncoding("UTF-8")
	        //      .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
	        //      .setThrowExceptionOnMissing(true);

	        // 详细参考BuilderParameters的子接口和实现类
	        // 用其返回值作为configure()方法的参数[使用FileBasedBuilderParameters]
	        FileBasedBuilderParameters fileBasedBuilderParameters = params.fileBased()
	                .setFile(propertiesFile)
	                .setEncoding("UTF-8")
	                .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
	                .setThrowExceptionOnMissing(true);
	        // org.apache.commons.configuration2.builder.BasicConfigurationBuilder.fetchBeanHelper()
	        // (helper != null) ? helper : BeanHelper.INSTANCE
	        fileBasedBuilderParameters.setBeanHelper(null);
	        
	        // 用其返回值作为configure()方法的参数[使用PropertiesBuilderParameters]
//	      PropertiesBuilderParameters propertiesBuilderParameters = params.properties()
//	              .setFile(propertiesFile)
//	              .setEncoding("UTF-8")
//	              .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
//	              .setThrowExceptionOnMissing(true);

	        // Use PropertiesConfiguration to read file
	        // 要想params配置的属性生效一定是要在configure()中配置的才可以原因是configure()方法要的参数是BuilderParameters[],但是原生的Parameters并非为该类型或者其子类,所以之前配置的params的相关设置不生效
	        ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder = new ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration>(
	                PropertiesConfiguration.class)
	                        // 使用FileBasedBuilderParameters
	                        .configure(fileBasedBuilderParameters);
	                        // 使用PropertiesBuilderParameters
	                        // .configure(propertiesBuilderParameters);
	        //  @formatter:on

	        // check the file per second
	        PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1,
	                TimeUnit.SECONDS);
	        // start trigger
	        trigger.start();
	        // 这里是通过BeanHelper.createBean创建出来的。
	        // 所以可以看到BeanHelper这个就是一个对Configer的IOC管理。
	        PropertiesConfiguration config = builder.getConfiguration();
	        Object value = null;
//	        value = config.get( Map.class ,"database");
	        value = config.get( String.class ,"database");
			System.out.println(value); 
	}
	
	@Test
	public  void main() throws Exception {
		BasicConfigurationBuilder<DatabaseConfiguration> builder =
			     new BasicConfigurationBuilder<DatabaseConfiguration>(DatabaseConfiguration.class);
			 DataSource dataSource= null;
			DatabaseBuilderParameters database = new Parameters().database();
			BeanHelper beanHelper = new BeanHelper();
			beanHelper.createBean(null);
			database.setBeanHelper(beanHelper);
			builder.configure(database
			         .setDataSource(dataSource)
			         .setTable("myconfig")
			         .setKeyColumn("key")
			         .setValueColumn("value"));
			DatabaseConfiguration config = builder.getConfiguration();
			 String value = config.getString("foo");

//		 Parameters params = new Parameters();
//		 CombinedConfigurationBuilder builder =
//		         new CombinedConfigurationBuilder().configure(params.fileBased()
//		                 .setFile(new File("config.properties")));
//		 PropertiesConfiguration config = builder.getConfiguration();
	}
	@Test
	public  void beanHelper() throws Exception {
		// 这个是传入默认的defaultBeanFactory
		// org.apache.commons.configuration2.beanutils.BeanFactory
		BeanHelper beanHelper = new BeanHelper(null);
		// 注册所有可以使用的工厂。  非静态，有些系统不需要这么多工厂。
		beanHelper.registerBeanFactory("woDe", null);
		BeanDeclaration data = null;
//		beanHelper.createBean(data);    等价于下面的。   
		// 1. 找工厂   
		// 2. 获得class，
		// 2.1 这个是从declaraion的Class通过lang3.utils的loadClass - 通过线程ClassLoader加载。
		// 2.2 用户传入的class
		// 2.3 Factory默认的class。
		// 2.4 class 必须不为null
		// 3. 
	
//      beanHelper.createBean(data, null);  
		// 这个在apache里面没有使用。 
		// 这个在apache里面完全相同 , 他的param并没有地方使用。
        beanHelper.createBean( data , null , null);
        
	}
	
	
}
