package com.glueframework.dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import com.glueframework.dao.entity.ColumnSet;
import com.glueframework.dao.entity.Record;
import com.glueframework.dao.entity.TableData;
import com.glueframework.dao.entity.Value;
import com.wlh.beanutils.BeanUtils;
import com.wlh.exception.ConvertRunException;

/**
 * @author wulinghui
 * 装饰组件。
 */
public  class DecorateSqlSession extends AbstractSqlSession{
	SqlSession session;
	
	public DecorateSqlSession(SqlSession session) {
		super();
		this.session = session;
	}
	
	public int[] batch(SqlConfig config, Object[][] params) throws SQLException {
		return session.batch(config, params);
	}

	public int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException {
		return session.batch(config, parameter);
	}

	public int[] batch0(SqlConfig config, List<?> parameter)
			throws SQLException {
		return session.batch0(config, parameter);
	}

	public int update(SqlConfig config, Map parameter) throws SQLException {
		return session.update(config, parameter);
	}

	public int update(SqlConfig config, Object parameter) throws SQLException {
		return session.update(config, parameter);
	}

	public <T> Future<T> select(SqlConfig config, Map parameter
			) throws SQLException {
		return session.select(config, parameter);
	}

	public <T> Future<T> select(SqlConfig config, Object parameter) throws SQLException {
		return session.select(config, parameter);
	}

	@Override
	protected SqlConfig getSqlConfig(String configId) throws SQLException {
		
		return super.getSqlConfig(configId);
	}

	@Override
	public final Future<ResultSet> selectResultSet(String configId, Object parameter)
			throws SQLException {
		
		return super.selectResultSet(configId, parameter);
	}

	@Override
	public final Future<ResultSet> selectResultSet(String configId, Map parameter)
			throws SQLException {
		
		return super.selectResultSet(configId, parameter);
	}

	@Override
	public final int[] batch(String configId, Object[][] params) throws SQLException {
		
		return super.batch(configId, params);
	}

	@Override
	public final int[] batch0(String configId, List<?> parameter)
			throws SQLException {
		
		return super.batch0(configId, parameter);
	}

	@Override
	public final int[] batch(String configId, List<? extends Map> parameter)
			throws SQLException {
		
		return super.batch(configId, parameter);
	}

	@Override
	public final int update(String configId, Object parameter) throws SQLException {
		
		return super.update(configId, parameter);
	}

	@Override
	public final int update(String configId, Map parameter) throws SQLException {
		
		return super.update(configId, parameter);
	}

	@Override
	public final <T> Future<T> select(String configId, Object parameter) throws SQLException {
		
		return super.select(configId, parameter);
	}

	@Override
	public final <T> Future<T> select(String configId, Map parameter) throws SQLException {
		
		return super.select(configId, parameter);
	}

	@Override
	public final Future<Value> selectValue(String configId, Object parameter)
			throws SQLException {
		
		return super.selectValue(configId, parameter);
	}

	@Override
	public final Future<Value> selectValue(String configId, Map parameter)
			throws SQLException {
		
		return super.selectValue(configId, parameter);
	}

	@Override
	public final Future<Record> selectRecord(String configId, Object parameter)
			throws SQLException {
		
		return super.selectRecord(configId, parameter);
	}

	@Override
	public final Future<Record> selectRecord(String configId, Map parameter)
			throws SQLException {
		
		return super.selectRecord(configId, parameter);
	}

	@Override
	public final Future<TableData> selectTableData(String configId, Object parameter)
			throws SQLException {
		
		return super.selectTableData(configId, parameter);
	}

	@Override
	public final Future<TableData> selectTableData(String configId, Map parameter)
			throws SQLException {
		
		return super.selectTableData(configId, parameter);
	}

	@Override
	public final Future<ColumnSet> selectColumnSet(String configId, Object parameter)
			throws SQLException {
		
		return super.selectColumnSet(configId, parameter);
	}

	@Override
	public final Future<ColumnSet> selectColumnSet(String configId, Map parameter)
			throws SQLException {
		
		return super.selectColumnSet(configId, parameter);
	}

	@Override
	public final Future<Value> selectValue(SqlConfig config, Map parameter)
			throws SQLException {
		
		return super.selectValue(config, parameter);
	}

	@Override
	public final Future<Record> selectRecord(SqlConfig config, Map parameter)
			throws SQLException {
		
		return super.selectRecord(config, parameter);
	}

	@Override
	public final Future<Record> selectRecord(SqlConfig config, Object parameter)
			throws SQLException {
		
		return super.selectRecord(config, parameter);
	}

	@Override
	public final Future<TableData> selectTableData(SqlConfig config, Map parameter)
			throws SQLException {
		
		return super.selectTableData(config, parameter);
	}

	@Override
	public final Future<TableData> selectTableData(SqlConfig config, Object parameter)
			throws SQLException {
		
		return super.selectTableData(config, parameter);
	}

	@Override
	public final Future<ColumnSet> selectColumnSet(SqlConfig config, Map parameter)
			throws SQLException {
		
		return super.selectColumnSet(config, parameter);
	}

	@Override
	public final Future<Value> selectValue(SqlConfig config, Object parameter)
			throws SQLException {
		
		return super.selectValue(config, parameter);
	}

	@Override
	public final Future<ColumnSet> selectColumnSet(SqlConfig config, Object parameter)
			throws SQLException {
		
		return super.selectColumnSet(config, parameter);
	}

	@Override
	public final Future<ResultSet> selectResultSet(SqlConfig config, Map parameter)
			throws SQLException {
		
		return super.selectResultSet(config, parameter);
	}

	@Override
	public final Future<ResultSet> selectResultSet(SqlConfig config, Object parameter)
			throws SQLException {
		
		return super.selectResultSet(config, parameter);
	}

	protected Map<String, String> toMap(Object parameter) {
		try {
			return BeanUtils.describe(parameter);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new ConvertRunException(e);
		}
	}

	
}
