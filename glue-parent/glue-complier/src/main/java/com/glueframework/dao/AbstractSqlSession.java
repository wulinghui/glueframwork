package com.glueframework.dao;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.glueframework.dao.entity.ColumnSet;
import com.glueframework.dao.entity.ParameterIndex;
import com.glueframework.dao.entity.Record;
import com.glueframework.dao.entity.TableData;
import com.glueframework.dao.entity.Value;
import com.glueframework.handler.ColumnSetHandler;
import com.glueframework.handler.ObjectHandler;
import com.glueframework.handler.ObjectListHandler;
import com.glueframework.handler.RecordHandler;
import com.glueframework.handler.ResultSetHandlerOfJdbc;
import com.glueframework.handler.ScalarHandler;
import com.glueframework.handler.TableDataHandler;
import com.glueframework.handler.ValueHandler;
import com.wlh.beanutils.BeanUtils;
import com.wlh.exception.ConvertRunException;
import com.wlh.util.Constant;
import com.wlh.util.StringUtils;
import com.wlh.util.TypeResolvable;

public abstract class AbstractSqlSession implements SqlSession {
	protected SqlConfig getSqlConfig(String configId) throws SQLException {
//		SqlConfig bean = IocManage.getBean(com.wlh.dao.SqlConfig.class,
//				configId);
//		return bean;
		return new AbstractSqlConfig(configId);
	}

	public Future<ResultSet> selectResultSet(String configId, Object parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectResultSet(bean, parameter);
	}

	public Future<ResultSet> selectResultSet(String configId, Map parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectResultSet(bean, parameter);
	}

