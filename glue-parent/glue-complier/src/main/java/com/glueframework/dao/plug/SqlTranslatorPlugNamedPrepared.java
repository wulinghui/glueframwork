package com.glueframework.dao.plug;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.wlh.beanutils.BeanUtils;
import com.wlh.dao.SqlConfig;
import com.wlh.dao.entity.ParameterIndex;
import com.wlh.exception.ConvertRunException;

public class SqlTranslatorPlugNamedPrepared extends SqlTranslatorPlug{
	public static final char prefix = '&';
	public static final char suffix = ' ';
	public static final char out = '?';

	private final List<ParameterIndex> namedPrepared = new ArrayList<ParameterIndex>(10);
//	private final AtomicInteger currentIndex = new AtomicInteger(1);
	private int currentIndex = 1;
	
	public SqlTranslatorPlugNamedPrepared(SqlConfig config, Object para) {
		super(config, para);
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.plug.zzz#getNamedPrepared()
	 */
	@Override
	public List<ParameterIndex> getNamedPrepared() {
		return namedPrepared;
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.plug.zzz#getNamedPreparedArray()
	 */
	@Override
	public ParameterIndex[] getNamedPreparedArray() {
		ParameterIndex [] keyArray = new ParameterIndex[this.namedPrepared.size()];
		namedPrepared.toArray(keyArray);
		return keyArray;
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.plug.zzz#getParams(java.util.Map)
	 */
	@Override
	public Object[] getParams(Map<String,Object>  map){
		if(map == null ) return ArrayUtils.EMPTY_OBJECT_ARRAY;
		Object [] valueArray = new Object[currentIndex];
		ParameterIndex parameterIndex;
		for (int i = 0; i < namedPrepared.size(); i++) {
			parameterIndex = namedPrepared.get(i);
			valueArray[parameterIndex.getIndex() - 1] = map.get( parameterIndex.getName() );
		}
		return valueArray;
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.plug.zzz#getParams(java.lang.Object)
	 */
	@Override
	public Object[] getParams(Object bean){
		try {
			Map<String, String> describe = BeanUtils.describe(bean);
			return getParams(describe);
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			throw new ConvertRunException(e);
		}
	}
	/* (non-Javadoc)
	 * @see com.wlh.dao.plug.zzz#getParameterIndex()
	 */
	@Override
	public int getParameterIndex() {
		return currentIndex;
	}
	@Override
	protected int translate(SqlConfig config, CharSequence input, int index,
			Writer out, Object para) throws IOException {
		if(input.charAt(index) == this.out ){
			currentIndex++;
			return 0;
		}
		if( input.charAt(index) == prefix ){
			List<Character> list = new ArrayList<Character>(10);
			char charAt;
			do{
				index++;
				charAt = input.charAt(index);
				if(input.charAt(index) != suffix){
					list.add(charAt);
				}else{
					namedPrepared.add(  new ParameterIndex(currentIndex++, StringUtils.join(list,""))  );
					out.write(this.out); 
					return list.size()+2;
				}
			}while(index < input.length());
		}
		return 0;
	}
	
}
