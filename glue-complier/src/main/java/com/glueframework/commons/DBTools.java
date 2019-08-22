package com.glueframework.commons;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.h2.jdbcx.JdbcDataSource;

import com.glueframework.common.lang.ConstructorUtils;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class DBTools {
	protected static ILogger logger = LogMSG.getLogger();
	// 1. 确保单例，2.反射new单例。 
	//protected DBTools(){}
	public static final DBTools TOOLS =
			(DBTools) ConstructorUtils.
			newInstanceFromSytem(DBTools.class.getName(), DBTools.class);
	
	public static DBTools getInstance(){
		return TOOLS;
	}
	public boolean drop(String tableName) {
		QueryRunner queryRunner = DBTools.getInstance().getQueryRunner();
		try {
			queryRunner.execute("drop table if exists "+tableName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			logger.debug(e);
			return false;
		}finally {
			logger.debug("create-table-sql=[{}]",sql);
		}  
    }
	public QueryRunner getQueryRunner() {
		JdbcDataSource ds = new JdbcDataSource();
		 ds.setURL("jdbc:h2:D:/springboot/h2/test");
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
}
