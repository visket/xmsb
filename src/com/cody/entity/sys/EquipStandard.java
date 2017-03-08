package com.cody.entity.sys;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class EquipStandard {
	private String id;

	@Comparator(comparater = Compare.LIKE)
	private String name; // 装备名称

	private Integer province;// 省级

	private Integer city;// 市级

	private Integer county;// 县级

	private String unit;// 单位

	private Integer leaf;

	private Double price;

	private Integer parentId;

	private Integer institutionNumber;

	private Integer personNumber;

	private String equipcrite;// 配备类型

	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;// 创建时间
	@Comparator
	private String typecode;// 装备类别

	private String countprovincetype;// 省级类别

	private String countcitytype;// 市级类别

	private String countcountytype;// 县级类别

	private int status;// 逻辑删除状态 -1表示已删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

	public Integer getCounty() {
		return county;
	}

	public void setCounty(Integer county) {
		this.county = county;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit == null ? null : unit.trim();
	}

	public Integer getLeaf() {
		return leaf;
	}

	public void setLeaf(Integer leaf) {
		this.leaf = leaf;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getInstitutionNumber() {
		return institutionNumber;
	}

	public void setInstitutionNumber(Integer institutionNumber) {
		this.institutionNumber = institutionNumber;
	}

	public Integer getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(Integer personNumber) {
		this.personNumber = personNumber;
	}

	public String getEquipcrite() {
		return equipcrite;
	}

	public void setEquipcrite(String equipcrite) {
		this.equipcrite = equipcrite;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getCountprovincetype() {
		return countprovincetype;
	}

	public void setCountprovincetype(String countprovincetype) {
		this.countprovincetype = countprovincetype;
	}

	public String getCountcitytype() {
		return countcitytype;
	}

	public void setCountcitytype(String countcitytype) {
		this.countcitytype = countcitytype;
	}

	public String getCountcountytype() {
		return countcountytype;
	}

	public void setCountcountytype(String countcountytype) {
		this.countcountytype = countcountytype;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}