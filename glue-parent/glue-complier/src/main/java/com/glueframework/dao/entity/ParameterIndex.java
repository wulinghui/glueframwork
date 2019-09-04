package com.glueframework.dao.entity;

/**
 * @author wulinghui
 * @see com.wlh.dao.SqlSessionNamedPrepared.translatorWrap(SqlConfig, Object)
 * 
 * 用户输入和NamedPrepared解析有冲突了的话将无法判断，那个该往后移那个该被替换那个该往前移。
 */
public class ParameterIndex {
	public static final ParameterIndex[] EMPTY_ARRAY = new ParameterIndex[0];
	int index;
	String name;
	// see com.wlh.dao.AbstractSqlSession.fillStatement(PreparedStatement, ParameterIndex[], Map)
	String setMethodName;
	//如果他有值就优先取他，否则去Map里面取name对应的值。
	Object obj; // setObject(index,obj);
	
	public ParameterIndex(int index, String name, String setMethodName,
			Object obj) {
		super();
		this.index = index;
		this.name = name;
		this.setMethodName = setMethodName;
		this.obj = obj;
	}
	/**
	 * @param index
	 * @param name
	 * @param setMethodName 为fillStatement选择方法。
	 */
	public ParameterIndex(int index, String name, String setMethodName) {
		this(index, name, setMethodName, null);
	}
	public ParameterIndex(int index, String name) {
		this(index, name, "");
	}
	public ParameterIndex() {
		super();
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSetMethodName() {
		return setMethodName;
	}
	public void setSetMethodName(String setMethodName) {
		this.setMethodName = setMethodName;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	@Override
	public String toString() {
		return "ParameterIndex [index=" + index + ", name=" + name
				+ ", setMethodName=" + setMethodName + ", obj=" + obj + "]";
	}
	
}
