package com.cody.entity.sys;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class HiddangerGrade {
	private String id;

	private String examineId;// 考核标准ID

	private String grade;// 评分标准

	private Long points;// 分值

	private String pointsType;// 分值类型
	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;

	private Long creatorId;

	private Integer status; // -1表示删除

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getExamineId() {
		return examineId;
	}

	public void setExamineId(String examineId) {
		this.examineId = examineId == null ? null : examineId.trim();
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade == null ? null : grade.trim();
	}

	public Long getPoints() {
		return points;
	}

	public void setPoints(Long points) {
		this.points = points;
	}

	public String getPointsType() {
		return pointsType;
	}

	public void setPointsType(String pointsType) {
		this.pointsType = pointsType == null ? null : pointsType.trim();
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