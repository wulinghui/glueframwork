package com.glueframework.commons;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import javax.sql.DataSource;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.h2.jdbcx.JdbcDataSource;

import com.glueframework.common.lang.ArrayUtils;
import com.glueframework.common.lang.ConstructorUtils;
import com.glueframework.common.util.MethodUtils;
import com.glueframework.common.util.PathUtil;
import com.glueframework.common.util.PropertyUtils;
import com.glueframework.confinger.ConfigerBeanSuper;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class DBTools {
	protected static ILogger logger = LogMSG.getLogger();
	// 1. 确保单例，2.反射new单例。 
	//protected DBTools()%s
	public static final DBTools TOOLS =
			(DBTools) ConstructorUtils.
			newInstanceFromSytem(DBTools.class.getName(), DBTools.class);
	
	 QueryRunner queryRunner = null;
	 public DBTools(DataSource ds){
		queryRunner = new QueryRunner(ds);
	}
	 public DBTools(){

		 String dataFile = PathUtil.getClassPath();
//		try {
//			org.h2.tools.Server.main("-web", "-webPort", "8082", "-browser");
//		} catch (SQLException e) {
//			logger.debug(e);
//		}
		 File parentFile = new File(dataFile).getParentFile();
		 parentFile = new File(parentFile,"h2_data_base");
		 dataFile = parentFile.getAbsolutePath();
		 logger.debug("dataFile-url=[%s]",dataFile);
		 //
			JdbcDataSource ds = new JdbcDataSource();
			ds.setURL("jdbc:h2:"+  dataFile);
			 ds.setUser("admin");
			 ds.setPassword("");
			 queryRunner = new QueryRunner(ds);
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

		return queryRunner;
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
	
	// insert into GLUE_CONFIGER values(?,?,?,?,?,?,?,?,?,?)
	public void insertByIntrospector(Object bean , String tableName) throws SQLException{
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean,Object.class);
		StringBuilder sb = new StringBuilder("INSERT INTO ");
		StringBuilder sb2 = new StringBuilder(")VALUES(");
		int length = propertyDescriptors.length;
		List<Object> list = new ArrayList<>(length);
		sb.append(tableName);
		sb.append('(');
		sb.append(propertyDescriptors[0].getName());
		list.add( MethodUtils.invokeMethod(propertyDescriptors[0].getReadMethod(), bean) );
		//
		sb2.append('?');
		for (int i = 1; i < length; i++) {
			sb.append(',');
			sb.append(propertyDescriptors[i].getName());
			//
			sb2.append(",?");
			list.add( MethodUtils.invokeMethod(propertyDescriptors[i].getReadMethod(), bean) );
		}
		sb2.append(')');
		sb.append(sb2.toString());
		update(sb, list);
	}
	
	// insert into GLUE_CONFIGER_HISTORY select * from GLUE_CONFIGER where _key=? 
	public void backupByIntrospector(Object bean , String tableName , String tableHistoryName , String[] whereArgs ) throws SQLException{
		if( ArrayUtils.isEmpty(whereArgs) || Objects.isNull(bean) ) return;
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>(whereArgs.length);
		sb.append("INSERT INTO ");
		sb.append(tableHistoryName);
		sb.append(" SELECT * FROM ");
		sb.append(tableName);
		sb.append(' ');
		getWhereSqlByIntrospector(bean,sb,list,whereArgs);
		update(sb, list);
	}
	// delete from GLUE_CONFIGER where _key=?
	public void deleteByIntrospector(Object bean , String tableName , String[] whereArgs ) throws SQLException{
		if( ArrayUtils.isEmpty(whereArgs) || Objects.isNull(bean) ) return;
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>(whereArgs.length);
		// insert into GLUE_CONFIGER_HISTORY select * from GLUE_CONFIGER where _key=?
		sb.append("DELETE FROM ");
		sb.append(tableName);
		sb.append(' ');
		getWhereSqlByIntrospector(bean,sb,list,whereArgs);
		update(sb, list);
	}
	public Object getValueByIntrospector(String select , String tableName, String[] whereArgs, Object bean)
			throws SQLException {
		//  sql   count(1) == 0
		StringBuilder sb = new StringBuilder(select);
		List<Object> list = new ArrayList<Object>();
		try {
			sb.append(" FROM ");
			sb.append(tableName);
			sb.append(' ');
			getWhereSqlByIntrospector(bean, sb, list, whereArgs );
			bean = queryRunner.query(sb.toString(),  new ScalarHandler<>(1), list.toArray() );
			logger.trace("sql=[%s],list=[%s],query=[%s]" , sb , list,bean);
		} catch (Exception e) {
			logger.warn(e);
		}
		return bean;
	}
	public List<String> getSetByIntrospector(String select , String tableName, String[] whereArgs, Object bean)
			throws SQLException {
		//  sql   count(1) == 0
		StringBuilder sb = new StringBuilder(select);
		List<Object> list = new ArrayList<Object>();
		List<String> list1 = ListUtils.EMPTY_LIST;
		try {
			sb.append(" FROM ");
			sb.append(tableName);
			sb.append(' ');
			getWhereSqlByIntrospector(bean, sb, list, whereArgs );
			list1 = queryRunner.query(sb.toString(),  new ColumnListHandler<String>(1), list.toArray() );
			logger.trace("sql=[%s],list=[%s],query=[%s]" , sb , list,bean);
		} catch (Exception e) {
			logger.warn(e);
		}
		return list1;
	}
	
	public <T>T getBeanByIntrospector(String tableName, String[] whereArgs, Object bean, Class<T> type)
			throws SQLException {

		//  sql   count(1) == 0
		StringBuilder sb = new StringBuilder("SELECT * FROM ");
		List<Object> list = new ArrayList<Object>();
		try {
			sb.append(tableName);
			sb.append(' ');
			getWhereSqlByIntrospector(bean, sb, list, whereArgs );
			bean = (ConfigerBeanSuper) queryRunner.query(sb.toString(), new BeanHandler<>(type), list.toArray() );
			logger.trace("sql=[%s],list=[%s],query=[%s]" , sb , list,bean);
		} catch (Exception e) {
			logger.warn(e);
		}
		return (T) bean;
	}
	public void update(StringBuilder sb, List<Object> list)
			throws SQLException {
		Object[] array = list.toArray();
		logger.trace("update=[%s] , listLength=%s values=[%s]" + array.length , sb ,list.size() , list);
		queryRunner.update(sb.toString(),array);
	}
	// WHERE 1=1 AND ID=? AND ....  
	public void getWhereSqlByIntrospector(Object bean ,StringBuilder sql , List<Object> list ,String[] args ) throws SQLException{
		if( ArrayUtils.isEmpty(args) ) return; // _groupId_       _groupId_
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(bean,Object.class);
		sql.append("WHERE 1=1");
		for (int i = 0; i < propertyDescriptors.length; i++) {
			String name = propertyDescriptors[i].getName();
			if( ArrayUtils.contains(args, name)){
				sql.append(" AND ");
				sql.append(name);
				sql.append("=?");
				list.add( MethodUtils.invokeMethod(propertyDescriptors[i].getReadMethod(), bean) );
			}
		}
	}
}
