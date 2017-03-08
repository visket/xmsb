package com.cody.common.utils.finals;


/**
 * 流程状态类型
 * 用于定义绑定数据库的流程状态ID
 * @author user
 * @date 2017-1-19
 */
public class ProcessType {
	
	/** 发起申报 */
	public static final String LCZT_FQSB = "468496a0-7692-4b3b-838f-612bd884f9d4";

	/** 待受理 */
	public static final String LCZT_DSL = "61a3bdf5-73f9-4285-b8c0-6bca56d0da6d";

	/** 已受理 */
	public static final String LCZT_YSL = "2239265d-bea4-4f81-b0f3-4cb7366e5558";
	
	/** 已审核 */
	public static final String LCZT_YSH = "7d41a53f-ed1a-4bad-8674-32b4fca59cc7";

	/** 已审批 */
	public static final String LCZT_YSP = "16a49a85-64ce-4eaf-8b8b-a615740037e7";
	
	/** 审批中 */
	public static final String LCZT_SPZ = "2187eeed-67c8-4cfe-8343-8db5e161f471";
	
	/** 驳回 */
	public static final String LCZT_BH = "22ef4600-7d7c-4062-b4eb-73ffedb38493";
	
	/** 通过 */
	public static final String LCZT_TG = "0ed99eca-9a87-40d5-aed5-dfaa481263e3";
	
	/** 不通过 */
	public static final String LCZT_BTG = "4d5d8f12-6c6b-49f3-b27e-8d6ba2130cb0";
	
	/** 同意 */
	public static final String LCZT_TY = "32984b8c-aaef-40f6-9ba7-660d7170613e";
	
	/** 审批完成 */
	public static final String LCZT_SPWC = "df84868f-f161-4105-b2a5-372359d11543";
	
	/** 县级已审批 */
	public static final String LCZT_XJSP = "13c74b66-9005-4919-a873-71229da5165c";
	
	/** 市级已审批 */
	public static final String LCZT_SJSP = "0d4b90c6-6c39-4799-a1c4-7a84e4686f40";
	
	/** 省级已审批 */
	public static final String LCZT_SSJSP = "3dbcbb53-60fa-4c4f-87f8-17b1923d3e71";
	
	/** 待分发处室 */
	public static final String LCZT_D_FFCS = "6ac1ea68-e8b2-4497-8930-9da3b744acf6";
	
	/** 已分发处室 */
	public static final String LCZT_YFFCS = "aae0ffb6-8fef-46c6-9bb9-b9bf13f266dc";
	
	/** 处室已审批 */
	public static final String LCZT_CSYSP = "70db0f46-5140-45f2-acf3-17ce759c3639";
	
	/** 已分发专家 */
	public static final String LCZT_YFFZJ = "f40263a3-86dc-4a5e-9b8c-6fbb75bd78d2";
	
	/** 专家已审核 */
	public static final String LCZT_ZJYSP = "f381bea9-71be-4d3f-997d-54529e7b1b4a";
	
	
}
