package com.glueframework.complier;

import com.glueframework.boilerplate.common.IJdtConvert;
import com.glueframework.boilerplate.common.JdtChangeMonitor;

public class CompilerChangeMonitor extends JdtChangeMonitor {
	
	
	public CompilerChangeMonitor(String pathStr, IJdtConvert jdtConvert) {
		super(pathStr, jdtConvert);
	}

	@Override
	protected void afterDo(String srcInner) {
		// 把srcInner,内容编译class，生成到指定目录。
		
	}
}
