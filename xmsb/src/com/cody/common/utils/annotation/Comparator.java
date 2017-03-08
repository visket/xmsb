package com.cody.common.utils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解帮助类-实体使用
 * @author around
 * @Description 同时提供给反射生成后台数据调用，定义反射标准，字段操作数据库是否是模糊查询，是否是字段属性，开始时间结束时间格式化问题
 */
@Retention(RetentionPolicy.RUNTIME) //定义注解应用于反射class文件中。
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.METHOD}) //定义注解应用于字段、方法
@Documented
public @interface Comparator {

	/**
	 * 添加比较运算符, default = 
	 * @return String
	 */
	String comparater() default "=";
	
	/**
	 * 待格式化的时间选项, default ""
	 * @return String
	 */
	String splitTime() default "";
	
	
	/**
	 * 自定义字段值，与数据库绑定的字段, default ""
	 * @return String
	 */
	String column() default "";
	
	
}
