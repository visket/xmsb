package com.cody.vo.sys;

import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
import com.cody.entity.sys.Unit;

public class UnitVo extends Unit {

	private String creatorname;// 创建人名称

	private String updatorname;// 修改人名称

	private String typename; // 单位类别名称

	private String tradeTypeName; // 行业类别名称

	@Comparator(comparater = Compare.LIKE)
	private String unitname; // 企业用户名称

	private Long userId;

	private String loginname;

	private String password;

	private Integer userorganizationId;

	private String userorganizationname;

	private String otype; // 组织单位类别

	// 上级部门
	private String parentId;
	private String parentName;

	// 下属部门，
	private String childId;
	private String childName;

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getUpdatorname() {
		return updatorname;
	}

	public void setUpdatorname(String updatorname) {
		this.updatorname = updatorname;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getChildId() {
		return childId;
	}

	public void setChildId(String childId) {
		this.childId = childId;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUserorganizationId() {
		return userorganizationId;
	}

	public void setUserorganizationId(Integer userorganizationId) {
		this.userorganizationId = userorganizationId;
	}

	public String getTradeTypeName() {
		return tradeTypeName;
	}

	public void setTradeTypeName(String tradeTypeName) {
		this.tradeTypeName = tradeTypeName;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getUserorganizationname() {
		return userorganizationname;
	}

	public void setUserorganizationname(String userorganizationname) {
		this.userorganizationname = userorganizationname;
	}

	public String getOtype() {
		return otype;
	}

	public void setOtype(String otype) {
		this.otype = otype;
	}

}
