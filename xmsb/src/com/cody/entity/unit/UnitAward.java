package com.cody.entity.unit;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class UnitAward {
	private String id;

	private String unitId; // 单位名称

	private BigDecimal money; // 金额

	private String year; // 年度
	
	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;
	private String sysareaId;

	public String getSysareaId() {
		return sysareaId;
	}

	public void setSysareaId(String sysareaId) {
		this.sysareaId = sysareaId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year == null ? null : year.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}