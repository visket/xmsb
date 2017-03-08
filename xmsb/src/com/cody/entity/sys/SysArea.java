package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class SysArea implements Serializable {

	private String id;

	private String parentId;

	private String parentIds;

	private String name;

	// private Byte seq;

	private Short seq;

	// private Integer seqInt;

	private String code;

	private Byte type;

	private Integer createBy;
	@JSONField(format = "yyyy-MM-dd")
	private Date createDate;

	private Integer updateBy;

	@JSONField(format = "yyyy-MM-dd")
	private Date updateDate;

	private String remarks;

	private Byte status;

	private String gradetype;

	private static final long serialVersionUID = 1L;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds == null ? null : parentIds.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	/*
	 * public Byte getSeq() { return seq; }
	 * 
	 * public void setSeq(Byte seq) { this.seq = seq; }
	 */

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code == null ? null : code.trim();
	}

	public Byte getType() {
		return type;
	}

	public void setType(Byte type) {
		this.type = type;
	}

	public Integer getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	/*
	 * public Integer getSeqInt() { return seqInt; }
	 * 
	 * public void setSeqInt(Integer seqInt) { this.seqInt = seqInt; }
	 */

	public Short getSeq() {
		return seq;
	}

	public void setSeq(Short seq) {
		this.seq = seq;
	}

	public String getGradetype() {
		return gradetype;
	}

	public void setGradetype(String gradetype) {
		this.gradetype = gradetype;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getClass().getSimpleName());
		sb.append(" [");
		sb.append("Hash = ").append(hashCode());
		sb.append(", id=").append(id);
		sb.append(", parentId=").append(parentId);
		sb.append(", parentIds=").append(parentIds);
		sb.append(", name=").append(name);
		sb.append(", seq=").append(seq);
		sb.append(", code=").append(code);
		sb.append(", type=").append(type);
		sb.append(", createBy=").append(createBy);
		sb.append(", createDate=").append(createDate);
		sb.append(", updateBy=").append(updateBy);
		sb.append(", updateDate=").append(updateDate);
		sb.append(", remarks=").append(remarks);
		sb.append(", status=").append(status);
		sb.append(", serialVersionUID=").append(serialVersionUID);
		sb.append("]");
		return sb.toString();
	}
}