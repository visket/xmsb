package com.cody.common.core;

import java.io.Serializable;

import com.cody.common.utils.Message;
import com.cody.common.utils.finals.OperateType;

/**
 * @description：操作结果集
 * @author：wanhuan
 * @date：2016/11/21
 */
public class Result implements Serializable {

	private static final long serialVersionUID = 5576237395711742681L;

	private boolean success = false;

	private String msg = "";

	private Object obj = null;

	private Integer code = null;
	
	private String pagetype;

	private String id = null;

	public static Result newInstance() {
		return new Result();
	}

	public Result() {
		super();
	}

	public Result(boolean success, String msg, Object obj, Integer code) {
		super();
		this.success = success;
		this.msg = msg;
		this.obj = obj;
		this.code = code;
	}

	/**
	 * 
	 * @param type
	 *            成功失败返回值 成功1 失败0
	 * @param id
	 *            主键
	 * @param errorMsg
	 *            捕捉的错误信息
	 * @param functionType
	 *            新增或修改或删除判定
	 */
	public Result(int type, String id, String errorMsg, String functionType) {
		this.id = id;// ID
		this.success = type == 0 ? false : true;// 添加或修改返回值 0表示失败，其余表示成功
		this.pagetype = functionType;

		if (OperateType.ADD.equals(functionType)) {
			this.msg = success ? Message.INSERTSUCCEED : Message.SAVEDEFEATED + errorMsg;// errorMsg 捕获的异常的的错误返回去前台
		} else if (OperateType.EDIT.equals(functionType)) {
			this.msg = success ? Message.SAVESUCCEED : Message.SAVEDEFEATED + errorMsg;// errorMsg 捕获的异常的的错误返回去前台
		} else if (OperateType.DELETE.equals(functionType)) {
			this.msg = success ? Message.DELSUCCEED : Message.DELDEFEATED + errorMsg;// errorMsg 捕获的异常的的错误返回去前台
		} else if (OperateType.DECLARE.equals(functionType)) {
			this.msg = success ? Message.DECLARESUCCEED : Message.DECLAREDEFEATED + errorMsg;// errorMsg 捕获的异常的的错误返回去前台
		} else if (OperateType.AUDIT.equals(functionType)) {
			this.msg = success ? Message.AUDITSUCCEED : Message.AUDITDEFEATED + errorMsg;// errorMsg 捕获的异常的的错误返回去前台
		}
	}
	
	
	
	public Result(int type, String id, String errorMsg, String functionType, Object obj) {
		this(type, id, errorMsg, functionType);
		this.obj = obj;
	}
	
	
	/**
	 * 验证消息返回框
	 * @param success
	 * @param msg
	 * @param obj
	 */
	public Result(boolean success, String msg, Object obj) {
		this.success = success;
		this.msg = msg;
		this.obj = obj;
	}
	
	
	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		if (code == ResultCode.SUCCESS) {
			setSuccess(Boolean.TRUE);
		}
		this.code = code;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPagetype() {
		return pagetype;
	}

	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}

	
	
}
