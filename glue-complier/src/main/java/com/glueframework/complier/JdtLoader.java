package com.glueframework.complier;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.glueframework.boilerplate.common.IJdtConvert;
import com.glueframework.boilerplate.common.JdCoreUtil;
import com.glueframework.boilerplate.javassist.SampleLoader;
import com.glueframework.commons.CompileTool;

public class JdtLoader extends SampleLoader{

	@Override
	protected byte[] handler(InputStream is,String name,String internalName , StringBuilder logSb)
			throws IOException {
		byte[] byteArray = null;
		try {
			// 反编译
			String javaCodes = JdCoreUtil.decompile(internalName);
			// 交给程序2次处理
			javaCodes = IJdtConvert.DEFAULT.doHandle(javaCodes);
			// 编译
			CompileTool tool = new CompileTool(name);
			tool.compile( javaCodes);
			// 获得输出路径
			File outFile = tool.getOutFile();
			// 读取字节。
			byteArray = IOUtils.toByteArray(outFile.toURL());
			logSb.append(" \t  JdtLoader handler ok \t  ");
		} catch (Exception e){
			byteArray = super.handler(is, name, internalName,logSb);
		}
		return byteArray;
	}
	
}
