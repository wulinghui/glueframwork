package com.glueframework.boilerplate.javassist;
import java.io.IOException;
import java.io.InputStream;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.Loader;

import org.apache.commons.io.IOUtils;

import com.glueframework.common.util.CombinationClassLoader;
import com.glueframework.log.ILogger;
import com.glueframework.log.LogMSG;

/**
 * @author Administrator
 * 1. 先通过getResourceAsStream()加载class
 * 2. 利用javassist.CtClass 转化.class
 * 3. 最后利用java.lang.ClassLoader加载。
 */
public class SampleLoader extends ClassLoader {
	protected static ILogger logger = LogMSG.getLogger();
    /* Call MyApp.main(). */

    
    private Loader javassistLoader;
    public SampleLoader()  {
    	ClassPool pool = new ClassPool( ClassPool.getDefault() );
        pool.appendSystemPath();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
//      pool.insertClassPath("./class"); // MyApp.class must be there.
        pool.childFirstLookup = true;
        javassistLoader = new Loader();
        javassistLoader.setClassPool(pool);
    }
    public SampleLoader(ClassPool pool) {
    	javassistLoader = new Loader();
        javassistLoader.setClassPool(pool);
	}
    
	public SampleLoader(Loader javassistLoader) {
		super();
		this.javassistLoader = javassistLoader;
	}
	/* 
     * Finds a specified class.
     * The bytecode for that class can be modified.
     */
    public Class loadClass(String name) throws ClassNotFoundException {
    	StringBuilder sb =new StringBuilder();
        	try {
				// *modify the CtClass object here*
				String internalName = name.replace(".", "/");
				InputStream is = null;
				is = this.getClass().getResourceAsStream("/" + internalName + ".class");
				byte[] b = handler(is,name,internalName , sb);
				sb.append("internalName=");
				sb.append(internalName);
				return defineClass(name, b, 0, b.length);
			} catch (Exception e) {
//				logger.warn(e);
				sb.append("Exception=");
				sb.append(e.getMessage());
				sb.append("name=");
				sb.append(name);
//					CtClass cc = pool.get(name);
//					byte[] b = cc.toBytecode();
					sb.append("=CtClass-convert=");
//					return cc.toClass();
					return javassistLoader.loadClass(name);
			} finally{ 
				logger.debug( sb.toString() ); 
			}
//        	URI uri = null;
//        	URL url = uri.toURL();
//        	InputStream openStream = url.openStream();  
    }
	protected byte[] handler(InputStream is,String name,String internalName , StringBuilder logSb) throws IOException {
		logSb.append(" \t  SampleLoader  handler ok  \t ");
		return IOUtils.toByteArray(is);
	}
    
    public static void setDefaultContextClassLoaderOfCurrentThread(){
    	Thread currentThread = Thread.currentThread();
		currentThread.setContextClassLoader( new CombinationClassLoader( new SampleLoader()  ,
				currentThread.getContextClassLoader()));
    }
}