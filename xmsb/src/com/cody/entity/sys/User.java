package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description：用户
 * @author：wanhuan
 * @date：2016/11/18
 */
public class User {

	private Long id;

	private String loginname;

	private String name;

	private String password;

	private Integer sex;

	private Integer age;

	private Integer usertype;

	private Integer status;

	private Integer organizationId;

	private String jobId;

	private String isCompany;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createdate;

	private String phone;

	@JSONField(serialize = false)
	private Organization organization = new Organization();

	private String unitId;// 部门ID
	
	private String unName;

	private String newusertype; // 用户类型 专家;

	private String tradetype; // 行业类别

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname == null ? null : loginname.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public boolean isAdmin() {
		return loginname.equals("admin");
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + id + ", loginname='" + loginname + '\''
				+ ", name='" + name + '\'' + ", password='" + password + '\''
				+ ", sex=" + sex + ", age=" + age + ", usertype=" + usertype
				+ ", status=" + status + ", organizationId=" + organizationId
				+ ", createdate=" + createdate + ", phone='" + phone + '\''
				+ '}';
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getIsCompany() {
		return isCompany;
	}

	public void setIsCompany(String isCompany) {
		this.isCompany = isCompany;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public String getUnName() {
		return unName;
	}

	public void setUnName(String unName) {
		this.unName = unName;
	}
	
	public String getNewusertype() {
		return newusertype;
	}

	public void setNewusertype(String newusertype) {
		this.newusertype = newusertype;
	}

	public String getTradetype() {
		return tradetype;
	}

	public void setTradetype(String tradetype) {
		this.tradetype = tradetype;
	}

}