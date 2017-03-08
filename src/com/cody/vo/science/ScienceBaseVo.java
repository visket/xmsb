package com.cody.vo.science;

import java.util.Date;

import com.cody.entity.science.ScienceBase;


public class ScienceBaseVo extends ScienceBase {

	
	private String applytimeStr;
	
	private String gradeTypeId;
	
	private String gradeTypeName;
	
	private String areaName;

	//项目状态
	private String statusName;
	
	//申请人
	private String applicantName;
	
	//单位名
	private String unitName;
	
	
	//回复状态名
	private String reviewstatusName;
	
	//创建时间自增
	private Date createtimeinc;


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


	public String getUnitName() {
		return unitName;
	}


	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}


	public String getReviewstatusName() {
		return reviewstatusName;
	}


	public void setReviewstatusName(String reviewstatusName) {
		this.reviewstatusName = reviewstatusName;
	}


	public String getApplytimeStr() {
		return applytimeStr;
	}


	public void setApplytimeStr(String applytimeStr) {
		this.applytimeStr = applytimeStr;
	}


	public Date getCreatetimeinc() {
		return createtimeinc;
	}


	public void setCreatetimeinc(Date createtimeinc) {
		this.createtimeinc = createtimeinc;
	}

}
