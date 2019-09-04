package com.glueframework.dao;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author wulinghui
 * 不使用多线程的使用。
 * @param <V>
 */
public class NoFutureImp<V> implements Future<V> {
	V value;
	
	public NoFutureImp(V value) {
		this.value = value;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		return false;
	}

	@Override
	public boolean isCancelled() {
		return false;
	}

	@Override
	public boolean isDone() {
		return value != null;
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		return value;
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException,
			ExecutionException, TimeoutException {
		return value;
	}

}
