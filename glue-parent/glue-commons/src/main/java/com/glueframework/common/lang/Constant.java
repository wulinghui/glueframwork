package com.glueframework.common.lang;

import static com.glueframework.common.lang.SystemOneConfiguration.SINGLE;

import java.io.File;
import java.net.URISyntaxException;

import com.glueframework.common.exception.ConvertRunException;


public abstract class Constant {
	public final static String ENVIRONMENT_KEY = "com.glueframework.environment";
	public final static String ENVIRONMENT_VALUE = SINGLE.getString(ENVIRONMENT_KEY, "dev");
	
	public final static String CLASS_LOAD_TMP_DIR_KEY = "com.glueframework.class_load_tmp_dir";
	static{
		File classPath = null;
		try {
			classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
			classPath = classPath.getParentFile();
			classPath = new File( classPath , System.getProperty(CLASS_LOAD_TMP_DIR_KEY, "class_load_tmp_dir"));
			classPath.mkdirs();
			String outDir = classPath.getAbsolutePath() + File.separator;
			System.setProperty(CLASS_LOAD_TMP_DIR_KEY, outDir);
		} catch (URISyntaxException e) {
			throw new ConvertRunException(e);
		}
	}
	public final static String CLASS_LOAD_TMP_DIR_VALUE = SINGLE.getString(CLASS_LOAD_TMP_DIR_KEY, "class_load_tmp_dir");
	/**是否是开发环境。
	 * @return
	 */
	public static boolean isDevEnvironment() {
		return "dev".equals(ENVIRONMENT_VALUE);
	}
}
