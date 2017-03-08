package com.cody.entity.equip;

public class EquipChild {
	
    private String id;

    private Integer equipnum;

    private String equiptype;
    
    //装备名关联id
    private String eqstandardid;
    
    private String eqbaseid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getEquipnum() {
        return equipnum;
    }

    public void setEquipnum(Integer equipnum) {
        this.equipnum = equipnum;
    }

    public String getEquiptype() {
        return equiptype;
    }

    public void setEquiptype(String equiptype) {
        this.equiptype = equiptype == null ? null : equiptype.trim();
    }

	public String getEqstandardid() {
		return eqstandardid;
	}

	public void setEqstandardid(String eqstandardid) {
		this.eqstandardid = eqstandardid;
	}

	public String getEqbaseid() {
		return eqbaseid;
	}

	public void setEqbaseid(String eqbaseid) {
		this.eqbaseid = eqbaseid;
	}

}