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

public interface SqlSession {
	int[] batch(String configId, Object[][] params) throws SQLException;

	int[] batch(SqlConfig config, Object[][] params) throws SQLException;

	int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException;

	int[] batch0(SqlConfig config, List<?> parameter) throws SQLException;

	int[] batch0(String configId, List<?> parameter) throws SQLException;

	int[] batch(String configId, List<? extends Map> parameter)
			throws SQLException;

	//
	int update(SqlConfig config, Map parameter) throws SQLException;

	int update(SqlConfig config, Object parameter) throws SQLException;

	int update(String configId, Object parameter) throws SQLException;

	int update(String configId, Map parameter) throws SQLException;

	// //
	// 这里需要使用
	// ////////////////
	Future<ResultSet> selectResultSet(SqlConfig config, Map parameter)
			throws SQLException;

	Future<ResultSet> selectResultSet(SqlConfig config, Object parameter)
			throws SQLException;

	Future<ResultSet> selectResultSet(String configId, Object parameter)
			throws SQLException;

	Future<ResultSet> selectResultSet(String configId, Map parameter)
			throws SQLException;

	<T> Future<T> select(SqlConfig config, Map parameter) throws SQLException;

	<T> Future<T> select(SqlConfig config, Object parameter)
			throws SQLException;

	<T> Future<T> select(String configId, Object parameter) throws SQLException;

	<T> Future<T> select(String configId, Map parameter) throws SQLException;

	/**
	 * 这里没有SqlConfig，就利用配置里面的默认的SqlConfig， 如对应的数据源，事务级别，事务传播类型。 这里可以看做是声明式的配置管理。
	 * @param config该配置写在代码里面就是强制性配置的。
	 * @param parameter
	 * @return
	 */
	Future<Value> selectValue(SqlConfig config, Map parameter)
			throws SQLException;

	Future<Value> selectValue(SqlConfig config, Object parameter)
			throws SQLException;

	Future<Value> selectValue(String configId, Object parameter)
			throws SQLException;

	Future<Value> selectValue(String configId, Map parameter)
			throws SQLException;

	Future<Record> selectRecord(SqlConfig config, Map parameter)
			throws SQLException;

	Future<Record> selectRecord(SqlConfig config, Object parameter)
			throws SQLException;

	Future<Record> selectRecord(String configId, Object parameter)
			throws SQLException;

	Future<Record> selectRecord(String configId, Map parameter)
			throws SQLException;

	Future<TableData> selectTableData(SqlConfig config, Map parameter)
			throws SQLException;

	Future<TableData> selectTableData(SqlConfig config, Object parameter)
			throws SQLException;

	Future<TableData> selectTableData(String configId, Object parameter)
			throws SQLException;

	Future<TableData> selectTableData(String configId, Map parameter)
			throws SQLException;

	Future<ColumnSet> selectColumnSet(SqlConfig config, Map parameter)
			throws SQLException;

	Future<ColumnSet> selectColumnSet(SqlConfig config, Object parameter)
			throws SQLException;

	Future<ColumnSet> selectColumnSet(String configId, Object parameter)
			throws SQLException;

	Future<ColumnSet> selectColumnSet(String configId, Map parameter)
			throws SQLException;
}
