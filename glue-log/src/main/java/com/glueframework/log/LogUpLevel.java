package com.glueframework.log;

import java.util.Map;

/**
 * @author wulinghui
 *	用户选中-是否可以分析   可以分析，提升日志等级
 *  应用场景
 *  1. 配置文件配置的debug级别但是我想打印出调试的trace级别的日志,上线后调试必现的bug
 *  2. 用户需要保护用户隐私。 把级别放低，就可以变成可选了。
 */
public class LogUpLevel extends LoggerAdapt{
	public static final String WARN = "warn";
	public static final String ERROR = "error";
	public static final String INFO = "info";
	public static final String DEBUG = "debug";
	public static final String TRACE = "trace";
	public static final String FLAG = "LogUpLevel-FLAG";
	public static ThreadLocal<Map> THREAD_LOCAL;
//	public static final ThreadLocal<String> THREAD_LOCAL; //这里代码是安全的，但是不好使用。
	
	public LogUpLevel(ILogger iLogger) {
		super(iLogger);
	}
	private boolean upLevel(String arg0, Object... arg1){
		if(THREAD_LOCAL == null) return false;
		Map map = THREAD_LOCAL.get();
		String flag = "";
		if( map != null){
			flag = (String) map.get(FLAG);
		}
		
		switch (flag) {
		case TRACE:
			return super.trace(arg0, arg1);
		case DEBUG:
			return super.debug(arg0, arg1);
		case INFO:
			return super.info(arg0, arg1);
		case ERROR:
			return super.error(arg0, arg1);
		case WARN:
			return super.warn(arg0, arg1);
		default:
			return false;
		}
	}
	private boolean upLevel(String arg0,Throwable arg1){
		if(THREAD_LOCAL == null) return false;
		Map map = THREAD_LOCAL.get();
		String flag = "";
		if( map != null){
			flag = (String) map.get(FLAG);
		}
		
		switch (flag) {
		case TRACE:
			return super.trace(arg0, arg1);
		case DEBUG:
			return super.debug(arg0, arg1);
		case INFO:
			return super.info(arg0, arg1);
		case ERROR:
			return super.error(arg0, arg1);
		case WARN:
			return super.warn(arg0, arg1);
		default:
			return false;
		}
	}
	private boolean upLevel(String arg0){
		if(THREAD_LOCAL == null) return false;
		Map map = THREAD_LOCAL.get();
		String flag = "";
		if( map != null){
			flag = (String) map.get(FLAG);
		}
		
		switch (flag) {
		case TRACE:
			return super.trace(arg0);
		case DEBUG:
			return super.debug(arg0);
		case INFO:
			return super.info(arg0);
		case ERROR:
			return super.error(arg0);
		case WARN:
			return super.warn(arg0);
		default:
			return false;
		}
	}
	public boolean debug(String arg0, Object... arg1) {
		if( super.debug(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean debug(String arg0, Throwable arg1) {
		if( super.debug(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean debug(String arg0) {
		if( super.debug(arg0)){
			return true;
		}else{
			return upLevel(arg0);
		}
	}
	public boolean error(String arg0, Object... arg1) {
		if( super.error(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean error(String arg0, Throwable arg1) {
		if( super.error(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean error(String arg0) {
		if( super.error(arg0)){
			return true;
		}else{
			return upLevel(arg0);
		}
	}
	public String getName() {
		return super.getName();
	}
	public boolean info(String arg0, Object... arg1) {
		if( super.info(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean info(String arg0, Throwable arg1) {
		if( super.info(arg0, arg1)){
			return true;
		}else{
			return upLevel(arg0, arg1);
		}
	}
	public boolean info(String arg0) {
		if( super.info(arg0)){
			return true;
		}else{
			return upLevel(arg0);
		}
	}
	public boolean trace(String arg0, Object... arg1) {
		if( super.trace(arg0,arg1)){
			return true;
		}else{
			return upLevel(arg0,arg1);
		}
	}
	public boolean trace(String arg0, Throwable arg1) {
		if( super.trace(arg0,arg1)){
			return true;
		}else{
			return upLevel(arg0,arg1);
		}
	}
	public boolean trace(String arg0) {
		if( super.trace(arg0)){
			return true;
		}else{
			return upLevel(arg0);
		}
	}
	public boolean warn(String arg0, Object... arg1) {
		if( super.warn(arg0,arg1)){
			return true;
		}else{
			return upLevel(arg0,arg1);
		}
	}
	public boolean warn(String arg0, Throwable arg1) {
		if( super.warn(arg0,arg1)){
			return true;
		}else{
			return upLevel(arg0,arg1);
		}
	}
	public boolean warn(String arg0) {
		if( super.warn(arg0)){
			return true;
		}else{
			return upLevel(arg0);
		}
	}
}
