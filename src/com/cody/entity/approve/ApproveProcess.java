package com.cody.entity.approve;

import java.util.Date;

/**
 * 审批流程
 * @author around
 *
 */
public class ApproveProcess {
	
    private String id;

    /** 流程编码 */
    private String number;

    /** 审批名称 */
    private String name;

    /** 关联项目 */
    private String projectId;

    /** 创建人-发起人 */
    private Long creatorId;

    /** 创建时间 */
    private Date createtime;

    /** 修改人 */
    private Long updatorId;

    /** 最后修改时间 */
    private Date lastupdatetime;

    /** 数据标识 */
    private String datasignId;
    
    /** 状态 */
    private String statusId;

    /** 备注 */
    private String description;

    /** 流程发起时间 */
    private Date starttime;

    /** 流程结束时间 */
    private Date endtime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
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

    public String getDatasignId() {
        return datasignId;
    }

    public void setDatasignId(String datasignId) {
        this.datasignId = datasignId == null ? null : datasignId.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
    
    
    
}