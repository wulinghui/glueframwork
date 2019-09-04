package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.glueframework.dao.SqlConfig;

/**
 * @author wulinghui
 * 用这个需要用户手动关闭游标。
 */
public class ResultSetHandlerOfJdbc extends AbstractHandler<ResultSet>{
	// 设置是否关闭ResultSet游标的使用,这个表示不能在SqlConfig里面显示，不安全。
	public static final String CLOSE_RS_FLAG = "notCloseResultSet";
	public ResultSetHandlerOfJdbc(SqlConfig config) {
		super(config);
		config.setConfig(CLOSE_RS_FLAG, false);
	}
	
	@Override
	public ResultSet handle(ResultSet rs) throws SQLException {
		return rs;
	}

}
