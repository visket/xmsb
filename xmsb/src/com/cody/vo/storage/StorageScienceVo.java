package com.cody.vo.storage;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
import com.cody.entity.storage.StorageScience;


/**
 * 科技项目入库Vo
 * @author around
 * @date 2017-2-27
 */
public class StorageScienceVo extends StorageScience {

	
	private String storageId;
	
	private String projectNumber;
	
	@Comparator(column="b.applyname", comparater=Compare.LIKE)
	private String projectName;
	
	@JSONField(format = "yyyy-MM-dd")
	private Date projectDeclareTime;
	
	private String classifyId;
	
	private String classifyName;
	
	private String accepttimeStr;
	
	@Comparator(column="s.accepttime",comparater=Compare.EGT,splitTime="00:00:00")
	private String accepttimeBegin;
	
	@Comparator(column="s.accepttime",comparater=Compare.ELT,splitTime="23:59:59")
	private String accepttimeEnd;

	
	private String acceptorName;
	
	private String statusName;
	
	private String unitName;
	
	@Comparator(column="b.projecttype")
	private String projecttype;
	
	
	
	
	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(String classifyId) {
		this.classifyId = classifyId;
	}

	public String getAccepttimeBegin() {
		return accepttimeBegin;
	}

	public void setAccepttimeBegin(String accepttimeBegin) {
		this.accepttimeBegin = accepttimeBegin;
	}

	public String getAccepttimeEnd() {
		return accepttimeEnd;
	}

	public void setAccepttimeEnd(String accepttimeEnd) {
		this.accepttimeEnd = accepttimeEnd;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public Date getProjectDeclareTime() {
		return projectDeclareTime;
	}

	public void setProjectDeclareTime(Date projectDeclareTime) {
		this.projectDeclareTime = projectDeclareTime;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public String getAcceptorName() {
		return acceptorName;
	}

	public void setAcceptorName(String acceptorName) {
		this.acceptorName = acceptorName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getAccepttimeStr() {
		return accepttimeStr;
	}

	public void setAccepttimeStr(String accepttimeStr) {
		this.accepttimeStr = accepttimeStr;
	}

	
	
}
