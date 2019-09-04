package com.glueframework.dao.entity;


public class ValueImp implements Value {
	Object obj;

	public ValueImp(Object obj) {
		super();
		this.obj = obj;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return obj.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return obj.equals(obj);
	}

	@Override
	public String toString() {
		return obj.toString();
	}

	public ValueImp() {
		super();
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.entity.Value#getObj()
	 */
	@Override
	public Object getObj() {
		return obj;
	}

	/* (non-Javadoc)
	 * @see com.wlh.dao.entity.Value#setObj(java.lang.Object)
	 */
	@Override
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
}
