package com.cody.entity.hiddanger;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
import com.cody.entity.science.ScienceBase;

public class HiddangerDeclare {
	
    private String id;

    @Comparator(comparater=Compare.LIKE)
    private String applyname;

    @Comparator(comparater=Compare.LIKE)
    private String applycode;
    
    /** 申请开始时间 */
	@Comparator(comparater=Compare.EGT,column="applytime", splitTime="00:00:00")
	private String applytimeBegin;
	
	/** 申请结束时间 */
	@Comparator(comparater=Compare.ELT,column="applytime", splitTime="23:59:59")
	private String applytimeEnd;

	/** 申报单位 */
    @Comparator(column="unit_id")
    private String unitId;

    private String projectcontacts;

    private String phone;

    @JSONField(format = "yyyy-MM-dd")
    private Date applytime;

    private String worksummary;
    
    /** 创建时间 */
    private Date createtime;
    
	/** 最后修改时间 */
    private Date lastupdatetime;
    
	/** 申请人Id */
    @Comparator
    private Long applicant;
    
    /**项目类型*/
    private String typeId;
    
    /**项目状态*/
    private String statusId;
    
    /**流程ID*/
    private String processId;
    
    /**操作日志*/
    private String logId;
    
    /**最后流程状态*/
    private String reviewstatusId;
    
    /**回复内容*/
    private String reviewcontent;
    
    /**项目分类-行业类别*/
    private String classifyId;
    
    /**区域*/
    private String areaId;
    
    /**创建人*/
    private Long creatorId;
    
    /**修改人*/
    private Long updatorId;
    
    /**项目开始时间*/
    private Date starttime;
    
    /**结束时间*/
    private Date endtime;
    
    /**删除状态*/
    private int delstatus;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname == null ? null : applyname.trim();
    }

    public String getApplycode() {
        return applycode;
    }

    public void setApplycode(String applycode) {
        this.applycode = applycode == null ? null : applycode.trim();
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId == null ? null : unitId.trim();
    }

    public String getProjectcontacts() {
        return projectcontacts;
    }

    public void setProjectcontacts(String projectcontacts) {
        this.projectcontacts = projectcontacts == null ? null : projectcontacts.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Date getApplytime() {
        return applytime;
    }

    public void setApplytime(Date applytime) {
        this.applytime = applytime;
    }

    public String getWorksummary() {
        return worksummary;
    }

    public void setWorksummary(String worksummary) {
        this.worksummary = worksummary == null ? null : worksummary.trim();
    }

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public Long getApplicant() {
		return applicant;
	}

	public void setApplicant(Long applicant) {
		this.applicant = applicant;
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

	public String getReviewcontent() {
		return reviewcontent;
	}

	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
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

	public int getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(int delstatus) {
		this.delstatus = delstatus;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getReviewstatusId() {
		return reviewstatusId;
	}

	public void setReviewstatusId(String reviewstatusId) {
		this.reviewstatusId = reviewstatusId;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
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
	
}