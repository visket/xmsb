package com.cody.entity.sys;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;

/**
 * 
 * @author yaoxia
 * 
 */
public class Item implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5285935331576706737L;

	@Comparator
	private String id;

	@Comparator(column = "dictionary_id")
	private String dictionaryId;// 父字典ID

	@Comparator(comparater = Compare.LIKE)
	private String itemcode; // 子字典代号

	@Comparator(comparater = Compare.LIKE)
	private String itemvalue;// 子字典描述

	@Comparator
	@JSONField(format = "yyyy-MM-dd")
	private Date createtime; // 创建时间

	@Comparator
	private String dictionarycode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getDictionaryId() {
		return dictionaryId;
	}

	public void setDictionaryId(String dictionaryId) {
		this.dictionaryId = dictionaryId == null ? null : dictionaryId.trim();
	}

	public String getItemcode() {
		return itemcode;
	}

	public void setItemcode(String itemcode) {
		this.itemcode = itemcode == null ? null : itemcode.trim();
	}

	public String getItemvalue() {
		return itemvalue;
	}

	public void setItemvalue(String itemvalue) {
		this.itemvalue = itemvalue == null ? null : itemvalue.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getDictionarycode() {
		return dictionarycode;
	}

	public void setDictionarycode(String dictionarycode) {
		this.dictionarycode = dictionarycode;
	}

}