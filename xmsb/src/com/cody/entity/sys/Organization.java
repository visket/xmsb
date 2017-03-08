package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * @description：部门
 * @author：wanhuan
 * @date：2016/11/18
 */
public class Organization implements Serializable {

	private static final long serialVersionUID = 1282186495210887307L;

	private Long id;

	private String name;

	private String address;

	private String code;

	private String type;

	private String areaId;

	private String pids;

	@JSONField(name = "iconCls")
	private String icon;

	private Long pid;

	private Integer seq;

	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	private Date createdate;

	private String areaName;

	private String unitTypeName;

	private Byte status;

	private Long roleid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon == null ? null : icon.trim();
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getUnitTypeName() {
		return unitTypeName;
	}

	public void setUnitTypeName(String unitTypeName) {
		this.unitTypeName = unitTypeName;
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", address="
				+ address + ", code=" + code + ", type=" + type + ", areaId="
				+ areaId + ", pids=" + pids + ", icon=" + icon + ", pid=" + pid
				+ ", seq=" + seq + ", createdate=" + createdate + "]";
	}

	public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

}