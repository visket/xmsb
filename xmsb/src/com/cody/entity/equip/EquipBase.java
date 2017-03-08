package com.cody.entity.equip;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class EquipBase {
	
	@Comparator
    private String id;

	//内设机构数量
	@Comparator(comparater=Compare.EQ)
    private Integer internalorg;

	//编制人数
    @Comparator(comparater=Compare.EQ)
    private Integer compile;

    //申请时间
    //@Comparator(comparater=Compare.EQ, splitTime="00:00:00")
    @JSONField(format = "yyyy-MM-dd")
    private Date applytime;

    //申请人
    @Comparator(comparater=Compare.LIKE)
    private Long applicant;
    
    //申请人的名字
    @Comparator(comparater=Compare.LIKE,column="u.name")
    private String applicantName;

    //级别(省，市，县)
    @Comparator(comparater=Compare.EQ,column="b.grade")
    private String grade;

    //申请名称
    @Comparator(comparater=Compare.LIKE)
    private String applyname;

    //单位ID
    @Comparator(comparater=Compare.EQ,column="b.unit_id")//注意这里绑定的是b.unit_id对应查询总记录数时要写成from t_equip_base b
    private String unitId;
    
    //单位名称
    @Comparator(comparater=Compare.LIKE,column="unit.name")
    private String unitName;

    //申请编码
    @Comparator(comparater=Compare.LIKE)
    private String applycode;
    
    /** 最后修改时间 */
    @Comparator(comparater=Compare.ELT, splitTime="23:59:59")
    private Date lastupdatetime;
    
    /**项目状态*/
    @Comparator(comparater=Compare.EQ,column="b.status_id")
    private String statusId;
    
    /**回复状态*/
    @Comparator(comparater=Compare.EQ)
    private String reviewstatusId;
    
    /**回复内容*/
    @Comparator(comparater=Compare.LIKE)
    private String reviewcotent;
    
    /**回复评价*/
    @Comparator(comparater=Compare.LIKE)
    private String reviewscore;
    
    /** 流程ID */
    private String processId;
    
    /** 流程操作ID */
    private String logId;
    
    /** 项目分类 */
    private String classifyId;
    
    /** 创建人 */
    private Long creatorId;
    
    /** 区域ID */
    private String areaId;
    
    /** 创建时间 */
    private Date createtime;
    
    /** 修改人 */
    private Long updatorId;
    
    /** 开始时间 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date starttime;
    
    /** 结束时间 */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    
	/** 申请开始时间 */
	@Comparator(comparater=Compare.EGT,column="applytime", splitTime="00:00:00")
	private String applytimeStr;
	
	/** 申请结束时间 */
	@Comparator(comparater=Compare.ELT,column="applytime", splitTime="23:59:59")
	private String applytimeEnd;
	
	/**删除状态1为正常-1为删除*/
	private int delstatus;
    
    public EquipBase() {}
    
    public EquipBase(String id, Long applicant) {
    	this.id = id;
    	this.applicant = applicant;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getInternalorg() {
        return internalorg;
    }

    public void setInternalorg(Integer internalorg) {
        this.internalorg = internalorg;
    }

    public Integer getCompile() {
        return compile;
    }

    public void setCompile(Integer compile) {
        this.compile = compile;
    }

    public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
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

	public String getUnitId() {
		return unitId;
	}

	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}

	public void setApplycode(String applycode) {
        this.applycode = applycode == null ? null : applycode.trim();
    }

	public String getStatusId() {
		return statusId;
	}

	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	public String getReviewstatusId() {
		return reviewstatusId;
	}

	public void setReviewstatusId(String reviewstatusId) {
		this.reviewstatusId = reviewstatusId;
	}

	public String getReviewcotent() {
		return reviewcotent;
	}

	public void setReviewcotent(String reviewcotent) {
		this.reviewcotent = reviewcotent;
	}

	public String getReviewscore() {
		return reviewscore;
	}

	public void setReviewscore(String reviewscore) {
		this.reviewscore = reviewscore;
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

	public Long getApplicant() {
		return applicant;
	}

	public void setApplicant(Long applicant) {
		this.applicant = applicant;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Date getApplytime() {
		return applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public Date getLastupdatetime() {
		return lastupdatetime;
	}

	public void setLastupdatetime(Date lastupdatetime) {
		this.lastupdatetime = lastupdatetime;
	}

	public String getApplytimeStr() {
		return applytimeStr;
	}

	public void setApplytimeStr(String applytimeStr) {
		this.applytimeStr = applytimeStr;
	}

	public String getApplytimeEnd() {
		return applytimeEnd;
	}

	public void setApplytimeEnd(String applytimeEnd) {
		this.applytimeEnd = applytimeEnd;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public int getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(int delstatus) {
		this.delstatus = delstatus;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
	
}