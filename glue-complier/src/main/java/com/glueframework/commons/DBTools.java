package com.glueframework.commons;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.glueframework.common.lang.ConstructorUtils;

public class DBTools {
	// 1. 确保单例，2.反射new单例。 
	protected DBTools(){}
	public static final DBTools TOOLS =
			(DBTools) ConstructorUtils.
			newInstanceFromSytem(DBTools.class.getName(), DBTools.class);
	
	public static DBTools getInstance(){
		return TOOLS;
	}
	
	
	
	public boolean createTable(Class<?> clas){
		List<Field> allFieldsList = FieldUtils.getAllFieldsList(clas);
		for (Field field : allFieldsList) {
			String fieldName = field.getName();
		}
		return true;
	}
	
}
