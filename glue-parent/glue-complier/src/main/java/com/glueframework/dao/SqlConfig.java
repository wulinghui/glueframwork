package com.glueframework.dao;

import java.sql.Connection;


/**
 * @author wulinghui
 *  
 */ 
public interface SqlConfig {
	public static final String SQL_DEFAULT_CONFIG = "dao-SqlDefaultConfig";
	String SQL = "SQL"; 
	String RESULT_SET_HANDLER = "org.apache.commons.dbutils.ResultSetHandler<T>";
	String IS_FUTURE = "Future";  // true 执行异步
	String COLUMN_NAME = "columnName";
	String COLUMN_MAX = "columnMax";
	String ROW_INDEXS = "RowIndexs"; // 第几行的数据可以被放到list里面。
	String CONNECTION = "java.sql.Connection";
	String SQL_PLUG = "SqlTranslatorPlug";
	
	String RETURN_CLASS = "returnClass"; // 这里引入一个 com.wlh.util.TypeResolvable。
/*
 *  * - 当一个 public List<User> findAll(){...} 的时候
 *  RETURN_CLASS 应该为List.class
 *  RETURN_CLASS_GENERIC_TYPE  应该为User.class
 */
//	String RETURN_CLASS_GENERIC_TYPE = "returnClassGenericType";
	
	/**
	 * 用于设置输入的map或bean对应设置值
	 * stmt.setObject(i + 1, object);
	 */
	String FILL_STATEMENT_KEYS = "fillStatementKeys";
	SqlConfig setConfig(String key , Object value);
	<T> T getConfig(String key);
}
