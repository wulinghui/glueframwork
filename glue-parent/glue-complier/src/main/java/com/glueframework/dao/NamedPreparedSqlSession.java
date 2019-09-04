package com.glueframework.dao;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.glueframework.dao.plug.SqlTranslatorPlug;
import com.glueframework.dao.plug.SqlTranslatorPlugNamedPrepared;
import com.glueframework.handler.ResultSetHandlerOfJdbc;
import com.wlh.exception.ConvertRunException;
import com.wlh.util.Constant;
import com.wlh.util.MapUtils;


/**
 * @author wulinghui
 * @Deprecated 职责太混乱，全部的操作都在if else里面。
 * 我们应该采用装饰模式，堆积木似的一步步往上堆。
 */
@Deprecated
public  class NamedPreparedSqlSession extends AbstractSqlSession {
	@Override
	public int[] batch(SqlConfig config, Object[][] params) throws SQLException {
		return this.execteBatch(config,getSql(config), params,false);
	}

	@Override
	public int[] batch(SqlConfig config, List<? extends Map> parameter) throws SQLException {
		return this.execteBatch(config,getSql(config), parameter,true);
	}

	@Override
	public int[] batch0(SqlConfig config, List<?> parameter) throws SQLException {
		return this.execteBatch(config,getSql(config), parameter,true);
	}

	@Override
	public int update(SqlConfig config, Map parameter) throws SQLException {
		SqlTranslatorPlug translator = getSqlTranslatorPlug( config ,parameter);
		return this.execteUpdate(config, translator.translate(getSql(config)), translator.getParams(parameter));
	}

	@Override
	public int update(SqlConfig config, Object parameter) throws SQLException {
		SqlTranslatorPlug translator = getSqlTranslatorPlug( config ,parameter);
		return this.execteUpdate( config,translator.translate(getSql(config)), translator.getParams(parameter));
	}

	protected SqlTranslatorPlug getSqlTranslatorPlug(SqlConfig config, Object parameter) {
		return new SqlTranslatorPlugNamedPrepared(config , parameter);
	} 

	@Override
	public <T> Future<T> select(SqlConfig config, Map parameter) throws SQLException {
		// TODO Auto-generated method stub
		ResultSetHandler<T> rsh = getResultSetHandler(config);
		Boolean isFuture = config.getConfig(config.IS_FUTURE);
		if( MapUtils.isNotEmpty(parameter)){
			SqlTranslatorPlug translator = getSqlTranslatorPlug( config ,parameter);
			if( isFuture == null || isFuture){
				return exeFuture(config, parameter, rsh, translator);
			}else{
				return new NoFutureImp<T>(execteQuery(config,translator.translate(getSql(config)), rsh,translator.getParams(parameter)));
			}
		}else{
			if( isFuture == null || isFuture){
				return exeFuture(config, rsh);
			}else{
				return new NoFutureImp<T>(execteQuery(config,getSql(config), rsh, ArrayUtils.EMPTY_OBJECT_ARRAY));
			}
		}
	}

	protected <T> Future<T> exeFuture(SqlConfig config, ResultSetHandler<T> rsh) {
		Callable<T> call = new Callable<T>(){
			public T call() throws Exception {
				return execteQuery(config,getSql(config), rsh,ArrayUtils.EMPTY_OBJECT_ARRAY);
			}
		};
		return Executors.newCachedThreadPool().submit(call);
	}

	protected <T> Future<T> exeFuture(SqlConfig config, Map parameter,
			ResultSetHandler<T> rsh, SqlTranslatorPlug translator) {
		Callable<T> call = new Callable<T>(){
			public T call() throws Exception {
				return execteQuery(config,translator.translate(getSql(config)), rsh,translator.getParams(parameter));
			}
		};
		return Executors.newCachedThreadPool().submit(call);
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Object parameter) throws SQLException {
		ResultSetHandler<T> rsh = getResultSetHandler(config);
		if( Objects.nonNull(parameter)){
			SqlTranslatorPlug translator = getSqlTranslatorPlug( config ,parameter);
			return new NoFutureImp<T>(execteQuery(config,translator.translate(getSql(config)), rsh,translator.getParams(parameter)));
		}else{
			return new NoFutureImp<T>(execteQuery(config,getSql(config), rsh, ArrayUtils.EMPTY_OBJECT_ARRAY));
		}
	}

	
	
