package com.cody.common.utils.reflect;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cody.common.utils.DataUtil;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
import com.cody.common.utils.tuple.ThreeTuple;
import com.cody.common.utils.tuple.TwoTuple;
import com.cody.entity.project.Project;
import com.google.common.collect.Maps;



/**
 * 属性（对象）值反射获取工具类
 * @author around
 * @date 2016-12-20
 */
public class ParamsReflect {
	
	
	
	/**
	 * 自定义反射封装查询条件，主要用于过滤查询使用
	 * @param source 待取值的对象
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> setParamsByReflect(Object source) {
		Map<String, Object> dynamic = Maps.newHashMap();
		
		Class<?> clazz = source.getClass();
		readFields(source, clazz, dynamic);
		
		return dynamic;
	}
	
	
	/**
	 * 自定义反射封装查询条件，主要用于过滤查询使用(Vo对象请使用这个)
	 * @param source 待取值的对象
	 * @return Map<String, Object>
	 */
	public static Map<String, Object> setAllParamsByReflect(Object source) {
		Map<String, Object> dynamic = Maps.newHashMap();
		
		Class<?> clazz = source.getClass();
		Class<?> superClazz = clazz.getSuperclass();
		
		readFields(source, clazz, dynamic);
		readFields(source, superClazz, dynamic);
		
		return dynamic;
	}
	
	
	/**
	 * 通过fields处理当前对象反射取值绑定
	 * @param source
	 * @param clazz
	 * @param dynamic
	 */
	public static void readFields(Object source, Class<?> clazz, Map<String, Object> dynamic) {
		Object value = null;
		Comparator compara = null;
		String comparaTemp = null; //临时注解值
		String columnTemp = null; //自定义字段值
		
		//获取所有的field对象
		Field[] fields = clazz.getDeclaredFields();
		try {
			for(Field f : fields) {
				f.setAccessible(true);
				if("serialVersionUID".equals(f.getName())) continue;
				if(DataUtil.isNull(f.get(source))) continue;//当没有值直接跳过
				
				//取当前字段值
				value = f.get(source);
				
				//取当前注解
				compara = f.getAnnotation(Comparator.class);
				if(compara != null) {
					comparaTemp = compara.comparater();
					value = setCustomData(compara, value);
				}
				
				//判断是否有自定义字段
				columnTemp = Compare.BLANK.equals(compara.column()) ? f.getName() : compara.column();
				
				dynamic.put(columnTemp + Compare.BLANKSPACE + comparaTemp, value);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	/**
	 * 通过反射获取前端序列化对象值，例如查询条件，直接传入后台
	 * @param source 封装对象
	 * @param temp
	 * @return
	 */
	public static TwoTuple<List<String>, List<Object>> setParamsByReflect(Object source, String temp) {
		//Map<String, Object> dynamic = Maps.newHashMap();
		//List<String> comparator = new ArrayList<String>();
		List<String> keys = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		Class<?> claz = source.getClass();
		Object value = null;
		Comparator compara = null;
		String comparaTemp = null; //临时注解值
		//获取所有的field对象
		Field[] fields = claz.getDeclaredFields();
		try {
			for(Field f : fields) {
				f.setAccessible(true);
				if(f.getName().equals("serialVersionUID")) continue;
				if(DataUtil.isNull(f.get(source))) continue;//当没有值直接跳过
				
				//取当前字段值
				value = f.get(source);
				
				//取当前注释
				compara = f.getAnnotation(Comparator.class);
				if(compara != null) {
					comparaTemp = compara.comparater();
					value = setCustomData(compara, value);
				}
				
				keys.add(f.getName() + Compare.BLANKSPACE + comparaTemp);
				values.add(value);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new TwoTuple<List<String>, List<Object>>(keys, values);
	}
	
	
	public static ThreeTuple<List<String>, List<String>, List<Object>> setParamsByReflect(Object source, Integer temp) {
		Map<String, Object> dynamic = Maps.newHashMap();
		List<String> keys = new ArrayList<String>();
		List<String> comparator = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		
		Class<?> claz = source.getClass();
		Object value = null;
		//获取所有的field对象
		Field[] fields = claz.getDeclaredFields();
		String comparaTemp = null;
		try {
			for(Field f : fields) {
				f.setAccessible(true);
				if(f.getName().equals("serialVersionUID")) continue;
				if(DataUtil.isNull(f.get(source))) continue;//当没有值直接跳过
				
				//取当前字段值
				value = f.get(source);
				
				//取当前注释
				Comparator compara = f.getAnnotation(Comparator.class);
				if(compara != null) {
					comparaTemp = compara.comparater();
					comparator.add(comparaTemp);
					value = setCustomData(compara, value);
				}
				
				
				dynamic.put(f.getName(), value);
				keys.add(f.getName() + Compare.BLANKSPACE + comparaTemp);
				
				values.add(value);
				System.out.println(value);
				
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return new ThreeTuple<List<String>, List<String>, List<Object>>(keys, comparator, values);
	}
	
	
	/**
	 * 自定义处理数据的方法
	 * @param comparator 注解	
	 * @param value Object
	 * @return Object
	 */
	private static Object setCustomData(Comparator comparator, Object value) {

		//判断是否存在其他时间格式的注解，若存在，则取值
		if(!"".equals(comparator.splitTime()))
			value = value + " " + comparator.splitTime();
		
		
		if(Compare.LIKE.equals(comparator.comparater())) {//当比较符为 like时
			value = Compare.PERCENT + value + Compare.PERCENT;
		} 
		
		
		return value;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		Project project = new Project();
		//project.setAreaId("aaa");
		//project.setClassesId("ccc");
		
		//Map<String, Object> condition = Maps.newHashMap();
		//condition = setParamsByReflect(project);
		//System.out.println(condition.toString());
		
		project.setName("asdhg");
		project.setNumber("12355");
		project.setStarttime("2016-11-12");
		
		ThreeTuple<List<String>, List<String>, List<Object>> threeTuple = ParamsReflect.setParamsByReflect(project, 1);
		
		System.out.println(threeTuple.a);
		System.out.println(threeTuple.b);
		System.out.println(threeTuple.c);
	}
	
	
	
	

}
