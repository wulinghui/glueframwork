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
 * 4. 没有必要使用javassist框架。我们可以自己用反编译来实现。比他更NB
@see #jd
 */
@Deprecated
public class SampleLoader extends ClassLoader {
	protected static ILogger logger = LogMSG.getLogger();
    /* Call MyApp.main(). */
	
	public static final String HOSTWARP_FLAG = System.getProperty(SampleLoader.class.getName(), "true"); 
    
    private Loader javassistLoader;
    public SampleLoader()  {
    	this(new Loader());
    	ClassPool pool = new ClassPool( ClassPool.getDefault() );
        pool.appendSystemPath();
        pool.insertClassPath(new ClassClassPath(this.getClass()));
//      pool.insertClassPath("./class"); // MyApp.class must be there.
        pool.childFirstLookup = true;
        javassistLoader.setClassPool(pool);
    }
    public SampleLoader(ClassPool pool) {
    	this(new Loader());
        javassistLoader.setClassPool(pool);
	}
    
	public SampleLoader(Loader javassistLoader) {
		super(null);
		this.javassistLoader = javassistLoader;
	}
	/*  
     * Finds a specified class.
     * The bytecode for that class can be modified.
     */
    public Class findClass(String name) throws ClassNotFoundException {
    	  
    	StringBuilder sb =new StringBuilder();
    	Class<?> c = 
//    			null;
    	super.findLoadedClass(name); 
    	
//    	sb.append(HOSTWARP_FLAG);
//    	sb.append('\t');
//    	// 不是支持热加载
//    	if( !"true".equals(HOSTWARP_FLAG) && c ==null ){
//    		
//    	}   
    	
    	if( c ==null){   
    		try {
    			// *modify the CtClass object here*
    			String internalName = name.replace(".", "/");
    			InputStream is = null;
    			is = this.getClass().getResourceAsStream("/" + internalName + ".class");
    			byte[] b = handler(is,name,internalName , sb);
    			sb.append("internalName=");
    			sb.append(internalName);
    			c = defineClass(name, b, 0, b.length);
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
    			c =  javassistLoader.loadClass(name);
    		} 
    	}
//        	if (resolve) {
//				resolveClass(c);
//            }
        	logger.debug( sb.toString() ); 
        	
//        	URI uri = null;
//        	URL url = uri.toURL();
//        	InputStream openStream = url.openStream();  
        	return c;
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