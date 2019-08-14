package com.glueframework.boilerplate;

import java.sql.SQLException;

public class TestH2 {
	public static void main(String[] args) throws SQLException {
		// java -jar h2*.jar org.h2.tools.Server -web -webPort 8082 -browser
		// 1.启动h2
		org.h2.tools.Server.main("-web", "-webPort", "8082", "-browser");
		// 2.没表建立ConfigerBean对应的表,这个表名glue_configer 和 glue_configer_history
		
		// 3.1利用DButil对glue_configer增，改、删(往glue_configer_history备份)，查的service。
		
		// 3.2封装对glue_configer_history的查。
		
		// 4. 把查询出来的，value由json转化成bean。
		
		// 5. 实现ioc的ConversionHandler做bean的转化。
		
	}
}
