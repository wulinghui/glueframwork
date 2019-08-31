package com.glueframework.confinger;


/**
 * @author Administrator
 * 基于spi规范
 * 自己的系统在各自的子系统中jar实现这个接口，
 * 用各自的Configer完成注册。
 * 我们这里只给默认的系统配置项。
 */
public interface InitSystemConfiger{
	void registerInitConfiger();
}
