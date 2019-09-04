package com.glueframework.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.dbutils.DbUtils;

/**
 * @author wulinghui
 * 简单的使用jdbc除了public int[] batch(SqlConfig config, Object[][] params)
 * 不需要指定config.setConfig(config.FILL_STATEMENT_KEYS,new String[]{"name","password"});
 * 其它的都需要显式的设置，不然这个类无法智能的判断。map和bean对应的set位置。
 * 同时这个类是同步的,没有使用Future模式。
 * 这是一个基石。
 */
public class SqlSessionJDBC extends AbstractSqlSession {

	@Override
	public int[] batch(SqlConfig config, Object[][] params) throws SQLException {
		Connection conn = getConnection(config);
		String sql = getSql(config);
		validate(conn, sql, params);

		PreparedStatement stmt = null;
		int[] rows = null;
		try {
			stmt = this.prepareStatement(conn, sql);
			for (int i = 0; i < params.length; i++) {
				this.fillStatement(stmt, params[i]);
				stmt.addBatch();
			}
			rows = stmt.executeBatch();
		} finally {
			DbUtils.close(stmt);
		}
		return rows;
	}

	@Override
	public int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException {
		return batchList(config, parameter);
	}

	@Override
	public int[] batch0(SqlConfig config, List<?> parameter)
			throws SQLException {
		return batchList(config, parameter);
	}

	protected int[] batchList(SqlConfig config, List parameter)
			throws SQLException {
		Connection conn = getConnection(config);
		String sql = getSql(config);
		validate(conn, sql, parameter);

		PreparedStatement stmt = null;
		int[] rows = null;
		try {
			stmt = this.prepareStatement(conn, sql);
			for (int i = 0; i < parameter.size(); i++) {
				this.fillStatement(stmt, getFillStatementKeys(config),
						parameter.get(i));
				stmt.addBatch();
			}
			rows = stmt.executeBatch();
		} finally {
			DbUtils.close(stmt);
		}
		return rows;
	}

	@Override
	public int update(SqlConfig config, Map params) throws SQLException {
		return updateObj(config, params);
	}

	protected int updateObj(SqlConfig config, Object params)
			throws SQLException {
		Connection conn = getConnection(config);
		String sql = getSql(config);
		validate(conn, sql, params);
		PreparedStatement stmt = null;
		int rows = 0;
		try {
			stmt = this.prepareStatement(conn, sql);
			this.fillStatement(stmt, getFillStatementKeys(config), params);
			rows = stmt.executeUpdate();
		} finally {
			DbUtils.close(stmt);
		}
		return rows;
	}

	@Override
	public int update(SqlConfig config, Object params) throws SQLException {
		return updateObj(config, params);
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Map params) throws SQLException {
		return new NoFutureImp<T>(selectObj(config, params));
	}
	@Override
	public <T> Future<T> select(SqlConfig config, Object parameter) throws SQLException {
		return new NoFutureImp<T>(selectObj(config, parameter));
	}
	

}
