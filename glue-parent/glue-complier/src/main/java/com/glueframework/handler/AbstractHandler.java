package com.glueframework.handler;

import org.apache.commons.dbutils.ResultSetHandler;

import com.glueframework.dao.SqlConfig;
import com.wlh.util.Constant;
import com.wlh.util.StringUtils;

public abstract class AbstractHandler<T> implements ResultSetHandler<T> {
	protected SqlConfig config;

	public AbstractHandler(SqlConfig config) {
		super();
		this.config = config;
	}

	public Integer getColumnMax() {
		Integer columnMax = config.getConfig(config.COLUMN_MAX);
		if (columnMax == null)
			columnMax = Integer.MAX_VALUE;
		return columnMax;
	}
	public String[] getColumnNames(){
		Object config2 = config.getConfig(config.COLUMN_NAME);
		String[] columnNames = null;
		if( config2 != null){
			columnNames = config2.toString().split(""+Constant.STRING_SEPARATOR);
		}
		return columnNames;
	}
	public int[] getcolumnIndexs(){
		return StringUtils.splitToInts(config.getConfig(config.COLUMN_NAME), ""+Constant.STRING_SEPARATOR);
	}
	public int[] getRowIndexs(){
		return StringUtils.splitToInts(config.getConfig(config.ROW_INDEXS), ""+Constant.STRING_SEPARATOR);
	}
}
