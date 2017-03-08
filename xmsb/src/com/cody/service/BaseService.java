package com.cody.service;

import org.apache.commons.lang3.StringUtils;

import com.cody.common.utils.UserUtils;
import com.cody.entity.sys.User;
import com.cody.mapper.BaseMapper;

public class BaseService {
	
	
	/**
	 * @Title: dataScopeFilter 
	 * @Description: 数据权限过滤方法
	 * @author：wanhuan
     * @date：2016/11/21
	 * @param @param user 当前用户
	 * @param @param orgAlias 组织结构表别名
	 * @param @param userAlias 用户表别名
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String dataScopeFilter(User user, String orgAlias, String userAlias){
		StringBuilder sqlString = new StringBuilder();
		if (user != null && !user.isAdmin()){
			//当前部门和下级数据
			for (String org : StringUtils.split(orgAlias, ",")){
				sqlString.append(" OR " + org + ".id = '" + user.getOrganizationId() + "'");
				sqlString.append(" OR " + org + ".pids LIKE '" + user.getOrganization().getPids()+ user.getOrganizationId() + ",%'");
			}
			
			// 如果没有全部数据权限，并设置了用户别名，则当前权限为本人；如果未设置别名，当前无权限为已植入权限
			if (StringUtils.isNotBlank(userAlias)){
				for (String ua : StringUtils.split(userAlias, ",")){
					sqlString.append(" OR " + ua + ".id = '" + user.getId() + "'");
				}
			}else {
				for (String oa : StringUtils.split(orgAlias, ",")){
					sqlString.append(" OR " + oa + ".id IS NULL");
				}
			}
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}
	
	/**
	 * @Title: treeScopeFilter 
	 * @Description: 过滤一级树数据权限 
	 * @param @param user
	 * @param @param orgAlias
	 * @param @param userAlias
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public String treeScopeFilter(User user, String orgAlias, String userAlias){
		StringBuilder sqlString = new StringBuilder();
		if (!user.isAdmin()){
			sqlString.append(" OR " + orgAlias + ".pid = " + user.getOrganization().getPid());
		}else{
			sqlString.append(" OR "+ orgAlias +".pid = 0 ");
		}
		if (StringUtils.isNotBlank(sqlString.toString())){
			return " AND (" + sqlString.substring(4) + ")";
		}
		return "";
	}
	
	public User getCurrentUser(){
		return (User) UserUtils.getCurrentUser();
	}
}
