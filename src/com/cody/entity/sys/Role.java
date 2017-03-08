package com.cody.entity.sys;

import java.io.Serializable;

/**
 * @description：角色
 * @author：wanhuan
 * @date：2016/11/18
 */
public class Role implements Serializable {

    private static final long serialVersionUID = -1756241579303707517L;

    private Long id;

    private String name;

    private Integer seq;

    private String description;

    private Integer status;
    
    private Long orgid;
    
    private String orgName;
    
    private String orgids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    
    public Long getOrgid() {
		return orgid;
	}

	public void setOrgid(Long orgid) {
		this.orgid = orgid;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	@Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", seq=" + seq +
                ", orgid=" + orgid +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

	public String getOrgids() {
		return orgids;
	}

	public void setOrgids(String orgids) {
		this.orgids = orgids;
	}
	
	
}