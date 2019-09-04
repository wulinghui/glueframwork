package com.glueframework.dao.plug;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import com.wlh.dao.SqlConfig;
import com.wlh.dao.entity.ParameterIndex;


public  class DecorateSqlTranslatorPlug extends SqlTranslatorPlug{
	private SqlTranslatorPlug plug;
	public DecorateSqlTranslatorPlug(SqlTranslatorPlug plug) {
		super(plug.config,plug.para);
		this.plug = plug;
	} 
	public List<ParameterIndex> getNamedPrepared() {
		return plug.getNamedPrepared();
	}
	public ParameterIndex[] getNamedPreparedArray() {
		return plug.getNamedPreparedArray();
	}
	public Object[] getParams(Map<String, Object> map) {
		return plug.getParams(map);
	}
	public Object[] getParams(Object bean) {
		return plug.getParams(bean);
	}
	public int getParameterIndex() {
		return plug.getParameterIndex();
	}
	public int hashCode() {
		return plug.hashCode();
	}
	public boolean equals(Object obj) {
		return plug.equals(obj);
	}
	public String toString() {
		return plug.toString();
	}
	@Override
	protected int translate(SqlConfig config, CharSequence input, int index,
			Writer out, Object para) throws IOException {
		return plug.translate(config, input, index, out, para);
	}
	
}
