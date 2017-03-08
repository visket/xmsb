package com.cody.entity.project;

import java.util.Date;

/**
 * 项目申报信息
 * @author around
 *
 */
public class ProjectDeclare {
	
	
    private String id;

    /** 项目编码 */
    private String number;

    /** 项目名称 */
    private String name;
    
    /** 关联项目 */
    private String projectId;

    /** 创建人 */
    private Long creatorId;

    /** 创建时间 */
    private Date createtime;

    /** 最后修改时间 */
    private Date lastupdatetime;

    /** 修改人 */
    private Long updatorId;

    /** 项目内容  */
    private String content;

    /** 备注 */
    private String description;

    
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }

    public Long getUpdatorId() {
        return updatorId;
    }

    public void setUpdatorId(Long updatorId) {
        this.updatorId = updatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
    
    
}