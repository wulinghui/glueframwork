package com.glueframework.confinger;

import com.glueframework.common.lang.Constant;
import com.glueframework.commons.DBTools;

/**
 * @author Administrator
 * 1. 数据库可以轻松实现分布式   url name pass
 * 2. select * from 查找配置项，要比配置文件要容易。
 * 3. 备份容易。
 * 4. 一些系统，还是需要数据库表来做配置文件的。
 * 5. 注解特别流行嘛。boot 相当于牺牲了灵活性,实现默认配置。  
 * 用户做一套规范出来的话，会把他想要的配置方式，默认往配置表里存放，甚至都需要写注解。
 * 6. 利用sql批量修改容易。
 */
public class ConfigerBean extends ConfigerBeanSuper{
	/*
	 <bean id="person" class="com.spring.xmlBean.Person">
        <property name="name" value="Little-Koala"></property>
        <property name="age" value="18"></property>
        <!-- 通过 ref 引用了 address 这个 bean -->
        <property name="address" ref="address"></property>
    </bean>
    
    
    select * from ConfigerBean where value like '%address%' 
	 */
//	public final static String TABLE_NAME = "GLUE_CONFIGER";
//	public final static String TABLE_NAME_HISTORY = "GLUE_CONFIGER_HISTORY";
	static{
		String tableName = "GLUE_CONFIGER";
		String tableName2 = "GLUE_CONFIGER_HISTORY";
		DBTools instance = DBTools.getInstance();
		if( Constant.isDevEnvironment()) {
			instance.drop(tableName);
			instance.drop(tableName2);
		}
		instance.createTable(ConfigerBean.class , tableName);
		instance.createTable(ConfigerBean.class , tableName2);
	}
	
	String _key;  // person 
	
	String _environment;

	String _groupId;
	
	String _artifactId;

	final String _create_Time = "" ;
	
	String update_Time = "" ;
	//  对应处理value的handles   配置项，他可能在运行时可以修改，运行时不可以修改 ，压根就不可修改 ，修改校验
	String flag_Class = "com.glueframework.confinger.ConfigerHandle";
	//   这个是给flagValue的值。
	String flag_Value;
	public String getKey() {
		return _key;
	}
	public void setKey(String key) {
		this._key = key;
	}
	public String getEnvironment() {
		return _environment;
	}
	public void setEnvironment(String environment) {
		this._environment = environment;
	}
	public String getGroupId() {
		return _groupId;
	}
	public void setGroupId(String groupId) {
		this._groupId = groupId;
	}
	public String getArtifactId() {
		return _artifactId;
	}
	public void setArtifactId(String artifactId) {
		this._artifactId = artifactId;
	}
	public String getCreateTime() {
		return _create_Time;
	}
	public String getUpdateTime() {
		return update_Time;
	}
	public void setUpdateTime(String updateTime) {
		this.update_Time = updateTime;
	}
	public String getFlagClass() {
		return flag_Class;
	}
	public void setFlagClass(String flagClass) {
		this.flag_Class = flagClass;
	}
	public String getFlagValue() {
		return flag_Value;
	}
	public void setFlagValue(String flagValue) {
		this.flag_Value = flagValue;
	}
	
	
}
