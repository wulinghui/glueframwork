package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.ArrayUtils;

import com.glueframework.dao.SqlConfig;
import com.glueframework.dao.entity.Value;
import com.glueframework.dao.entity.ValueImp;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.ScalarHandler
 */
public class ValueHandler extends  AbstractHandler<Value> {
	
    public ValueHandler(SqlConfig config) {
		super(config);
	}

	public Value handle(ResultSet rs) throws SQLException {
        return new ValueImp( toValue(this, rs) );
    }

	public static Object toValue(AbstractHandler handler,ResultSet rs) throws SQLException {
		int[] rowIndexs = handler.getRowIndexs();
		int rowIndex = 0;
        if( rowIndexs != null ) rowIndex = rowIndexs[0];
        int rsIndex = 0;
        while(rs.next()){
        	if( rsIndex ++ != rowIndex) continue;
        	
        	String[] columnNames = handler.getColumnNames();
            String columnName = columnNames == null ? null : columnNames[0];
			if (columnName == null) {
				int[] columnIndexs = handler.getcolumnIndexs();
				if( ArrayUtils.isEmpty(columnIndexs) ){
					return rs.getObject( 1 ) ;
				}else{
					return rs.getObject(columnIndexs[0] );
				}
            }
            return rs.getObject(columnName); 
        }
        return null;
	}
}
