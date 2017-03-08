package com.cody.entity.prokeyper;

import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class ProjectKeyperson {
	
    private String id;

    @Comparator(comparater=Compare.EQ)
    private String scienceId;

    private String name;

    private String officialcapacity;

    private String professbusiness;

    private String percentile;

    private String sharetask;

    private String belongunit;
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScienceId() {
        return scienceId;
    }

    public void setScienceId(String scienceId) {
        this.scienceId = scienceId == null ? null : scienceId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getOfficialcapacity() {
        return officialcapacity;
    }

    public void setOfficialcapacity(String officialcapacity) {
        this.officialcapacity = officialcapacity == null ? null : officialcapacity.trim();
    }

    public String getProfessbusiness() {
        return professbusiness;
    }

    public void setProfessbusiness(String professbusiness) {
        this.professbusiness = professbusiness == null ? null : professbusiness.trim();
    }

    public String getPercentile() {
        return percentile;
    }

    public void setPercentile(String percentile) {
        this.percentile = percentile == null ? null : percentile.trim();
    }

    public String getSharetask() {
        return sharetask;
    }

    public void setSharetask(String sharetask) {
        this.sharetask = sharetask == null ? null : sharetask.trim();
    }

    public String getBelongunit() {
        return belongunit;
    }

    public void setBelongunit(String belongunit) {
        this.belongunit = belongunit == null ? null : belongunit.trim();
    }
}