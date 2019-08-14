package com.glueframework.confinger;

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
public class ConfigerBean {
	/*
	 <bean id="person" class="com.spring.xmlBean.Person">
        <property name="name" value="Little-Koala"></property>
        <property name="age" value="18"></property>
        <!-- 通过 ref 引用了 address 这个 bean -->
        <property name="address" ref="address"></property>
    </bean>
    
    
    select * from ConfigerBean where value like '%address%' 
	 */
	String value;  // 
	
	
	String key;  // person 
	
	String environment;

	String groupId;
	
	String artifactId;
	
	String version;

	String createTime;
	
	String updateTime;
	// 配置项，他可能在运行时可以修改，运行时不可以修改 ，压根就不可修改 ，修改校验
	String flag;
}
