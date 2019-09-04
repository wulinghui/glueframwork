package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.lang3.ArrayUtils;

import com.glueframework.dao.SqlConfig;
import com.glueframework.dao.entity.ColumnSet;
import com.glueframework.dao.entity.ColumnSetImp;
import com.glueframework.dao.entity.Value;
import com.glueframework.dao.entity.ValueImp;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.ColumnListHandler
 */
public class ColumnSetHandler extends AbstractHandler<ColumnSet<Object>> {

	public ColumnSetHandler(SqlConfig config) {
		super(config);
	}

	@Override
	public ColumnSet<Object> handle(ResultSet rs) throws SQLException {
		ColumnSet<Object> rows = new ColumnSetImp(new ArrayList<Object>(),Object.class);
		int i = 0;
		Integer columnMax = this.getColumnMax();
		int[] rowIndexs = getRowIndexs();
        int rsIndex = 0;
        while(rs.next()){
        	if( rowIndexs != null)	
        		if(ArrayUtils.contains(rowIndexs, rsIndex++)) continue;
        	
			if (i == columnMax)
				break;
			rows.add(this.handleRow(rs));
			i++;
		}
		return rows;
	}

	protected Object handleRow(ResultSet rs) throws SQLException {
		return  ValueHandler.toValue(this, rs);
	}

}