	@Override
	public int[] batch(String configId, Object[][] params) throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		
		return batch(bean, params);
	}

	@Override
	public int[] batch0(String configId, List<?> parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return batch0(bean, parameter);
	}

	@Override
	public int[] batch(String configId, List<? extends Map> parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return batch(bean, parameter);
	}

	@Override
	public int update(String configId, Object parameter) throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return update(bean, parameter);
	}

	@Override
	public int update(String configId, Map parameter) throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return update(bean, parameter);
	}

	@Override
	public <T> Future<T> select(String configId, Object parameter) throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return select(bean, parameter);
	}

	@Override
	public <T> Future<T> select(String configId, Map parameter) throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return select(bean, parameter);
	}

	@Override
	public Future<Value> selectValue(String configId, Object parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectValue(bean, parameter);
	}

	@Override
	public Future<Value> selectValue(String configId, Map parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectValue(bean, parameter);
	}

	@Override
	public Future<Record> selectRecord(String configId, Object parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectRecord(bean, parameter);
	}

	@Override
	public Future<Record> selectRecord(String configId, Map parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectRecord(bean, parameter);
	}

	@Override
	public Future<TableData> selectTableData(String configId, Object parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectTableData(bean, parameter);
	}

	@Override
	public Future<TableData> selectTableData(String configId, Map parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectTableData(bean, parameter);
	}

	@Override
	public Future<ColumnSet> selectColumnSet(String configId, Object parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectColumnSet(bean, parameter);
	}

	@Override
	public Future<ColumnSet> selectColumnSet(String configId, Map parameter)
			throws SQLException {
		SqlConfig bean = getSqlConfig(configId);
		return selectColumnSet(bean, parameter);
	}

	@Override
	public Future<Value> selectValue(SqlConfig config, Map parameter)
			throws SQLException {
		wrapResultSetHandler(config, Value.class);
		return select(config, parameter);
	}

	@Override
	public Future<Record> selectRecord(SqlConfig config, Map parameter)
			throws SQLException {
		wrapResultSetHandler(config, Record.class);
		return select(config, parameter);
	}

	@Override
	public Future<Record> selectRecord(SqlConfig config, Object parameter)
			throws SQLException {
		wrapResultSetHandler(config, Record.class);
		return select(config, parameter);
	}

	@Override
	public Future<TableData> selectTableData(SqlConfig config, Map parameter)
			throws SQLException {
		wrapResultSetHandler(config, TableData.class);
		return select(config, parameter);
	}

	@Override
	public Future<TableData> selectTableData(SqlConfig config, Object parameter)
			throws SQLException {
		wrapResultSetHandler(config, TableData.class);
		return select(config, parameter);
	}

	@Override
	public Future<ColumnSet> selectColumnSet(SqlConfig config, Map parameter)
			throws SQLException {
		wrapResultSetHandler(config, ColumnSet.class);
		return select(config, parameter);
	}

	@Override
	public Future<Value> selectValue(SqlConfig config, Object parameter)
			throws SQLException {
		wrapResultSetHandler(config, Value.class);
		return select(config, parameter);
	}

	@Override
	public Future<ColumnSet> selectColumnSet(SqlConfig config, Object parameter)
			throws SQLException {
		wrapResultSetHandler(config, ColumnSet.class);
		return select(config, parameter);
	}

	@Override
	public Future<ResultSet> selectResultSet(SqlConfig config, Map parameter)
			throws SQLException {
		wrapResultSetHandler(config, ResultSet.class);
		return select(config, parameter);
	}

	@Override
	public Future<ResultSet> selectResultSet(SqlConfig config, Object parameter)
			throws SQLException {
		wrapResultSetHandler(config, ResultSet.class);
		return select(config, parameter );
	}

	protected Connection getConnection(SqlConfig config) {
		return config.getConfig(config.CONNECTION);
	}

	protected void validate(Connection conn, String sql, Object params)
			throws SQLException {
		validate(conn, sql);
		if (params == null) {
			throw new SQLException(
					"Null parameters. If parameters aren't need, pass an empty array.");
		}
	}

	protected void validate(Connection conn, String sql) throws SQLException {
		if (conn == null) {
			throw new SQLException("Null connection");
		}
		if (sql == null) {
			throw new SQLException("Null SQL statement");
		}
	}

	protected String getSql(SqlConfig config) {
		return config.getConfig(config.SQL);
	}

	protected PreparedStatement prepareStatement(Connection conn, String sql)
			throws SQLException {
		return conn.prepareStatement(sql);
	}

	public void fillStatement(PreparedStatement stmt, Object[] params)
			throws SQLException {
		// nothing to do here
		if (params == null || stmt == null) {
			return;
		}
		// check the parameter count, if we can
		ParameterMetaData pmd = null;
		pmd = stmt.getParameterMetaData();
		int stmtCount = pmd.getParameterCount();
		int paramsCount = params == null ? 0 : params.length;
		if (stmtCount > /* != */paramsCount) {
			throw new SQLException("Wrong number of parameters: expected "
					+ stmtCount + ", was given " + paramsCount);
		}
		for (int i = 0; i < params.length; i++) {
			if (params[i] != null) {
				stmt.setObject(i + 1, params[i]);
			} else {
				int sqlType = Types.VARCHAR;
				sqlType = pmd.getParameterType(i + 1);
				stmt.setNull(i + 1, sqlType);
			}
		}
	}

	public void fillStatement(PreparedStatement stmt, Collection params)
			throws SQLException {
		// nothing to do here
		if (params == null || stmt == null) {
			return;
		}
		// check the parameter count, if we can
		ParameterMetaData pmd = null;
		pmd = stmt.getParameterMetaData();
		int stmtCount = pmd.getParameterCount();
		int paramsCount = params == null ? 0 : params.size();
		if (stmtCount > paramsCount) {
			throw new SQLException("Wrong number of parameters: expected "
					+ stmtCount + ", was given " + paramsCount);
		}
		int i = 0;
		for (Object object : params) {
			if (object != null) {
				stmt.setObject(i + 1, object);
			} else {
				int sqlType = Types.VARCHAR;
				sqlType = pmd.getParameterType(i + 1);
				stmt.setNull(i + 1, sqlType);
			}
			i++;
		}
	}
	public void fillStatement(PreparedStatement stmt, ParameterIndex[] setKeys ,Map params)
			throws SQLException {
		// nothing to do here
		if (params == null || setKeys == null || stmt == null) {
			return;
		}
		// check the parameter count, if we can
		ParameterMetaData pmd = null;
		pmd = stmt.getParameterMetaData();
		int stmtCount = pmd.getParameterCount();
		if (stmtCount > setKeys.length) {
			throw new SQLException("Wrong number of parameters: expected "
					+ stmtCount + ", was given " + setKeys.length);
		}
		Object object;
		for (int i = 0; i < setKeys.length; i++) {
			object = setKeys[i].getObj() == null ?
					params.get( setKeys[i].getName() ) : setKeys[i].getObj();
			if (object != null) {
				if( StringUtils.isEmpty( setKeys[i].getSetMethodName() )  ){
					stmt.setObject( setKeys[i].getIndex() , object);
				}else{
					try {
						MethodUtils.invokeExactMethod(stmt,  setKeys[i].getSetMethodName()  , new Object[]{object});
					} catch (NoSuchMethodException | IllegalAccessException
							| InvocationTargetException e) {
						throw new ConvertRunException(e);
					}
				}
			} else {
				int sqlType = Types.VARCHAR;
				sqlType = pmd.getParameterType(i + 1);
				stmt.setNull( setKeys[i].getIndex() , sqlType);
			}
		}
	}
	public void fillStatement(PreparedStatement stmt, ParameterIndex[] setKeys ,Object params)
			throws SQLException {
		try {
			//在这里支持数组。
			if( params.getClass().isArray()){
				fillStatement(stmt, (Object[] )params);
			//这里支持主键查询。 是基本类型或者是java.lang.包下的	
			}else if( params.getClass().isPrimitive() || params.getClass().getName().startsWith("java.lang.") ){
				fillStatement(stmt, new Object[]{params});
			}else{
				fillStatement(stmt, setKeys, BeanUtils.describe(params));
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new ConvertRunException(e);
		}
	}

	protected ParameterIndex[] getFillStatementKeys(SqlConfig config) {
		Object config2 = config.getConfig(config.FILL_STATEMENT_KEYS);
		if( config2 == null ) return ParameterIndex.EMPTY_ARRAY;
		String[] name;
		if(config2 instanceof ParameterIndex[] ){
			return (ParameterIndex[]) config2;
		}else if(config2 instanceof String[]  ){
			name = (String[])config2;
		}else if( config2 instanceof String  ){
			name = StringUtils.split(config2.toString(), Constant.STRING_SEPARATOR);
		}else{
			return ParameterIndex.EMPTY_ARRAY;
		}
		ParameterIndex[] indexs = new ParameterIndex[name.length];
		for (int i = 0; i < name.length; i++) {
			indexs[i] = new ParameterIndex(i+1, name[i]);
		}
		return indexs;
	}

	protected void wrapResultSetHandler(SqlConfig config, Class<?> returnClass) {
		ResultSetHandler rsh = config.getConfig(config.RESULT_SET_HANDLER);
		
		if(rsh == null){
			if(ClassUtils.isAssignable( returnClass , Value.class )){
				rsh = (ResultSetHandler) new ValueHandler(config);
				
			}else if(ClassUtils.isAssignable( returnClass ,  ColumnSet.class )){
				rsh = new ColumnSetHandler(config);
				
			}else if(ClassUtils.isAssignable( returnClass , Record.class )){
				rsh = (ResultSetHandler) new RecordHandler(config);
				
			}else if(ClassUtils.isAssignable( returnClass,   TableData.class)){
				rsh = (ResultSetHandler) new TableDataHandler(config);
				
			}else if(ClassUtils.isAssignable( returnClass , ResultSet.class )){
				rsh = (ResultSetHandler) new ResultSetHandlerOfJdbc(config);
				
			}else{
				// 为了提供Spring jpa的方法的语法规定
				TypeResolvable returnType = config.getConfig(config.RETURN_CLASS);
				if(returnType != null){
					Class toClass = returnType.toClass();
					// 判断是不是Future。
					if(  ClassUtils.isAssignable( toClass , Future.class) ){
						config.setConfig(config.IS_FUTURE, true);
						//
						returnType = returnType.getGeneric(0);
						toClass = returnType.toClass();
					}
					//是集合
					if(     ClassUtils.isAssignable( toClass ,Collection.class  )  ){
						returnType = returnType.getGeneric(0);
						toClass = returnType.toClass();
						//设置。
						if( ClassUtils.isPrimitiveOrWrapper( toClass )){
							rsh = new ColumnSetHandler(config);    
						}else if (  ClassUtils.isAssignable( toClass  ,  Map.class ) ){
							rsh =  new TableDataHandler(config);
						}else{
							rsh = new ObjectListHandler(config, toClass );
						}
					//基本类型和包装类
					}else if( ClassUtils.isPrimitiveOrWrapper(returnType.toClass())){
						rsh = new ScalarHandler(config);
					//其它类型
					}else{
						rsh = new ObjectHandler(config,returnType.toClass());
					}
				}else{
					assert 1==1 : "returnType is null , please check config.RETURN_CLASS";
				}
			}
			config.setConfig(config.RESULT_SET_HANDLER , rsh);
		}
	}
	protected  ResultSetHandler getResultSetHandler(SqlConfig config) {
		TypeResolvable returnType = config.getConfig(config.RETURN_CLASS);
		wrapResultSetHandler(config, returnType.toClass() ); 
		return config.getConfig(config.RESULT_SET_HANDLER);
	}

	public <T> T selectObj(SqlConfig config, Object params)
			throws SQLException {
				Connection conn = getConnection(config);
				String sql = getSql(config);
				validate(conn, sql, params);
				
				PreparedStatement stmt = null;
				ResultSet rs = null;
				T result = null;
				ResultSetHandler<T> rsh = getResultSetHandler(config);
				try {
					stmt = this.prepareStatement(conn, sql);
					this.fillStatement(stmt, getFillStatementKeys(config), params);
					rs = stmt.executeQuery();
					result = rsh.handle(rs);
				} finally {
					Boolean close = config.getConfig(ResultSetHandlerOfJdbc.CLOSE_RS_FLAG);
					try {
						if (close == null || close)
							DbUtils.close(rs);
					} finally {
						if (close == null || close)
							DbUtils.close(stmt);
					}
				}
				return result;
			}
}
