package com.glueframework.common.tools;

import com.alibaba.fastjson.JSON;

public class JsonTools {
	public static String bean2Json(Object obj) {
		return JSON.toJSONString(obj);
	}

	public static <T> T json2Bean(String jsonStr, Class<T> objClass) {
		return JSON.parseObject(jsonStr, objClass);
	}
}
