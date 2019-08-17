package com.glueframework.complier;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class InitTable {
	public static Boolean crateTable(String tableName,String[] items) throws SQLException {
		String urlString="jdbc:h2:D:/springboot/h2/test";
		String usernameString="admin";
		String password="";
        Statement stmt = null;
        Connection connection=null;
        try {
        	connection=DriverManager.getConnection(urlString, usernameString, password);
            DatabaseMetaData meta = connection.getMetaData();
            ResultSet rsTables = meta.getTables(null, null, tableName,
                    new String[] { "TABLE" });
            if (!rsTables.next()) {
                stmt = connection.createStatement();
                StringBuilder sql = new StringBuilder();
                sql.append(" CREATE TABLE ");
                if (null!=tableName&&tableName.length()>0) {
                    sql.append(tableName);
                }
                if (items != null && items.length > 0) {
                    sql.append(" ( ");
                    for (int i = 0;i < items.length;i++) {
                        sql.append(items[i]);
                        sql.append(" VARCHAR(5000), ");
                    }
                    sql.append("PRIMARY KEY(key,environment,groupId,artifactId)) ");
                }
 
                stmt.execute(sql.toString());
            }
            rsTables.close();
            return true;
        } finally {
        	stmt.close();
        	connection.close();
        }
    }
	public static void main(String[] args) throws SQLException {
		// java -jar h2*.jar org.h2.tools.Server -web -webPort 8082 -browser
		// 1.启动h2
		//org.h2.tools.Server.main("-web", "-webPort", "8082", "-browser");
		String[] items= {"value","key","environment","groupId","artifactId","version","createTime","updateTime","flag"};
		String tablename="ConfigerBean";
		crateTable(tablename,items);
		// 2.没表建立ConfigerBean对应的表,这个表名glue_configer 和 glue_configer_history
		
		// 3.1利用DButil对glue_configer增，改、删(往glue_configer_history备份)，查的service。
		
		// 3.2封装对glue_configer_history的查。
		
		// 4. 把查询出来的，value由json转化成bean。
		
		// 5. 实现ioc的ConversionHandler做bean的转化。
		
	}
}
