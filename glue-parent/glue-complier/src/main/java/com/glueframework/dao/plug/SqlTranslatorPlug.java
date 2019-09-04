package com.glueframework.dao.plug;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.text.translate.CharSequenceTranslator;

import com.wlh.dao.SqlConfig;
import com.wlh.dao.entity.ParameterIndex;
import com.wlh.log.ILogger;
import com.wlh.log.LogMSG;

/**
 * @author wulinghui
 *本来想使用org.apache.commons.text.translate.AggregateTranslator类的。
 *但是他是子类一返回非0的就退出了，我们这里应该是需要累积的。
 *我们这里一开始是想使用策略模式实现的，但是有许多地方都不同级的，而且是都需要执行，
 *只不过是执行增强功能。
 */
public abstract class SqlTranslatorPlug extends CharSequenceTranslator {
	public static ILogger logger = LogMSG.getLogger();

	public abstract List<ParameterIndex> getNamedPrepared();

	public abstract ParameterIndex[] getNamedPreparedArray();

	/**从map里面找到对应namedPrepared的值放入数组中。
	 * @param map
	 * @return
	 */
	public abstract Object[] getParams(Map<String, Object> map);

	public abstract Object[] getParams(Object bean);

	public abstract int getParameterIndex();


	protected SqlConfig config;
	protected Object para;
	public SqlTranslatorPlug(SqlConfig config, Object para) {
		this.config = config;
		this.para = para;
	}
	@Override
	public final int translate(CharSequence input, int index, Writer out)
			throws IOException {
		return translate( config , input, index, out ,para);
	}
	protected abstract int translate(SqlConfig config , CharSequence input, int index, Writer out , Object para)
			throws IOException;
}
