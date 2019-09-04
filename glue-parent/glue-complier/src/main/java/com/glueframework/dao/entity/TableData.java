package com.glueframework.dao.entity;


public interface TableData extends ColumnSet<Record>{
	public Object get(int index,String name);
	public default Class<Record> getElementType() {
		return Record.class;
	}
}
