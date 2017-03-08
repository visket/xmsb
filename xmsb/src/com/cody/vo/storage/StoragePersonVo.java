package com.cody.vo.storage;

import com.cody.entity.storage.StoragePerson;



/**
 * 项目入库-验收人员名单
 * @author around
 * @date 2017-3-6
 */
public class StoragePersonVo extends StoragePerson {

	
	private String postName;
	
	private String majorName;
	
	private String typeName;
	
	private String creatorName;
	
	private String updatorName;

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}
	
	
	
	
}
