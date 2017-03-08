package com.cody.entity.sys;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
/**
 * 考核标准
 * @author yaoxia
 *
 */
public class HiddangerExamine {
	private String id;

	@Comparator(comparater = Compare.LIKE)
	private String name;// 考核名称

	@Comparator
	private String type;// 考核类别

	private Long totalPoints;// 考核类别总分

	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;// 创建时间

	private Long creatorId;

	private Integer status;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public Long getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(Long totalPoints) {
		this.totalPoints = totalPoints;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}