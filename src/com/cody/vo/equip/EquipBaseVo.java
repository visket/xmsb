package com.cody.vo.equip;

import java.io.Serializable;
import com.cody.entity.equip.EquipBase;

public class EquipBaseVo extends EquipBase implements Serializable {

	
	//项目状态
	private String statusName;
	
	//申请人
	private String applicantName;
	
	//单位名
	private String unitName;
	
	/** 级别名称 */
	private String gradeName;
	
	//回复状态名
	private String reviewstatusName;
	
	private String unitId;
	
	private Integer institutions;// 内设机构数

	private Integer formationcount;// 编制人数
	
	private String orgId;

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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
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

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

}
