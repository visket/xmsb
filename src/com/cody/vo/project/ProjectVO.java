package com.cody.vo.project;

import com.cody.entity.project.Project;



/**
 * 项目基本信息扩展VO
 * @author around
 *
 */
public class ProjectVO extends Project {

	private String typename;
	
	private String classifyname;
	
	private String classesname;
	
	private String statusname;
	
	private String unitname;
	
	private String processname;
	
	private String creatorname;
	
	private String updatorname;
	
	private String principaltorname;
	
	private String areaname;

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getClassifyname() {
		return classifyname;
	}

	public void setClassifyname(String classifyname) {
		this.classifyname = classifyname;
	}

	public String getClassesname() {
		return classesname;
	}

	public void setClassesname(String classesname) {
		this.classesname = classesname;
	}

	public String getStatusname() {
		return statusname;
	}

	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getProcessname() {
		return processname;
	}

	public void setProcessname(String processname) {
		this.processname = processname;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String creatorname) {
		this.creatorname = creatorname;
	}

	public String getUpdatorname() {
		return updatorname;
	}

	public void setUpdatorname(String updatorname) {
		this.updatorname = updatorname;
	}

	public String getPrincipaltorname() {
		return principaltorname;
	}

	public void setPrincipaltorname(String principaltorname) {
		this.principaltorname = principaltorname;
	}

	public String getAreaname() {
		return areaname;
	}

	public void setAreaname(String areaname) {
		this.areaname = areaname;
	}
	
}
