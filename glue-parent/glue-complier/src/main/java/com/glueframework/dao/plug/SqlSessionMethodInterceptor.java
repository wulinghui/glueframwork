package com.glueframework.dao.plug;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Future;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.apache.commons.lang3.ClassUtils;

import com.wlh.dao.AbstractSqlConfig;
import com.wlh.dao.SqlConfig;
import com.wlh.dao.SqlHelper;
import com.wlh.util.CollectionUtils;
import com.wlh.util.StringUtils;
import com.wlh.util.TypeResolvable;

/**
 * @author wulinghui
 * Spring jpa 和 mybits
 */
public class SqlSessionMethodInterceptor implements MethodInterceptor {
	
	private static final Set<String> KeyWords =new HashSet();
	
	static{
		KeyWords.add("AND");
		KeyWords.add("OR");
		KeyWords.add("BETWEEN");
		
	}
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		//TODO 
		SqlConfig config = getSqlConfigAndsetSQL(method);
		//设置RETURN_CLASS的值.
		TypeResolvable forMethodReturnType = TypeResolvable.forMethodReturnType(method);
		config.setConfig(config.RETURN_CLASS, forMethodReturnType);
		Object parameter = setParameter(args);
		if( ClassUtils.isAssignable( forMethodReturnType.toClass() , Future.class ) ){
			return SqlHelper.select(config , parameter);
		}else{
			return SqlHelper.select(config , parameter).get();
		}
	}

	protected Object setParameter(Object[] args) {
		//本身就是null , 数组，
		if( args == null || args.length > 1 || args.length ==0 ){
			return args;
		}
		Class<? extends Object> class1 = args[0].getClass();
		// 是java.lang的类
		if(ClassUtils.isPrimitiveOrWrapper(class1) || class1.getName().startsWith("java.lang")){
			return args;
		// 是其它用户扩展类。
		}else{
			return args[0];
		}
	}

	protected static void ndsetSQLgetSqlConfigAndsetSQLgedsetigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetS() {
		        System.out.println("1111111");
	}
	public static void main(String[] args) {
		ndsetSQLgetSqlConfigAndsetSQLgedsetigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLqlConfigAndsetgetSqgetgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetSqlConfigAndsetSQLgetS();
	}
	protected SqlConfig getSqlConfigAndsetSQL( Method method) {
		String[] split = method.toString().split(" ");
		SqlConfig config = null;
		for (String string : split) {
			//string=> com.wlh.dao.ParametersTest.printParameter1(int,java.lang.String)
			if( string.contains("(") && string.contains(")") ){
				//去配置里面找有不有对应的sql语句。
				config = new AbstractSqlConfig(string);
				//config没有sql 或者 放进去的和config的sql一样，说明没有转化
				if( StringUtils.isBlank(config.getConfig(config.SQL)) || 
						config.getConfig(config.SQL).equals(string)){
					//可能是jpa的的语法规定。
					String name = method.getName();
					//转_
					name = new CamelCase2UnderScoreCaseTranslator().translate(name);
					String[] split2 = name.split("_");
					// findZ_user_vdByNameAndPassword   2次解析，同时实现find 表名 By .. 还特别麻烦。
					// selZ_user_vdByNameAndPassword   2次解析，同时实现find 表名 By .. 还特别麻烦。
					// select * from user where name = ? and password = ?
					
					// UPDATE user SET password = ? WHERE name = ?
					
					// DELETE FROM user WHERE name = ?
					
					// INSERT INTO user (name,password) VALUES (?,?)
					StringBuilder sb = new StringBuilder();
					String anObject;
					for (int i = 0; i < split2.length; i++) {
						anObject = split2[i];
						if( "find".equals(anObject)){
							sb.append("SELECT * FROM ");
							sb.append( getTableName(method) );
							
						}else if( "by".equals(anObject)){
							sb.append(" WHERE ");
						}else if( isSqlKeyWords(anObject) ){
							sb.append(anObject);
						}else{
							sb.append(anObject);
							sb.append("=?");
						}
					}
					config.setConfig(config.SQL,sb.toString());
				}
			}
		}
		return config;
	}
	public String getTableName( Method method){
		TypeResolvable forMethodReturnType = TypeResolvable.forMethodReturnType(method);
		Class<?> class1;
		do{
			class1 = forMethodReturnType.toClass();
			if( !class1.isPrimitive() && !class1.getName().startsWith("java.") ){
				return class1.getSimpleName();
			}else{
				forMethodReturnType = forMethodReturnType.getGeneric(0);
			}
		}while(true);
	}
	/**是否是sql的关键字
	 * @param key
	 * @return
	 */
	public static boolean isSqlKeyWords(String key){
		if( StringUtils.isBlank(key)) return false;
		return CollectionUtils.contains(KeyWords, key.toUpperCase() );
	}
}
