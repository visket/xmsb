package com.cody.entity.approve;

import java.util.Date;

/**
 * 审批流程-步骤
 * @author around
 *
 */
public class ApproveStep {
	
	private String id;

	/** 流程ID */
    private String processId;
    
    private String processnumber;
    
    /** 步骤编号 */
    private Integer stepnumber;

    /** 上级步骤编号 */
    private Integer parentstepnumber;
    
    /** 上级步骤ID */
    private String parentId;

    /** 步骤名称 */
    private String stepname;
    
    /** 是否上级单位逐级审批 */
    private Integer isparentunit;

    /** 是否最后审批步骤 */
    private Integer islaststep;

    /** 步骤权限-组织 */
    private String stepunitId;

    /** 步骤权限-用户 */
    private Long stepuserId;

    /** 步骤权限-角色 */
    private Long steproleId;

    /** 步骤权限-职务 */
    private String stepjobId;

    /** 步骤类型 */
    private String statusId;

    /** 创建人-发起人 */
    private Long createorId;

    /** 创建时间 */
    private Date createtime;

    /** 修改人 */
    private Long updatorId;

    /** 最后修改时间 */
    private Date lastupdatetime;

    /** 数据标识 */
    private String datasign;

    /** 业务表ID */
    private String bussinessId;

    /** 开始时间 */
    private Date starttime;

    /** 结束时间 */
    private Date endtime;
    
    /** 节点开始状态 */
    private String startstatusId;

    /** 节点结束状态 */
    private String endstatusId;
    
    /** 退回状态 */
    private String backstatusId;
    

    public ApproveStep() {}
    
    public ApproveStep(String id) {
		this.id = id;
	}
    
    
    public Integer getParentstepnumber() {
        return parentstepnumber;
    }

    public void setParentstepnumber(Integer parentstepnumber) {
        this.parentstepnumber = parentstepnumber;
    }

    public String getStepname() {
        return stepname;
    }

    public void setStepname(String stepname) {
        this.stepname = stepname == null ? null : stepname.trim();
    }


    
    public String getStepunitId() {
		return stepunitId;
	}

	public void setStepunitId(String stepunitId) {
		this.stepunitId = stepunitId;
	}

	public Long getStepuserId() {
        return stepuserId;
    }

    public void setStepuserId(Long stepuserId) {
        this.stepuserId = stepuserId;
    }

    public Long getSteproleId() {
        return steproleId;
    }

    public void setSteproleId(Long steproleId) {
        this.steproleId = steproleId;
    }

    public String getStepjobId() {
        return stepjobId;
    }

    public void setStepjobId(String stepjobId) {
        this.stepjobId = stepjobId == null ? null : stepjobId.trim();
    }

    public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public Long getCreateorId() {
        return createorId;
    }

    public void setCreateorId(Long createorId) {
        this.createorId = createorId;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public String getBussinessId() {
        return bussinessId;
    }

    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId == null ? null : bussinessId.trim();
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public Integer getStepnumber() {
		return stepnumber;
	}

	public void setStepnumber(Integer stepnumber) {
		this.stepnumber = stepnumber;
	}

	public Integer getIsparentunit() {
		return isparentunit;
	}

	public void setIsparentunit(Integer isparentunit) {
		this.isparentunit = isparentunit;
	}

	public Integer getIslaststep() {
		return islaststep;
	}

	public void setIslaststep(Integer islaststep) {
		this.islaststep = islaststep;
	}

	public String getDatasign() {
		return datasign;
	}

	public void setDatasign(String datasign) {
		this.datasign = datasign;
	}

	public String getProcessnumber() {
		return processnumber;
	}

	public void setProcessnumber(String processnumber) {
		this.processnumber = processnumber;
	}
    
	public String getStartstatusId() {
		return startstatusId;
	}
	
	public void setStartstatusId(String startstatusId) {
        this.startstatusId = startstatusId == null ? null : startstatusId.trim();
    }

    public String getEndstatusId() {
        return endstatusId;
    }

	public void setEndstatusId(String endstatusId) {
		this.endstatusId = endstatusId == null ? null : endstatusId.trim();
	}

	public String getBackstatusId() {
        return backstatusId;
    }

    public void setBackstatusId(String backstatusId) {
        this.backstatusId = backstatusId == null ? null : backstatusId.trim();
    }

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
    
}