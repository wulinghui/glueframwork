package com.glueframework.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang3.BooleanUtils;

import com.wlh.config.WrapEntity;

public class SqlSessionFuture extends SqlSessionJDBC{
	protected static final WrapEntity<ExecutorService> entity = 
			WrapEntity.getWrapEntityBySystemConfig("dao-SqlSessionFuture-ExecutorService");
	static{
		if( entity.isEmpty() ){
			entity.setWrapObj(Executors.newCachedThreadPool());
		}
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Map parameter) throws SQLException {
		Boolean isFuture = config.getConfig(config.IS_FUTURE);
		if(BooleanUtils.isNotFalse(isFuture)){
			return exeFuture(config, parameter);
		}else{
			return super.select(config, parameter);
		}
	}

	protected <T> Future<T> exeFuture(SqlConfig config, Object parameter) {
		Callable<T> call = new Callable<T>(){
			public T call() throws Exception {
				return (T) selectObj(config, parameter);
			}
		};
		return entity.getWrapObj().submit(call);
	}

	@Override
	public <T> Future<T> select(SqlConfig config, Object parameter) throws SQLException {
		Boolean isFuture = config.getConfig(config.IS_FUTURE);
		if(BooleanUtils.isNotFalse(isFuture)){
			return exeFuture(config, parameter);
		}else{
			return super.select(config, parameter);
		}
	}
	
}
