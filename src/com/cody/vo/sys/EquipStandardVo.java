package com.cody.vo.sys;

import com.cody.entity.sys.EquipStandard;

public class EquipStandardVo extends EquipStandard {

	private String typevalue;

	private int standarnum;

	private String countprovincevalue;// 省级类别

	private String countcityvalue;// 市级类别

	private String countcountyvalue;// 县级类别

	private String unitvalue;// 单位名称
	
	private String equipcritevalue; // 配置名称
	
	private String standartypename;//标准类型名
	
	private String standartypeid;//标准类型ID
	
	private String eqid;

	private int equipnum;
	
	public String getTypevalue() {
		return typevalue;
	}

	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}

	public int getStandarnum() {
		return standarnum;
	}

	public void setStandarnum(int standarnum) {
		this.standarnum = standarnum;
	}

	public String getCountprovincevalue() {
		return countprovincevalue;
	}

	public void setCountprovincevalue(String countprovincevalue) {
		this.countprovincevalue = countprovincevalue;
	}

	public String getCountcityvalue() {
		return countcityvalue;
	}

	public void setCountcityvalue(String countcityvalue) {
		this.countcityvalue = countcityvalue;
	}

	public String getCountcountyvalue() {
		return countcountyvalue;
	}

	public void setCountcountyvalue(String countcountyvalue) {
		this.countcountyvalue = countcountyvalue;
	}

	public String getUnitvalue() {
		return unitvalue;
	}

	public void setUnitvalue(String unitvalue) {
		this.unitvalue = unitvalue;
	}

	public String getEquipcritevalue() {
		return equipcritevalue;
	}

	public void setEquipcritevalue(String equipcritevalue) {
		this.equipcritevalue = equipcritevalue;
	}

	public String getStandartypename() {
		return standartypename;
	}

	public void setStandartypename(String standartypename) {
		this.standartypename = standartypename;
	}

	public String getStandartypeid() {
		return standartypeid;
	}

	public void setStandartypeid(String standartypeid) {
		this.standartypeid = standartypeid;
	}

	public String getEqid() {
		return eqid;
	}

	public void setEqid(String eqid) {
		this.eqid = eqid;
	}

	public int getEquipnum() {
		return equipnum;
	}

	public void setEquipnum(int equipnum) {
		this.equipnum = equipnum;
	}
	
}
