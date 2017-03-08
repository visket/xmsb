package com.cody.vo.sys;

import java.util.Date;
import java.util.List;
import com.cody.entity.sys.Role;
import com.cody.entity.sys.User;

/**
 * @description：UserVo
 * @author：wanhuan
 * @date：2016/11/18
 */
public class UserVo extends User {

	private List<Role> rolesList;

	private String organizationName;

	private String roleIds;
	
	private String roleId;

	// 前台传递过来是String类型，如果这里用日期接收会报异常
	private Date createdateStart;
	private Date createdateEnd;

	private String createdateStartStr;
	private String createdateEndStr;

	private String unitName;

	private String typename; // 用户类型名称

	private String tradetypename;// 行业类型名称

	public List<Role> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<Role> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}

	public String getCreatedateStartStr() {
		return createdateStartStr;
	}

	public void setCreatedateStartStr(String createdateStartStr) {
		this.createdateStartStr = createdateStartStr;
	}

	public String getCreatedateEndStr() {
		return createdateEndStr;
	}

	public void setCreatedateEndStr(String createdateEndStr) {
		this.createdateEndStr = createdateEndStr;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getTradetypename() {
		return tradetypename;
	}

	public void setTradetypename(String tradetypename) {
		this.tradetypename = tradetypename;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
}