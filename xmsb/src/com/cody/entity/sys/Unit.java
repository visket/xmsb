package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

/**
 * @data 2016-12-22
 * @author yaoxia
 * 
 */
public class Unit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5062649055280767842L;

	@Comparator
	private String id; // 主键

	@Comparator(comparater = Compare.LIKE)
	private String unitIdentifier; // 单位编号

	@Comparator(comparater = Compare.LIKE)
	private String name;// 单位名称

	@Comparator
	private String type;// 单位类别 (字典)

	@Comparator
	private String higherLevelIdentifier; // 上级单位编号

	@Comparator(comparater = Compare.LIKE, column = "unit_linkman")
	private String unitLinkman;// 联系人

	@Comparator(comparater = Compare.LIKE)
	private String telephone;// 联系电话

	@Comparator(comparater = Compare.LIKE)
	private String phone;// 手机号

	@Comparator(comparater = Compare.LIKE)
	private String portraiture;// 传真

	@Comparator(comparater = Compare.LIKE)
	private String email;// emal

	@Comparator(comparater = Compare.LIKE)
	private String zipCode;// 邮编

	private String address;// 地址

	private String remarks;// 备注

	private String registerStatus;// 注册状态 (字典)

	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;// 创建时间

	private Long creatorId;// 创建人Id
	@JSONField(format = "yyyy-MM-dd")
	private Date lastupdatetime;// 最后修改时间

	private Long updatorId;// 修改人Id

	private String qq;// qq

	private Integer institutions;// 内设机构数

	private Integer formationcount;// 编制人数

	private String higherLevelName;// 上级单位名称

	private String tradeType; // 行业类别 (字典)

	private Integer organizationId; // 组织ID 跟USER的组织关联 这个只用于安监局注册
	@Comparator(column = "sysarea_id")
	private String sysareaId; // 区域ID
	@Comparator
	private String gradetype;// 区域等级 :省 市 县

	private int status;// 逻辑删除 -1表示删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getUnitIdentifier() {
		return unitIdentifier;
	}

	public void setUnitIdentifier(String unitIdentifier) {
		this.unitIdentifier = unitIdentifier == null ? null : unitIdentifier
				.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getHigherLevelIdentifier() {
		return higherLevelIdentifier;
	}

	public void setHigherLevelIdentifier(String higherLevelIdentifier) {
		this.higherLevelIdentifier = higherLevelIdentifier;
	}

	public String getUnitLinkman() {
		return unitLinkman;
	}

	public void setUnitLinkman(String unitLinkman) {
		this.unitLinkman = unitLinkman == null ? null : unitLinkman.trim();
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone == null ? null : telephone.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPortraiture() {
		return portraiture;
	}

	public void setPortraiture(String portraiture) {
		this.portraiture = portraiture == null ? null : portraiture.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode == null ? null : zipCode.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getRegisterStatus() {
		return registerStatus;
	}

	public void setRegisterStatus(String registerStatus) {
		this.registerStatus = registerStatus == null ? null : registerStatus
				.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq == null ? null : qq.trim();
	}

	public Integer getInstitutions() {
		return institutions;
	}

	public void setInstitutions(Integer institutions) {
		this.institutions = institutions;
	}

	public Integer getFormationcount() {
		return formationcount;
	}

	public void setFormationcount(Integer formationcount) {
		this.formationcount = formationcount;
	}

	public String getHigherLevelName() {
		return higherLevelName;
	}

	public void setHigherLevelName(String higherLevelName) {
		this.higherLevelName = higherLevelName == null ? null : higherLevelName
				.trim();
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Long getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(Long updatorId) {
		this.updatorId = updatorId;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public String getSysareaId() {
		return sysareaId;
	}

	public void setSysareaId(String sysareaId) {
		this.sysareaId = sysareaId;
	}

	public String getGradetype() {
		return gradetype;
	}

	public void setGradetype(String gradetype) {
		this.gradetype = gradetype;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}