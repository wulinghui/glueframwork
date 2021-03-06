package com.glueframework.complier;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import com.glueframework.boilerplate.common.IJdtConvert;
import com.glueframework.boilerplate.common.JdCoreUtil;
import com.glueframework.common.lang.Constant;
import com.glueframework.common.util.CombinationClassLoader;
import com.glueframework.commons.CompileTool;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class JdtLoader extends ClassLoader{

	protected static ILogger logger = LogMSG.getLogger();
    /* Call MyApp.main(). */
	
	public static final String HOSTWARP_FLAG = System.getProperty(JdtLoader.class.getName(), "true"); 
	public static final JdtLoader ONE = new JdtLoader(); 
    
    private JdtLoader()  {
    	super(null);
    }
	/*  
     * Finds a specified class.
     * The bytecode for that class can be modified.
     */
	public Class findClass(String name) throws ClassNotFoundException {

		StringBuilder sb = new StringBuilder();
		Class<?> c = null;
		Exception e1 = null;
		try {
			// *modify the CtClass object here*
			String internalName = name.replace(".", "/");
			InputStream is = null;
			is = this.getClass().getResourceAsStream(
					"/" + internalName + ".class");
			byte[] b = handler(is, name, internalName, sb);
			sb.append("internalName=");
			sb.append(internalName);
			c = defineClass(name, b, 0, b.length);
		} catch (Exception e) {
			e1 = e;
			sb.append("Exception=");
			sb.append(e.getMessage());
			sb.append("name=");
			sb.append(name);
			sb.append("if decompile and compile fail ,please go to [https://github.com/java-decompiler/jd-core/issues] create issues ");
		}

		if (c == null)
			c = getSystemClassLoader().loadClass(name);
		if( e1 !=null){
			logger.warn(e1);
			logger.warn(sb.toString());
		}else{
			logger.debug(sb.toString());
		}
		return c;
	}
    
    public static void setDefaultContextClassLoaderOfCurrentThread(){
    	Thread currentThread = Thread.currentThread();
		currentThread.setContextClassLoader( new CombinationClassLoader( getInstance()  ,
				currentThread.getContextClassLoader()));
    }
    
    /**通过工厂实现支不支持热加载。
     * 注意:
     * @return
     */
    public static JdtLoader getInstance(){
	    if( "true".equals(HOSTWARP_FLAG) ){
	    	return new JdtLoader(); 
		}else{
			return ONE;
		}
    }

	protected byte[] handler(InputStream is,String name,String internalName , StringBuilder logSb)
 throws Exception {
		byte[] byteArray = null;
		File file = new File( Constant.CLASS_LOAD_TMP_DIR_VALUE , internalName+".class");
		if( file.exists()  && file.isFile() ){
			logSb.append(" \t  no decompile handler \t  ");
			byteArray = FileUtils.readFileToByteArray(file);
		}else{
			logSb.append(" \t  JdtLoader decompile handler start javaCodes[=\n  ");
			// 反编译
			String javaCodes = JdCoreUtil.decompile(internalName);
			// 交给程序2次处理
			javaCodes = IJdtConvert.DEFAULT.doHandle(javaCodes);
			logSb.append(javaCodes);
			logSb.append("]\t");
			// 编译
			CompileTool tool = new CompileTool(name);
			tool.compile(javaCodes);
			// 获得输出路径
			File outFile = tool.getOutFile();
			// 读取字节。
			byteArray = IOUtils.toByteArray(outFile.toURL());
			// 
			//URL resource = this.getClass().getResource("/");
		}
		logSb.append(" \t  JdtLoader handler ok \t  ");
		return byteArray;
	}
	
}
