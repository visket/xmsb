package com.cody.common.shiro;

import java.io.Serializable;

/**
 * @ClassName: DataScope
 * @Description: 数据权限对象
 * @author：wanhuan
 * @date：2016/11/18
 * 
 */

public class DataScope implements Serializable{
	
	private static final long serialVersionUID = -883613269965908392L;
	/**
	 * 当前用户
	 */
	private Object user;
	/**
	 * 数据过滤sql
	 */
	private String dataFilterSql;

	public Object getUser() {
		return user;
	}

	public void setUser(Object user) {
		this.user = user;
	}

	public String getDataFilterSql() {
		return dataFilterSql;
	}

	public void setDataFilterSql(String dataFilterSql) {
		this.dataFilterSql = dataFilterSql;
	}

}
