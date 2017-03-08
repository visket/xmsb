package com.cody.common.utils.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * 自定义注解类-日期处理
 * @author around
 * @Description 开始时间格式化问题
 */
@Retention(RetentionPolicy.RUNTIME) //定义注解应用于反射class文件中。
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.METHOD}) //定义注解应用于字段、方法
@Documented
public @interface StartTime {

	String name() default " 00:00:00";
	
}
