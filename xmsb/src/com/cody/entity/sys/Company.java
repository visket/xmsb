package com.cody.entity.sys;

import java.util.Date;

/**
 * 公司实体
 * @author around
 *
 */
public class Company {
	
    private String id;

    /** 公司名称 */
    private String name;

    /** 所属组织 */
    private Long organizationId;

    /** 所属区域 */
    private Integer areaId;

    /** 创建时间 */
    private Date createtime;

    /** 最后修改时间 */
    private Date lastupdatetime;

    /** 备注 */
    private String description;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
}