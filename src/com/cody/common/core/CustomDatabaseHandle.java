package com.cody.common.core;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


/**
 * 自定义封装增删改查sql
 * @author around
 *
 */
public class CustomDatabaseHandle {

	private String statement;
	
	private List<String> fields;
	
	private String tablename;
	
	private Map<String, Object> condition = Maps.newHashMap(); // 查询条件
	
	private Map<String, Object> updates = Maps.newHashMap(); // update修改内容
	
	private Map<String, Object> orders = Maps.newHashMap(); // 排序
	
	private List<String> ids = Lists.newArrayList();

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}

	public List<String> getFields() {
		return fields;
	}

	public void setFields(List<String> fields) {
		this.fields = fields;
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

	public Map<String, Object> getUpdates() {
		return updates;
	}

	public void setUpdates(Map<String, Object> updates) {
		this.updates = updates;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public Map<String, Object> getOrders() {
		return orders;
	}

	public void setOrders(Map<String, Object> orders) {
		this.orders = orders;
	}
	
	
	
}
