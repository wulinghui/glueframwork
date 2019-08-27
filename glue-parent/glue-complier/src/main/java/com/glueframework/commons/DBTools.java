package com.glueframework.commons;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.h2.jdbcx.JdbcDataSource;

import com.glueframework.common.lang.ConstructorUtils;
import com.glueframework.common.util.PathUtil;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class DBTools {
	protected static ILogger logger = LogMSG.getLogger();
	// 1. 确保单例，2.反射new单例。 
	//protected DBTools()%s
	public static final DBTools TOOLS =
			(DBTools) ConstructorUtils.
			newInstanceFromSytem(DBTools.class.getName(), DBTools.class);
	
	 String dataFile = PathUtil.getClassPath();
	{
//		try {
//			org.h2.tools.Server.main("-web", "-webPort", "8082", "-browser");
//		} catch (SQLException e) {
//			logger.debug(e);
//		}
		 File parentFile = new File(dataFile).getParentFile();
		 parentFile = new File(parentFile,"h2_data_base");
		 dataFile = parentFile.getAbsolutePath();
		 logger.debug("dataFile-url=[%s]",dataFile);
	}
	public static DBTools getInstance(){
		return TOOLS;
	}
	public boolean drop(String tableName) {
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		try {
			String sql = "drop table if exists "+tableName;
			queryRunner.execute(sql);
			logger.debug("drop-table-sql=[%s]",sql);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return true;
	}
	public  Boolean create(String tableName,List<String> items) throws SQLException {
		QueryRunner run = getQueryRunner();
		 StringBuilder sql = new StringBuilder();
         sql.append(" CREATE TABLE ");
         if (null!=tableName&&tableName.length()>0) {
             sql.append(tableName);
         }
         if (items != null && items.size() > 0) {
             sql.append(" ( ");
             for (int i = 0;i < items.size();i++) {
                 sql.append(items.get(i));
                 if(i==items.size()-1)
                	 sql.append(" VARCHAR(5000) )");
                 else
                	 sql.append(" VARCHAR(5000), ");
             }
         }
         try {
        	   run.execute(sql.toString());
        	   return true;
		} catch (Exception e) {
			logger.warn(e);
			return false;
		}finally {
			logger.debug("create-table-sql=[%s]",sql);
		}  
    }
	public QueryRunner getQueryRunner() {
		JdbcDataSource ds = new JdbcDataSource();
//		 ds.setURL("jdbc:h2:~/test");
		ds.setURL("jdbc:h2:"+  dataFile);
		 ds.setUser("admin");
		 ds.setPassword("");
		 QueryRunner run = new QueryRunner(ds);
		return run;
	}
	
	
	public boolean createTable(Class<?> clas){
		return createTable(clas,clas.getSimpleName());
	}
	public boolean createTable(Class<?> clas,String tableName){
		List<String> items=new ArrayList<String>();
		List<Field> allFieldsList = FieldUtils.getAllFieldsList(clas);
		for (Field field : allFieldsList) {
			String fieldName = field.getName();
			items.add(fieldName);
		}
		try {
			create(tableName,items);
		} catch (SQLException e) {
			logger.warn(e);
		}
		return true;
	}
	
}
