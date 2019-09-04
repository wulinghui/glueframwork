package com.glueframework.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ObjectUtils;

import com.wlh.util.MapUtils;

/**
 * @author wulinghui
 * 允许输入有null值。这里做个默认的转化。
 */
public class SqlSessionEnableNull extends DecorateSqlSession {
	public static final Object[][] EMPTY_2Arrays = new Object[0][0];
	public static final Map EMPTY_MAP = MapUtils.EMPTY_MAP;
	public static final Object EMPTY_OBJECT = ObjectUtils.NULL;
	public static final List EMPTY_LIST = ListUtils.EMPTY_LIST;
	public SqlSessionEnableNull(SqlSession session) {
		super(session);
	}

	@Override
	public int[] batch(SqlConfig config, Object[][] params) throws SQLException {
		params = wrapNull2Array(params);
		return super.batch(config, params);
	}
	
	@Override
	public int[] batch(SqlConfig config, List<? extends Map> parameter)
			throws SQLException {
		parameter = wrapNullList(parameter);
		return super.batch(config, parameter);
	}

	@Override
	public int[] batch0(SqlConfig config, List<?> parameter)
			throws SQLException {
		parameter = wrapNullList(parameter);
		return super.batch0(config, parameter);
	}

	@Override
	public int update(SqlConfig config, Map parameter) throws SQLException {
		parameter = wrapNullMap(parameter);
		return super.update(config, parameter);
	}

	@Override
	public int update(SqlConfig config, Object parameter) throws SQLException {
		parameter = wrapNullObj(parameter);
		return super.update(config, parameter);
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Map parameter)
			throws SQLException {
		parameter = wrapNullMap(parameter);
		return super.select(config, parameter);
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Object parameter)
			throws SQLException {
		parameter = wrapNullObj(parameter);
		return super.select(config, parameter);
	}
	public static final Object[][] wrapNull2Array(Object[][] params){
		return params == null ? EMPTY_2Arrays : params;
	}
	public static final Map wrapNullMap(Map params){
		return params == null ? EMPTY_MAP : params;
	}
	public static final Object wrapNullObj(Object params){
		return params == null ? EMPTY_OBJECT : params;
	}
	public static final List wrapNullList(List params){
		return params == null ? EMPTY_LIST : params;
	}
}
