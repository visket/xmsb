package com.cody.common.utils;


/**
 * 工具实现类
 * @author 周围
 * @date 2016-5-27
 */
public class DataUtil {

	
	
	public static boolean isNull(String str) {
		if(null == str)
			return true;
		else if("".equals(str))
			return true;
		else if("0".equals(str.trim()))
			return true;
		else
			return false;
	}
		
	public static boolean isNotNull(String str) {
		if(null == str)
			return false;
		else if("".equals(str))
			return false;
		else if(str.trim().length() == 0)
			return false;
		else
			return true;
	}
	
	public static boolean isNotNull(String str, String temp) {
		if(null == str)
			return false;
		else if("".equals(str))
			return false;
		else
			if(str.equals(temp)) return true;
			else return false;
	}
	
	
	public static boolean isNotNull(Integer value) {
		if(null == value)
			return false;
		else if("".equals(value))
			return false;
		else
			return true;
	}
	
	public static boolean isNotNull(Object obj) {
		if(null == obj)
			return false;
		else if("".equals(obj.toString()))
			return false;
		else if("0".equals(obj.toString()))
			return false;
		else
			return true;
	}
	
	public static boolean isNull(Object obj) {
		return isNotNull(obj) ? false : true;
	}
	
}
