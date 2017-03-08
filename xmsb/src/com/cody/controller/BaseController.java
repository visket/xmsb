package com.cody.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import com.cody.common.utils.StringEscapeEditor;
import com.cody.common.utils.UserUtils;
import com.cody.entity.sys.User;

/**
 * @description：基础 controller
 * @author：wanhuan
 * @date：2016/11/18
 */
public class BaseController {

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		/**
		 * 自动转换日期类型的字段格式
		 */
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));

		/**
		 * 防止XSS攻击
		 */
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true,
				false));
	}

	/**
	 * 获取当前登录用户对象
	 * 
	 * @return
	 */
	public User getCurrentUser() {
		User sessionUser = (User) UserUtils.getCurrentUser();
        return sessionUser;
	}

	/**
	 * 
	 * @Title: getCurrentUserId
	 * @Description: 获取当前登录用户的id
	 * @param @return 设定文件
	 * @return Integer 返回类型
	 * @throws
	 */
	public Integer getCurrentUserId() {
		return getCurrentUser().getId().intValue();
	}

}
