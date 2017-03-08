package com.cody.common.utils.object;

import java.util.Map;

import com.google.common.collect.Maps;


/**
 * 验证数据库对象
 * @author around
 * @date 2017-2-24
 */
public class ValidInfo {

	
	/** 主键 */
	private String id;
	
	/** 表名 */
	private String tablename;
	
	/** 字段名 */
	private String field;
	
	/** 字段值 */
	private String value;
	
	private String fields;
	
	private String values;
	
	private String title;
	
	private String statusField;
	
	private String statusValue;
	
	private boolean status;
	
	/** where条件集合 */
	private Map<String, Object> condition = Maps.newHashMap();
	
	public ValidInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public Map<String, Object> getCondition() {
		return condition;
	}

	public void setCondition(Map<String, Object> condition) {
		this.condition = condition;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatusField() {
		return statusField;
	}

	public void setStatusField(String statusField) {
		this.statusField = statusField;
	}

	public String getStatusValue() {
		return statusValue;
	}

	public void setStatusValue(String statusValue) {
		this.statusValue = statusValue;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
}