	protected  boolean getcloseConn(){
		return false;
	}
	protected int execteUpdate(SqlConfig config, String sql, Object[] params) throws SQLException {
		Connection conn = getConnection(config);
		boolean closeConn = getcloseConn();
        validate(conn, closeConn, sql, params);
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            stmt = this.prepareStatement(conn, sql);
            this.fillStatement(stmt, params);
            rows = stmt.executeUpdate();
        }finally {
        	DbUtils.close(stmt);
        	isCloseConnection(conn, closeConn);
        }
        return rows;
    }
	protected <T> T execteQuery(SqlConfig config, String sql, ResultSetHandler<T> rsh, Object[] params)
            throws SQLException {
		Connection conn = getConnection(config);
		boolean closeConn = getcloseConn();
      validate(conn, closeConn, sql, params);

        PreparedStatement stmt = null;
        ResultSet rs = null;
        T result = null;

        try {
            stmt = this.prepareStatement(conn, sql);
            this.fillStatement(stmt, params);
            rs = stmt.executeQuery();
            result = rsh.handle(rs);
        } finally {
            try {
            	Boolean close = config.getConfig(ResultSetHandlerOfJdbc.CLOSE_RS_FLAG);
            	if( close == null || close)
            		DbUtils.close(rs);
            } finally {
            	DbUtils.close(stmt);
                isCloseConnection(conn, closeConn);
            }
        }
        return result;
    }

	
	protected int[] execteBatch( SqlConfig config,String sql, Object params,boolean istranslator) throws SQLException {
		Connection conn = getConnection(config);
		boolean closeConn = getcloseConn();
		validate(conn, closeConn, sql, params);
		
        PreparedStatement stmt = null;
        int[] rows = null;
        SqlTranslatorPlug translator = null;
        try {
        	if(istranslator){
        		translator = getSqlTranslatorPlug( config ,params);
        		stmt = this.prepareStatement(conn, translator.translate(sql));
        	}else{
        		stmt = this.prepareStatement(conn,sql);
        	}
            if( params instanceof Object[][]){
            	Object[][]  param = (Object[][]) params;
            	for (int i = 0; i < param.length; i++) {
            		this.fillStatement(stmt, param[i]);
            		stmt.addBatch();
            	}
            }else if( params instanceof List){
            	List  param = (List) params;
            	Object object;
            	for (int i = 0; i < param.size(); i++) { 
            		object = param.get(i);
            		if( object instanceof Object[] ){
            			this.fillStatement(stmt, object);
            		}else if( object instanceof Map ){
            			this.fillStatement(stmt, translator.getParams((Map)object));
            		}else if( object instanceof Object ){
            			this.fillStatement(stmt, translator.getParams(object));
            		}
            		stmt.addBatch();
            	}
            }
            rows = stmt.executeBatch();
        } finally {
        	DbUtils.close(stmt);
            isCloseConnection(conn, closeConn);
        }
        return rows;
    }

	
	public void fillStatement(PreparedStatement stmt, Object obj)
            throws SQLException {
		// nothing to do here
        if (obj == null || stmt == null) {
            return;
        }
        // check the parameter count, if we can
        ParameterMetaData pmd = null;
            pmd = stmt.getParameterMetaData();
            int stmtCount = pmd.getParameterCount();
            

        
        if( obj instanceof Object[]){
        	Object[] params = (Object[]) obj; 
        	int paramsCount = params == null ? 0 : params.length;
            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }
        	for (int i = 0; i < params.length; i++) {
        		if (params[i] != null) {
        			stmt.setObject(i + 1, params[i]);
        		} else {
        			// VARCHAR works with many drivers regardless
        			// of the actual column type. Oddly, NULL and
        			// OTHER don't work with Oracle's drivers.
        			int sqlType = Types.VARCHAR;
        			/*
        			 * It's not possible for pmdKnownBroken to change from
        			 * true to false, (once true, always true) so pmd cannot
        			 * be null here.
        			 */
        			sqlType = pmd.getParameterType(i + 1);
        			stmt.setNull(i + 1, sqlType);
        		}
        	}
        }else if( obj instanceof Collection){
        	List params = (List) obj; 
        	int paramsCount = params == null ? 0 : params.size();
            if (stmtCount != paramsCount) {
                throw new SQLException("Wrong number of parameters: expected "
                        + stmtCount + ", was given " + paramsCount);
            }
            Object object;
        	for (int i = 0; i < params.size(); i++) {
        		object = params.get(i);
				if (object !=null) {
        			stmt.setObject(i + 1, object);
        		} else {
        			// VARCHAR works with many drivers regardless
        			// of the actual column type. Oddly, NULL and
        			// OTHER don't work with Oracle's drivers.
        			int sqlType = Types.VARCHAR;
        			/*
        			 * It's not possible for pmdKnownBroken to change from
        			 * true to false, (once true, always true) so pmd cannot
        			 * be null here.
        			 */
        			sqlType = pmd.getParameterType(i + 1);
        			stmt.setNull(i + 1, sqlType);
        		}
        	}
        }
    }
	protected PreparedStatement prepareStatement(Connection conn, String sql)
            throws SQLException {
        return conn.prepareStatement(sql);
    }
	protected void validate(Connection conn, boolean closeConn, String sql,
			Object params) throws SQLException {
		if (conn == null) {
            throw new SQLException("Null connection");
        }
        
        if (sql == null) {
            isCloseConnection(conn, closeConn);
            throw new SQLException("Null SQL statement");
        }

        if (params == null) {
            isCloseConnection(conn, closeConn);
            throw new SQLException("Null parameters. If parameters aren't need, pass an empty array.");
        }
	}
	protected void isCloseConnection(Connection conn, boolean closeConn)
			throws SQLException {
		if (closeConn) {
			DbUtils.close(conn);
		}
	}

	
}
