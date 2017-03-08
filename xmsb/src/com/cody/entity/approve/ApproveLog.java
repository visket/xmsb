package com.cody.entity.approve;

import java.util.Date;

/**
 * 审批日志对象- 具体内容
 * @author around
 *
 */
public class ApproveLog {
	
	private String id;

	/** 流程ID */
	private String processId;
	
	/** 上级步骤ID */
	private String parentId;
	
	/** 流程编号 */
	private String processnumber;
	
	/** 步骤ID */
	private String stepId;

	/** 步骤编号 */
	private Integer stepnumber;
	
	private Integer lognumber;
	
	/** 审批人 */
    private Long approvetorId;
    
    private String stepunitId;

    private Long steproleId;
    
    private Long stepuserId;
    

	/** 业务表ID */
    private String businessId;

	/** 备注 */
    private String description;

	/** 创建人 */
    private Long creatorId;

	/** 创建时间 */
    private Date createtime;

	/** 修改人 */
    private Long updatorId;

	/** 最后修改时间 */
    private Date lastupdatetime;

	/** 数据标识 */
    private String datasign;

	/** 业务状态ID */
    private String statusId;

	/** 操作开始时间 */
    private Date starttime;

	/** 操作结束时间 */
    private Date endtime;

	/** 意见 */
    private String opinion;
    
    private String reviewstatusId;

    private String reviewscoreId;

    private String reviewcontent;
    
    
    

    public Long getApprovetorId() {
        return approvetorId;
    }

    public void setApprovetorId(Long approvetorId) {
        this.approvetorId = approvetorId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId == null ? null : businessId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
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

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId == null ? null : statusId.trim();
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

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion == null ? null : opinion.trim();
    }

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getProcessnumber() {
		return processnumber;
	}

	public void setProcessnumber(String processnumber) {
		this.processnumber = processnumber;
	}

	public String getDatasign() {
		return datasign;
	}

	public void setDatasign(String datasign) {
		this.datasign = datasign;
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

	public Integer getLognumber() {
		return lognumber;
	}

	public void setLognumber(Integer lognumber) {
		this.lognumber = lognumber;
	}

	public String getStepunitId() {
		return stepunitId;
	}

	public void setStepunitId(String stepunitId) {
		this.stepunitId = stepunitId;
	}

	public Long getSteproleId() {
		return steproleId;
	}

	public void setSteproleId(Long steproleId) {
		this.steproleId = steproleId;
	}

	public Long getStepuserId() {
		return stepuserId;
	}

	public void setStepuserId(Long stepuserId) {
		this.stepuserId = stepuserId;
	}

	public String getReviewstatusId() {
		return reviewstatusId;
	}

	public void setReviewstatusId(String reviewstatusId) {
		this.reviewstatusId = reviewstatusId;
	}

	public String getReviewscoreId() {
		return reviewscoreId;
	}

	public void setReviewscoreId(String reviewscoreId) {
		this.reviewscoreId = reviewscoreId;
	}

	public String getReviewcontent() {
		return reviewcontent;
	}

	public void setReviewcontent(String reviewcontent) {
		this.reviewcontent = reviewcontent;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	
    
    
}