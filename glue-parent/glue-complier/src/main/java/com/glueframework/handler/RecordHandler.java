package com.glueframework.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;

import com.glueframework.dao.SqlConfig;
import com.glueframework.dao.entity.Record;
import com.glueframework.dao.entity.RecordImp;
import com.wlh.util.StringUtils;

/**
 * @author wulinghui
 * @see org.apache.commons.dbutils.handlers.MapHandler.MapHandler()
 */
public class RecordHandler extends AbstractHandler<Record> {

	public RecordHandler(SqlConfig config) {
		super(config);
	}

	public Record handle(ResultSet rs) throws SQLException {
		int[] rowIndexs = this.getRowIndexs();
		int rowIndex = 0;
		if (rowIndexs != null)
			rowIndex = rowIndexs[0];
		int rsIndex = 0;
		while (rs.next()) {
			if (rsIndex++ != rowIndex)
				continue;
			return toRecord(this, rs);
		}
		return null;
	}

	public static RecordImp toRecord(AbstractHandler handler, ResultSet rs)
			throws SQLException {
		return new RecordImp(toMap(handler, rs));
	}

	public static Map<String, Object> toMap(AbstractHandler handler,
			ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int cols = rsmd.getColumnCount();
		int[] columnIndexs = handler.getcolumnIndexs();
		if (columnIndexs == null)
			columnIndexs = ArrayUtils.EMPTY_INT_ARRAY;
		String[] columnNames = handler.getColumnNames();
		if (columnNames == null)
			columnNames = ArrayUtils.EMPTY_STRING_ARRAY;
		Map<String, Object> result = new HashMap(cols - columnIndexs.length
				- columnNames.length);

		for (int i = 1; i <= cols; i++) {
			for (int string : columnIndexs) {
				if (string == i)
					continue;
			}
			String columnName = rsmd.getColumnName(i);// rsmd.getColumnLabel(i);
			for (String string : columnNames) {
				// if(StringUtils.isNotBlank(string) &&
				// columnName.equalsIgnoreCase(string)) continue;
				if (StringUtils.equalsIgnoreCase(columnName, string))
					continue;
			}
			// if (null == columnName || 0 == columnName.length()) {
			// columnName = rsmd.getColumnName(i);
			// }
			result.put(columnName, rs.getObject(i));
		}
		return result;
	}

}
