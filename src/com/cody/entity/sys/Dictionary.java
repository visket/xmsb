package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

public class Dictionary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2482376167830317446L;

	/** 主键ID */
	@Comparator
	private String id;

	/** 代号 */
	@Comparator(comparater = Compare.LIKE)
	private String dictionarycode;

	/** 描述 */
	@Comparator(comparater = Compare.LIKE)
	private String dictionaryvalue;

	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd")
	private Date createtime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDictionarycode() {
		return dictionarycode;
	}

	public void setDictionarycode(String dictionarycode) {
		this.dictionarycode = dictionarycode == null ? null : dictionarycode
				.trim();
	}

	public String getDictionaryvalue() {
		return dictionaryvalue;
	}

	public void setDictionaryvalue(String dictionaryvalue) {
		this.dictionaryvalue = dictionaryvalue == null ? null : dictionaryvalue
				.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}