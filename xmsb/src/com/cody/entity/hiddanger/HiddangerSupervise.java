package com.cody.entity.hiddanger;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class HiddangerSupervise {
    private String id;

    @Comparator(comparater=Compare.EQ)
    private String hiddangerId;

    @Comparator(comparater=Compare.LIKE)
    private String applyname;

    private String grade;
    
    private String gradeName;

    private String superviseorg;

    @JSONField(format = "yyyy-MM-dd")
    private Date listingtime;
    
    private String listingtimestr;

    @JSONField(format = "yyyy-MM-dd")
    private Date governterm;
    
    private String governtermstr;

    @JSONField(format = "yyyy-MM-dd")
    private Date disannulterm;
    
    private String disannultermstr;

    private Integer capitalbudget;

    private Integer investedfunds;

    private String termgovern;
    
    /** 创建时间 */
    private Date createtime;
    
    /** 最后修改时间 */
    private Date lastupdatetime;
    
    /** 申请开始时间 */
	@Comparator(comparater=Compare.EGT,column="applytime", splitTime="00:00:00")
	private String applytimeBegin;
	
	/** 申请结束时间 */
	@Comparator(comparater=Compare.ELT,column="applytime", splitTime="23:59:59")
	private String applytimeEnd;

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

    public String getApplyname() {
        return applyname;
    }

    public void setApplyname(String applyname) {
        this.applyname = applyname == null ? null : applyname.trim();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade == null ? null : grade.trim();
    }

    public String getSuperviseorg() {
        return superviseorg;
    }

    public void setSuperviseorg(String superviseorg) {
        this.superviseorg = superviseorg == null ? null : superviseorg.trim();
    }

    public Date getListingtime() {
        return listingtime;
    }

    public void setListingtime(Date listingtime) {
        this.listingtime = listingtime;
    }

    public Date getGovernterm() {
        return governterm;
    }

    public void setGovernterm(Date governterm) {
        this.governterm = governterm;
    }

    public Date getDisannulterm() {
        return disannulterm;
    }

    public void setDisannulterm(Date disannulterm) {
        this.disannulterm = disannulterm;
    }

    public Integer getCapitalbudget() {
        return capitalbudget;
    }

    public void setCapitalbudget(Integer capitalbudget) {
        this.capitalbudget = capitalbudget;
    }

    public Integer getInvestedfunds() {
        return investedfunds;
    }

    public void setInvestedfunds(Integer investedfunds) {
        this.investedfunds = investedfunds;
    }

    public String getTermgovern() {
        return termgovern;
    }

    public void setTermgovern(String termgovern) {
        this.termgovern = termgovern == null ? null : termgovern.trim();
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

	public String getListingtimestr() {
		return listingtimestr;
	}

	public void setListingtimestr(String listingtimestr) {
		this.listingtimestr = listingtimestr;
	}

	public String getGoverntermstr() {
		return governtermstr;
	}

	public void setGoverntermstr(String governtermstr) {
		this.governtermstr = governtermstr;
	}

	public String getDisannultermstr() {
		return disannultermstr;
	}

	public void setDisannultermstr(String disannultermstr) {
		this.disannultermstr = disannultermstr;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
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
		
}