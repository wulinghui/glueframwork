package com.glue.framework.common.lang;

import java.lang.reflect.Array;
import java.util.Objects;


public class ArrayUtils extends org.apache.commons.lang3.ArrayUtils {
	public static int size(Object array){
		return Objects.isNull(array) ? 0 : Array.getLength(array);
	}
}