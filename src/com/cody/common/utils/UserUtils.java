package com.cody.common.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import com.cody.common.core.Constants;

/**
 * @ClassName: UserUtils
 * @Description: 用户工具类
 * @author：wanhuan
 * @date：2016/11/18 
 */

public class UserUtils {
	
	private static Session getSession(){
		return SecurityUtils.getSubject().getSession();
	}
	
	public static Object getCurrentUser(){
    	return getSession().getAttribute(Constants.SESSION_USER);
	}
	
	public static void setCurrentUser(Object currentUser){
		getSession().setAttribute(Constants.SESSION_USER, currentUser);
	}
}
