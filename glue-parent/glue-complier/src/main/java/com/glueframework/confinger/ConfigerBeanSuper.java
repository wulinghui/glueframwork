package com.glueframework.confinger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.glueframework.common.lang.Constant;
import com.glueframework.common.tools.JsonTools;
import com.glueframework.commons.DBTools;
import com.glueframework.commons.DateTools;

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
public class ConfigerBeanSuper {
	public final String PREFIX = "glue_confige_";
	// 主键
	String _key_; 
	String _environment_;
	String _groupId_;
	String _artifactId_;
	//  内容
	String _value_; 
	// 版本
	String _version_;
	String create_Time;
	String update_Time = DateTools.date2Str(new Date(),DateTools.DATE_FORMAT_MSEC);
	private static final List<Class> tmp = new ArrayList<Class>();
	{
		Class<? extends ConfigerBeanSuper> class1 = this.getClass();
		if( !tmp.contains(class1)){
			DBTools instance = DBTools.getInstance();
			if( Constant.isDevEnvironment()) {
				instance.drop(tableName());
				instance.drop(tableHistoryName());
			}
			instance.createTable(class1 , tableName());
			instance.createTable(class1 , tableHistoryName());
			tmp.add(class1);
		}
	}

	public String get_key_() {
		return _key_;
	}
	public void set_key_(String _key_) {
		this._key_ = _key_;
	}
	public String get_environment_() {
		return _environment_;
	}
	public void set_environment_(String _environment_) {
		this._environment_ = _environment_;
	}
	public String get_groupId_() {
		return _groupId_;
	}
	public void set_groupId_(String _groupId_) {
		this._groupId_ = _groupId_;
	}
	public String get_artifactId_() {
		return _artifactId_;
	}
	public void set_artifactId_(String _artifactId_) {
		this._artifactId_ = _artifactId_;
	}
	public String get_version_() {
		return _version_;
	}
	public void set_version_(String _version_) {
		this._version_ = _version_;
	}
	public String getUpdate_Time() {
		return update_Time;
	}
	public void setUpdate_Time(String update_Time) {
		this.update_Time = update_Time;
	}
	public final String tableName(){
		return PREFIX+this.getClass().getSimpleName();
	}
	public final String tableHistoryName(){
		return tableName()+"_History";
	}
	public String getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
	}
	public String get_value_() {
		return _value_;
	}
	public void set_value_(String _value_) {
		this._value_ = _value_;
	}
	@Override
	public String toString() {
		return JsonTools.bean2Json(this);
	}
}
