package com.glueframework.handler;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.glueframework.dao.SqlConfig;
import com.wlh.beanutils.BeanUtils;
import com.wlh.exception.ConvertRunException;

public class ObjectHandler<T> extends AbstractHandler<T> {
	Class<T> cla;

	public ObjectHandler(SqlConfig config, Class<T> cla) {
		super(config);
		this.cla = cla;
	}

	@Override
	public T handle(ResultSet rs) throws SQLException {
		return rs.next() ? toObject(this,rs,cla) : null;
	}

	public static <T> T toObject(AbstractHandler handler,ResultSet rs , Class<T> cla) throws SQLException {
		Map<String, Object> map = RecordHandler.toMap(handler, rs);
		T bean;
		try {
			bean = cla.newInstance();
			BeanUtils.populate(bean, map);
		} catch (InstantiationException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ConvertRunException(e);
		}
		return bean;
	}

}
