package com.cody.common.utils;


/**
 * 消息类：用于返回save、update、delete操作
 * @author 周围
 * @deprecated： 提供后台返回前端需要封装的消息类
 */
public class Message {

	public static final String INSERTSUCCEED = "新增成功!";
	
	public static final String SAVESUCCEED = "保存成功!";
	
	public static final String DELSUCCEED = "删除成功！";
	
	public static final String DELDEFEATED = "删除失败";
	
	public static final String SAVEDEFEATED = "保存失败";
	
	
	
	public static final String DECLARESUCCEED = "申报发起成功!";
	
	public static final String DECLAREDEFEATED = "申报失败";
	
	public static final String AUDITSUCCEED = "审批成功!";
	
	public static final String AUDITDEFEATED = "审批失败";
	
	
	/** 是否成功 */
	private boolean success;
	
	/** 需要返回的消息 */
	private String message;
	
	/** 需要返回的Id*/
	private String returnId;
	
	private String name;
	
	private String path;

	public Message() {}
	
	
	/**
	 * 生成返回前端的消息对象
	 * @param isSuccess 是否成功
	 * @param message	消息内容
	 * @param returnId	需要返回的ID，配合新增使用
	 */
	public Message(boolean success, String message, String returnId) {
		this.success = success;
		this.message = message;
		this.returnId = returnId;
	}
	
	public Message(boolean success, String message, String returnId,
			String name, String path) {
		this.success = success;
		this.message = message;
		this.returnId = returnId;
		this.name = name;
		this.path = path;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getReturnId() {
		return returnId;
	}

	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	
	
	
	
	
}
