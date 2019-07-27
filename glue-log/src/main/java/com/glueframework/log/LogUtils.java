package com.glueframework.log;

import java.lang.reflect.Array;
import java.util.Collection;

/**
 * @author 梁梁
 * jdk 日志 无法输出debug
 * @see https://zochen.iteye.com/blog/616151
 * Level的五个等级SEVERE（最高值） 、WARNING 、INFO 、CONFIG 、FINE 、FINER 、FINEST（最低值）  。这个不同于log4j  
 * # ILogger: TRACE, DEBUG, INFO, WARN, ERROR
 */
public class LogUtils {
	public static  ILogger logger = LogMSG.getLogger();
	public static void debugCollection(Collection args) {
		debugCollection("",args);
	}
	public static void debugCollection(Object label,Collection args) {
		StringBuilder sb = new StringBuilder(label.toString() );
		sb.append(" ={ \n");
		if( args != null ){
			for (Object object : args) {
				sb.append(object.toString());
				sb.append('\n');
			}
		}
		sb.append(" }");
		logger.debug(sb.toString());
	}
	
	
	public static void debugArray(Object args) {
		debugArray("" ,args);
	}

	public static void debugArray(Object label,Object args) {
		StringBuilder sb = new StringBuilder(label.toString() );
		sb.append(" ={ \n");
		if( args != null ){
			if( args.getClass().isArray()){
				int length = Array.getLength(args);
				for (int i = 0; i < length; i++) {
					sb.append(Array.get(args, i));
					sb.append('\n');
				}
			}
		}
		sb.append(" }");
		logger.debug(sb.toString());
	}
	public static void debugObject(Object args) {
		debugObject("", args);
	}
	public static void debugObject(Object label,Object args) {
		StringBuilder sb = new StringBuilder(label.toString() );
		sb.append(" ={ \n");
		if( args != null ){
			sb.append(args.toString());
		}
		sb.append(" }");
		logger.debug(sb.toString());
	}
	public static void setLogger(ILogger logger) {
		LogUtils.logger = logger;
	}
	
}
