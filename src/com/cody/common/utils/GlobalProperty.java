package com.cody.common.utils;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;



public class GlobalProperty {
	
	//获取当前地址
	public static String getCurradress( HttpServletRequest request)
	{
		 String path = request.getContextPath();
		  String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/"; 
		  return basePath;
		
	}
	
	public  static final String DATEFORMAT_FULLNAME = "yyyy-MM-dd HH:mm:ss";
	
	//获取当前用户信息
//	public User GetCurrUser()
//	{
//		
//	}
	
}
