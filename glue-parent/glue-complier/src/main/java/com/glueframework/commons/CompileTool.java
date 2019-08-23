package com.glueframework.commons;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import com.glueframework.common.lang.Constant;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

public class CompileTool {
	protected static ILogger logger = LogMSG.getLogger();
	

	String outDir = "";
	final String className;
	String javaCodes;
	File outFile;
	  /**
		 * @param className
		 */
		public CompileTool(String className) {
			super();
			this.className = className;
			this.outDir = Constant.class_load_tmp_dir;
		}
		
	
    /**
	 * @param outDir
	 * @param className
	 */
	public CompileTool(String outDir, String className) {
		super();
		this.outDir = outDir;
		this.className = className;
	}


	/**
     * 装载字符串成为java可执行文件
     * @param className className
     * @param javaCodes javaCodes
     * @return Class        
     */
    public boolean compile(String javaCodes) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        StrSrcJavaObject srcObject = new StrSrcJavaObject(className, javaCodes);
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList(srcObject);
        String flag = "-d";   
        //            File classPath = new File(Thread.currentThread().getContextClassLoader().getResource("").toURI());
		//            outDir = classPath.getAbsolutePath() + File.separator;
		outFile = new File(outDir,className.replace(".", File.separator)+".class");
		logger.debug("classPath={} \n className=[{}] \n src=[{}]   \n file = [{}]  file-exists={}" , outDir , className , javaCodes  , outFile ,outFile.exists() );
        Iterable<String> options = Arrays.asList(flag, outDir);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, fileObjects);
        return task.call();
    }


  


	private static class StrSrcJavaObject extends SimpleJavaFileObject {
        private String content;
        StrSrcJavaObject(String name, String content) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return content;
        }
    }





	public static ILogger getLogger() {
		return logger;
	}

	public String getOutDir() {
		return outDir;
	}

	public String getClassName() {
		return className;
	}
	public String getJavaCodes() {
		return javaCodes;
	}
	public File getOutFile() {
		return outFile;
	}

}
