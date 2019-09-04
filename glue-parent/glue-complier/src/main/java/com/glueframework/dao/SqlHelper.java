package com.glueframework.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.glueframework.dao.entity.ColumnSet;
import com.glueframework.dao.entity.Record;
import com.glueframework.dao.entity.TableData;
import com.glueframework.dao.entity.Value;
import com.wlh.config.WrapEntity;

public  abstract class SqlHelper {
	SqlHelper(){}
	
	public  static final WrapEntity<SqlSession> entity = WrapEntity.getWrapEntityBySystemConfig(SqlSession.class);
	static{
		if( entity.isEmpty() ){
			SqlSession session = new SqlSessionSqlPlugs(
					new SqlSessionFuture() );
			entity.setWrapObj(session);
		}
	}
	private static SqlSession getSqlSession(){
		return entity.getWrapObj();
	}
	public static int[] batch(String configId, Object[][] params) throws SQLException {
		return getSqlSession().batch(configId, params);
	}
	public static int[] batch(SqlConfig config, Object[][] params) throws SQLException {
		return getSqlSession().batch(config, params);
	}
	public static int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException {
		return getSqlSession().batch(config, parameter);
	}
	public static int[] batch0(SqlConfig config, List<?> parameter)
			throws SQLException {
		return getSqlSession().batch0(config, parameter);
	}
	public static int[] batch0(String configId, List<?> parameter)
			throws SQLException {
		return getSqlSession().batch0(configId, parameter);
	}
	public static int[] batch(String configId, List<? extends Map> parameter)
			throws SQLException {
		return getSqlSession().batch(configId, parameter);
	}
	public static int update(SqlConfig config, Map parameter) throws SQLException {
		return getSqlSession().update(config, parameter);
	}
	public static int update(SqlConfig config, Object parameter) throws SQLException {
		return getSqlSession().update(config, parameter);
	}
	public static int update(String configId, Object parameter) throws SQLException {
		return getSqlSession().update(configId, parameter);
	}
	public static int update(String configId, Map parameter) throws SQLException {
		return getSqlSession().update(configId, parameter);
	}
	public static Future<ResultSet> selectResultSet(SqlConfig config, Map parameter)
			throws SQLException {
		return getSqlSession().selectResultSet(config, parameter);
	}
	public static Future<ResultSet> selectResultSet(SqlConfig config, Object parameter)
			throws SQLException {
		return getSqlSession().selectResultSet(config, parameter);
	}
	public static Future<ResultSet> selectResultSet(String configId, Object parameter)
			throws SQLException {
		return getSqlSession().selectResultSet(configId, parameter);
	}
	public static Future<ResultSet> selectResultSet(String configId, Map parameter)
			throws SQLException {
		return getSqlSession().selectResultSet(configId, parameter);
	}
	public static <T> Future<T> select(SqlConfig config, Map parameter
			) throws SQLException {
		return getSqlSession().select(config, parameter);
	}
	public static <T> Future<T> select(SqlConfig config, Object parameter
			) throws SQLException {
		return getSqlSession().select(config, parameter);
	}
	public static <T> Future<T> select(String configId, Object parameter
			) throws SQLException {
		return getSqlSession().select(configId, parameter);
	}
	public static <T> Future<T> select(String configId, Map parameter
			) throws SQLException {
		return getSqlSession().select(configId, parameter);
	}
	public static Future<Value> selectValue(SqlConfig config, Map parameter)
			throws SQLException {
		return getSqlSession().selectValue(config, parameter);
	}
	public static Future<Value> selectValue(SqlConfig config, Object parameter)
			throws SQLException {
		return getSqlSession().selectValue(config, parameter);
	}
	public static Future<Value> selectValue(String configId, Object parameter)
			throws SQLException {
		return getSqlSession().selectValue(configId, parameter);
	}
	public static Future<Value> selectValue(String configId, Map parameter)
			throws SQLException {
		return getSqlSession().selectValue(configId, parameter);
	}
	public static Future<Record> selectRecord(SqlConfig config, Map parameter)
			throws SQLException {
		return getSqlSession().selectRecord(config, parameter);
	}
	public static Future<Record> selectRecord(SqlConfig config, Object parameter)
			throws SQLException {
		return getSqlSession().selectRecord(config, parameter);
	}
	public static Future<Record> selectRecord(String configId, Object parameter)
			throws SQLException {
		return getSqlSession().selectRecord(configId, parameter);
	}
	public static Future<Record> selectRecord(String configId, Map parameter)
			throws SQLException {
		return getSqlSession().selectRecord(configId, parameter);
	}
	public static Future<TableData> selectTableData(SqlConfig config, Map parameter)
			throws SQLException {
		return getSqlSession().selectTableData(config, parameter);
	}
	public static Future<TableData> selectTableData(SqlConfig config, Object parameter)
			throws SQLException {
		return getSqlSession().selectTableData(config, parameter);
	}
	public static Future<TableData> selectTableData(String configId, Object parameter)
			throws SQLException {
		return getSqlSession().selectTableData(configId, parameter);
	}
	public static Future<TableData> selectTableData(String configId, Map parameter)
			throws SQLException {
		return getSqlSession().selectTableData(configId, parameter);
	}
	public static Future<ColumnSet> selectColumnSet(SqlConfig config, Map parameter)
			throws SQLException {
		return getSqlSession().selectColumnSet(config, parameter);
	}
	public static Future<ColumnSet> selectColumnSet(SqlConfig config, Object parameter)
			throws SQLException {
		return getSqlSession().selectColumnSet(config, parameter);
	}
	public static Future<ColumnSet> selectColumnSet(String configId, Object parameter)
			throws SQLException {
		return getSqlSession().selectColumnSet(configId, parameter);
	}
	public static Future<ColumnSet> selectColumnSet(String configId, Map parameter)
			throws SQLException {
		return getSqlSession().selectColumnSet(configId, parameter);
	}
}
