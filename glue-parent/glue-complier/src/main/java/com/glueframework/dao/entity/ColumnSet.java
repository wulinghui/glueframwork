package com.glueframework.dao.entity;

import java.util.List;

/**
 * @author wulinghui
 * 建议使用这个代替List。
 * 主要是List在方法声明时无法获得泛型类型。spring的ResolvableType都没办法。
 * @param <T>
 */
public interface ColumnSet<T> extends List<T>{
	/**这里有2个实现方式，
	 * 1.构造方法就限定。
	 * 2.遍历之后获得最高级别的类型。
	 * @return
	 */
	Class<T> getElementType();
}