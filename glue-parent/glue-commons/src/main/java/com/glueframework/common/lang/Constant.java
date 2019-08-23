package com.glueframework.common.lang;

import java.io.File;
import java.net.URISyntaxException;

import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;


public abstract class Constant {
	public static final ILogger logger = LogMSG.getLogger();
	public final static String environment = SystemUtil.getAndClearProperty("com.glueframework.environment", "dev");
	
	static{
		File classPath = null;
		try {
			classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			classPath = classPath.getParentFile();
			classPath = new File( classPath , System.getProperty("com.glueframework.class_load_tmp_dir", "class_load_tmp_dir"));
			classPath.mkdirs();
			String outDir = classPath.getAbsolutePath() + File.separator;
			System.setProperty("com.glueframework.class_load_tmp_dir", outDir);
		} catch (URISyntaxException e) {
			logger.debug(e);  
		}
	} 
	public final static String class_load_tmp_dir = SystemUtil.getAndClearProperty("com.glueframework.class_load_tmp_dir", "class_load_tmp_dir");
	/**是否是开发环境。
	 * @return
	 */
	public static boolean isDevEnvironment() {
		return "dev".equals(environment);
	}
}
