package com.glueframework.dao.entity;

import java.io.Serializable;

public interface Value extends Serializable,Cloneable{

	public abstract Object getObj();

	public abstract void setObj(Object obj);

}