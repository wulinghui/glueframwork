package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.glueframework.dao.SqlConfig;
import com.glueframework.dao.entity.TableDataImp;

public class ObjectListHandler<T> extends AbstractHandler<List<T>> {
	Class<T> cla;
	private final ColumnSetHandler handler;
	public ObjectListHandler(SqlConfig config, Class<T> cla) {
		super(config);
		this.cla = cla;
		handler = new ColumnSetHandler(config){
			protected Object handleRow(ResultSet rs) throws SQLException {
				return ObjectHandler.toObject(this, rs, cla);
			}
		};
	}

	public List handle(ResultSet rs) throws SQLException {
		return new TableDataImp(handler.handle(rs));
	}
	
}
