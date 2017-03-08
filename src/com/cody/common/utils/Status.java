/**   
 * @Title: Status.java
 * @Description: 状态枚举类
 * @author wanhuan   
 * @date 2016年12月26日 下午
 * @version V1.0   
 */


package com.cody.common.utils;

public enum Status {
	NORMAL(1, "正常"),
	LOCK(0,"锁定"),
	DELETE(-1,"删除");
	
	private int type;
	private String description;

	public int getType()
	{
		return type;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	private Status(int type, String description)
	{
		this.type = type;
		this.description = description;
	}
	
	public static Status getByType(int type)
	{
		for(Status at:Status.values()){
			if(at.type==type){
				return at;
			}
		}
		throw new IllegalArgumentException("Not supported client type:" + type); 
	}
}
