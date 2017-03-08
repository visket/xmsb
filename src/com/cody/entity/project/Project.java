package com.cody.entity.project;

import java.math.BigDecimal;

import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;


/**
 * 项目基础信息
 * @author around
 *
 */
public class Project {
	
	
	@Comparator
	private String id;

    /** 项目编号 */
	@Comparator(comparater=Compare.LIKE)
    private String number;

    /** 审批流程ID */
	@Comparator
    private String processId;
	
	private String logId;

    /** 项目名称 */
	@Comparator(comparater=Compare.LIKE)
    private String name;

    /** 项目开始时间 */
	@Comparator(comparater=Compare.EGT, splitTime="00:00:00")
    private String starttime;

    /** 项目结束时间 */
	@Comparator(comparater=Compare.ELT, splitTime="23:59:59")
    private String endtime;

    /** 项目类型 */
	@Comparator(column="type_id", comparater=Compare.EQ)
    private String typeId;

    /** 项目分类 */
    private String classifyId;

    /** 项目类别 */
    private String classesId;

    /** 项目状态 */
    private String statusId;

    /** 关联单位ID */
    private String unitId;

    /** 创建时间 */
    @Comparator(comparater=Compare.EGT, splitTime="00:00:00")
    private String createtime;

    /** 创建人 */
    private Long creatorId;

    /** 最后修改时间 */
    @Comparator(comparater=Compare.ELT, splitTime="23:59:59")
    private String lastupdatetime;

    /** 修改人 */
    private Long updatorId;

    /** 项目负责人 */
    private Long principaltorId;

    /** 总造价 */
    private BigDecimal cost;

    /** 回复状态 */
    private String reviewstatus;

    /** 回复内容 */
    private String reviewcontent;

    /** 回复评价 */
    private String reviewscore;

    /** 区域ID */
    private String areaId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId == null ? null : classifyId.trim();
    }

    public String getClassesId() {
        return classesId;
    }

    public void setClassesId(String classesId) {
        this.classesId = classesId == null ? null : classesId.trim();
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
		this.unitId = unitId;
	}

	public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
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

	public Long getPrincipaltorId() {
		return principaltorId;
	}

	public void setPrincipaltorId(Long principaltorId) {
		this.principaltorId = principaltorId;
	}

	public String getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(String lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }



    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getReviewstatus() {
        return reviewstatus;
    }

    public void setReviewstatus(String reviewstatus) {
        this.reviewstatus = reviewstatus == null ? null : reviewstatus.trim();
    }

    public String getReviewcontent() {
        return reviewcontent;
    }

    public void setReviewcontent(String reviewcontent) {
        this.reviewcontent = reviewcontent == null ? null : reviewcontent.trim();
    }

    public String getReviewscore() {
        return reviewscore;
    }

    public void setReviewscore(String reviewscore) {
        this.reviewscore = reviewscore == null ? null : reviewscore.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }
}