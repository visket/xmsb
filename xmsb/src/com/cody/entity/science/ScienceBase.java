package com.cody.entity.science;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

/**
 * 科技项目
 * @author around
 * @date 2017-2-13
 */
public class ScienceBase {
    
	
	private String id;
	
	/** 申请开始时间 */
	@Comparator(comparater=Compare.EGT,column="applytime", splitTime="00:00:00")
	private String applytimeBegin;
	
	/** 申请结束时间 */
	@Comparator(comparater=Compare.ELT,column="applytime", splitTime="23:59:59")
	private String applytimeEnd;

	/** 申请编码 */
	@Comparator(comparater=Compare.LIKE)
    private String applycode;

	/** 申请名称 */
	@Comparator(comparater=Compare.LIKE)
    private String applyname;

	/** 申请日期 */
    @JSONField(format = "yyyy-MM-dd")
    private Date applytime;
    
    /** 申请日期字符串 */
    private String applytimeStr;

	/** 申请人 */
    @Comparator
    private Long applicant;
    
    /** 项目状态 */
    private String typeId;

	/** 项目状态 */
    private String statusId;

	/** 申报单位 */
    @Comparator(column="unit_id")
    private String unitId;

	/** 流程ID */
    private String processId;

	/** 操作日志ID */
    private String logId;

	/** 最后操作日志ID */
    private String reviewstatusId;

	/** 最后操作日志评价 */
    private String reviewcotent;

	/** 项目分类 */
    private String classifyId;

	/** 创建人 */
    private Long creatorId;

	/** 修改人 */
    private Long updatorId;

	/** 区域ID */
    private String areaId;

	/** 创建时间 */
    private Date createtime;

	/** 最后修改时间 */
    private Date lastupdatetime;

	/** 项目开始时间 */
    private Date starttime;

	/** 项目完成时间 */
    private Date endtime;

	/** 删除状态 */
    private Integer delstatus;
    
    /**项目名称*/
    private String projectname;
    
    /**项目编号*/
    private String projectcode;
    
    /**项目联系人*/
    private String projectcontacts;

    /**联系电话*/
    private String phone;
    
    /**填报日期*/
    @JSONField(format = "yyyy-MM-dd")
    private Date filltime;
    
    /**主要内容及意义*/
    private String primarycoverage;
    
    /**现有工作基础和优势*/
    private String workbase;
    
    /**试点示范情况*/
    private String pilotdemonstration;
    
    /**应用该项目条件与优势*/
    private String conditionaladvantage;
    
    /**考核指标*/
    private String assessmentindex;
    
    /**安全经济社会效益分析*/
    private String benefitanalysis;
    
    /**经费预算*/
    private String budget;
    
    /**项目实施方案*/
    private String projectimplement;
    
    /**项目类别*/
    private String projecttype;
    
    /**承担单位*/
    private String bearunit;
    
    /**参加单位*/
    private String partunit;
    
    /**项目负责人*/
    private String personcharge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplycode() {
        return applycode;
    }

    public void setApplycode(String applycode) {
        this.applycode = applycode == null ? null : applycode.trim();
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname == null ? null : applyname.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public Long getApplicant() {
        return applicant;
    }

    public void setApplicant(Long applicant) {
        this.applicant = applicant;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getProcessId() {
        return processId;
    }

    public void setProcessId(String processId) {
        this.processId = processId == null ? null : processId.trim();
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId == null ? null : logId.trim();
    }
    
   

	public String getReviewstatusId() {
        return reviewstatusId;
    }

    public void setReviewstatusId(String reviewstatusId) {
        this.reviewstatusId = reviewstatusId == null ? null : reviewstatusId.trim();
    }

    public String getReviewcotent() {
        return reviewcotent;
    }

    public void setReviewcotent(String reviewcotent) {
        this.reviewcotent = reviewcotent == null ? null : reviewcotent.trim();
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId == null ? null : classifyId.trim();
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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getDelstatus() {
        return delstatus;
    }

    public void setDelstatus(Integer delstatus) {
        this.delstatus = delstatus;
    }

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getProjectcode() {
		return projectcode;
	}

	public void setProjectcode(String projectcode) {
		this.projectcode = projectcode;
	}

	public String getProjectcontacts() {
		return projectcontacts;
	}

	public void setProjectcontacts(String projectcontacts) {
		this.projectcontacts = projectcontacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getFilltime() {
		return filltime;
	}

	public void setFilltime(Date filltime) {
		this.filltime = filltime;
	}

	public String getPrimarycoverage() {
		return primarycoverage;
	}

	public void setPrimarycoverage(String primarycoverage) {
		this.primarycoverage = primarycoverage;
	}

	public String getWorkbase() {
		return workbase;
	}

	public void setWorkbase(String workbase) {
		this.workbase = workbase;
	}

	public String getPilotdemonstration() {
		return pilotdemonstration;
	}

	public void setPilotdemonstration(String pilotdemonstration) {
		this.pilotdemonstration = pilotdemonstration;
	}

	public String getConditionaladvantage() {
		return conditionaladvantage;
	}

	public void setConditionaladvantage(String conditionaladvantage) {
		this.conditionaladvantage = conditionaladvantage;
	}

	public String getAssessmentindex() {
		return assessmentindex;
	}

	public void setAssessmentindex(String assessmentindex) {
		this.assessmentindex = assessmentindex;
	}

	public String getBenefitanalysis() {
		return benefitanalysis;
	}

	public void setBenefitanalysis(String benefitanalysis) {
		this.benefitanalysis = benefitanalysis;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getProjectimplement() {
		return projectimplement;
	}

	public void setProjectimplement(String projectimplement) {
		this.projectimplement = projectimplement;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}
	
	public String getApplytimeStr() {
		return applytimeStr;
	}

	public void setApplytimeStr(String applytimeStr) {
		this.applytimeStr = applytimeStr;
	}

	public String getBearunit() {
		return bearunit;
	}

	public void setBearunit(String bearunit) {
		this.bearunit = bearunit;
	}

	public String getPartunit() {
		return partunit;
	}

	public void setPartunit(String partunit) {
		this.partunit = partunit;
	}

	public String getPersoncharge() {
		return personcharge;
	}

	public void setPersoncharge(String personcharge) {
		this.personcharge = personcharge;
	}

	public String getApplytimeBegin() {
		return applytimeBegin;
	}

	public void setApplytimeBegin(String applytimeBegin) {
		this.applytimeBegin = applytimeBegin;
	}

	public String getApplytimeEnd() {
		return applytimeEnd;
	}

	public void setApplytimeEnd(String applytimeEnd) {
		this.applytimeEnd = applytimeEnd;
	}

}