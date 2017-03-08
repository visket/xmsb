package com.cody.vo.hiddanger;

import java.io.Serializable;
import com.cody.entity.hiddanger.HiddangerDeclare;

public class HiddangerDeclareVo extends HiddangerDeclare implements Serializable {

	private String applytimeStr;
	
	private String unitName;
	
	private String gradeTypeId;
	
	private String gradeTypeName;
	
	private String areaName;

	//项目状态
	private String statusName;
	
	//申请人
	private String applicantName;
	
	//回复状态名
	private String reviewstatusName;
	

	public String getApplytimeStr() {
		return applytimeStr;
	}

	public void setApplytimeStr(String applytimeStr) {
		this.applytimeStr = applytimeStr;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getGradeTypeId() {
		return gradeTypeId;
	}

	public void setGradeTypeId(String gradeTypeId) {
		this.gradeTypeId = gradeTypeId;
	}

	public String getGradeTypeName() {
		return gradeTypeName;
	}

	public void setGradeTypeName(String gradeTypeName) {
		this.gradeTypeName = gradeTypeName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getReviewstatusName() {
		return reviewstatusName;
	}

	public void setReviewstatusName(String reviewstatusName) {
		this.reviewstatusName = reviewstatusName;
	}
	
	
}
