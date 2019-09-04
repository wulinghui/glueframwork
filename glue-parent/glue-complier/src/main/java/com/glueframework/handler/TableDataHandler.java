package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.glueframework.dao.SqlConfig;
import com.glueframework.dao.entity.ColumnSet;
import com.glueframework.dao.entity.Record;
import com.glueframework.dao.entity.TableData;
import com.glueframework.dao.entity.TableDataImp;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.MapListHandler
 */
public class TableDataHandler extends AbstractHandler<ColumnSet<Record>> {
	private final ColumnSetHandler handler;
			
	public TableDataHandler(SqlConfig config) {
		super(config);
		handler = new ColumnSetHandler(config){
			protected Record handleRow(ResultSet rs) throws SQLException {
				return RecordHandler.toRecord(this, rs);
			}
		};
	}
	
	@Override
	public TableData handle(ResultSet rs) throws SQLException {
		return new TableDataImp(handler.handle(rs));
	}

	
}
