package com.cody.entity.hiddanger;

import java.util.Date;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class HiddangerInfo {
	
    private String id;

    @Comparator(comparater=Compare.EQ)
    private String hiddangerId;

    private String screegovern;

    private String grade;
    
    private String gradeName;

    private Integer quantity;
    
    private String screegovernName;
    
    /** 创建时间 */
    private Date createtime;
    
    /** 最后修改时间 */
    private Date lastupdatetime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHiddangerId() {
        return hiddangerId;
    }

    public void setHiddangerId(String hiddangerId) {
        this.hiddangerId = hiddangerId == null ? null : hiddangerId.trim();
    }

    public String getScreegovern() {
        return screegovern;
    }

    public void setScreegovern(String screegovern) {
        this.screegovern = screegovern == null ? null : screegovern.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getScreegovernName() {
		return screegovernName;
	}

	public void setScreegovernName(String screegovernName) {
		this.screegovernName = screegovernName;
	}
	
}