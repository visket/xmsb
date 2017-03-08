package com.cody.vo.project;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;


/**
 * 项目审核关联查询 VO
 * @author user
 *
 */
public class ProjectAuditVO {

	/** 项目ID */
	private String projectId;

	/** 项目编号 */
	@Comparator(column="p.applycode",comparater=Compare.LIKE)
	private String projectNumber;

	/** 项目名称 */
	@Comparator(column="p.applyname",comparater=Compare.LIKE)
	private String projectName;
	
	/** 项目类型*/
	private String projectTypeId; 
	
	/** 项目状态 */
	private String projectStatusId;
	
	/** 项目最近关联审批操作 */
	private String projectLastLogId;

	/** 项目状态名称 */
	private String projectStatusName;
	
	/** 项目申报时间 */
	@JSONField(format = "yyyy-MM-dd")
	private Date projectDeclareTime;

	/** 项目回复状态 */
	private String projectReviewStatus;
	
	private String projectReviewStatusName;

	/** 项目回复内容 */
	private String projectReviewContent;

	/** 项目回复内容 */
	private String projectReviewScore;

	/** 流程ID */
	private String processId;

	/** 流程编号 */
	private String processNumber;

	/** 流程名称 */
	private String processName;

	/** 节点ID */
	private String stepId;

	/** 节点编号 */
	private Integer stepNumber;

	/** 上级节点编号 */
	private Integer stepParentNumber;

	/** 节点名称 */
	private String stepName;

	/** 日志ID */
	private String logId;
	
	/** 日志步骤编号 */
	private Integer logStepNumber;
	
	/** 日志操作编号 */
	private Integer logNumber;

	/** 创建时间 */
	private String logCreatetime;

	/** 最后修改时间 */
	@JSONField(format = "yyyy-MM-dd")
	private Date logLastupdatetime;

	/** 操作开始时间 */
	private String logStarttime;

	/** 操作结束时间 */
	@JSONField(format = "yyyy-MM-dd")
	private Date logEndtime;
	
	/** 操作日志状态 */
	@Comparator(column="log.status_id",comparater=Compare.EQ)
	private String logStatusId;
	
	/** 操作日志状态 */
	private String logStatusName;
	
	/** 日志回复状态 */
	private String logReviewstatus;
	
	/** 日志回复ID */
	private String logReviewstatusId;

	/** 日志回复评价 */
	private String logReviewcore;

	/** 日志回复内容 */
	private String logReviewcontent;

	/** 区域ID */
	private String areaId;

	/** 区域名称 */
	private String areaName;

	/** 审批人ID */
	private Long approverId;

	/** 审批人名称 */
	private String approverName;

	/** 申报人ID */
	private Long declarerId;

	/** 申报人名称 */
	private String declarerName;

	/** 实际审批人ID */
	private Long actualApproverId;

	/** 实际审批人名称 */
	private String actualApproverName;

	/** 审批单位ID */
	private String approveUnitId;

	/** 审批单位名称 */
	private String approveUnitName;
	
	/** 实际审批单位ID */
	private String actualApproveUnitId;

	/** 实际审批单位名称 */
	private String actualApproveUnitName;
	
	/** 审批单位级别 */
	private String approveUnitGradeId;
	
	/** 审批单位级别 */
	private String approveUnitGradeName;

	/** 申报单位ID */
	@Comparator(column="p.unit_id",comparater=Compare.EQ)
	private String declareUnitId;

	/** 申报单位名称 */
	private String declareUnitName;
	
	//私有过滤字段
	
	@Comparator(column="p.applytime",comparater=Compare.EGT,splitTime="00:00:00")
	private String projectDeclareTimeBegin;
	
	@Comparator(column="p.applytime",comparater=Compare.ELT,splitTime="23:59:59")
	private String projectDeclareTimeEnd;
	
	@Comparator(column="log.logEndtime",comparater=Compare.EGT,splitTime="00:00:00")
	private String logEndtimeBegin;
	
	@Comparator(column="log.logEndtime",comparater=Compare.ELT,splitTime="23:59:59")
	private String logEndtimeEnd;
	
	//项目分类
	@Comparator(column="p.classify_id")
	private String projectClassifyId;
	private String projectClassifyName;
	
	@Comparator(column="p.projectcontacts", comparater=Compare.LIKE)
	private String projectContacts;
	
	@Comparator(column="p.phone", comparater=Compare.LIKE)
	private String projectPhone;
	
	
	//重要部分，应用于插入自定义下级节点权限
	private String stepUnitId;
	private Long stepUserId;
	private Long stepRoleId;
	
	private String stepBackStatusId; 
	
	
	public ProjectAuditVO() {
		// TODO Auto-generated constructor stub
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectNumber() {
		return projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectReviewStatus() {
		return projectReviewStatus;
	}

	public void setProjectReviewStatus(String projectReviewStatus) {
		this.projectReviewStatus = projectReviewStatus;
	}

	public String getProjectReviewContent() {
		return projectReviewContent;
	}

	public void setProjectReviewContent(String projectReviewContent) {
		this.projectReviewContent = projectReviewContent;
	}

	public String getProjectReviewScore() {
		return projectReviewScore;
	}

	public void setProjectReviewScore(String projectReviewScore) {
		this.projectReviewScore = projectReviewScore;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessNumber() {
		return processNumber;
	}

	public void setProcessNumber(String processNumber) {
		this.processNumber = processNumber;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}



	public String getStepName() {
		return stepName;
	}

	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getLogCreatetime() {
		return logCreatetime;
	}

	public void setLogCreatetime(String logCreatetime) {
		this.logCreatetime = logCreatetime;
	}

	public Date getLogLastupdatetime() {
		return logLastupdatetime;
	}

	public void setLogLastupdatetime(Date logLastupdatetime) {
		this.logLastupdatetime = logLastupdatetime;
	}

	public String getLogStarttime() {
		return logStarttime;
	}

	public void setLogStarttime(String logStarttime) {
		this.logStarttime = logStarttime;
	}

	public Date getLogEndtime() {
		return logEndtime;
	}

	public void setLogEndtime(Date logEndtime) {
		this.logEndtime = logEndtime;
	}

	public String getLogReviewstatus() {
		return logReviewstatus;
	}

	public void setLogReviewstatus(String logReviewstatus) {
		this.logReviewstatus = logReviewstatus;
	}

	public String getLogReviewcore() {
		return logReviewcore;
	}

	public void setLogReviewcore(String logReviewcore) {
		this.logReviewcore = logReviewcore;
	}

	public String getLogReviewcontent() {
		return logReviewcontent;
	}

	public void setLogReviewcontent(String logReviewcontent) {
		this.logReviewcontent = logReviewcontent;
	}


	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}


	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
	}


	public String getDeclarerName() {
		return declarerName;
	}

	public void setDeclarerName(String declarerName) {
		this.declarerName = declarerName;
	}

	public String getActualApproverName() {
		return actualApproverName;
	}

	public void setActualApproverName(String actualApproverName) {
		this.actualApproverName = actualApproverName;
	}

	public String getApproveUnitId() {
		return approveUnitId;
	}

	public void setApproveUnitId(String approveUnitId) {
		this.approveUnitId = approveUnitId;
	}

	public String getApproveUnitName() {
		return approveUnitName;
	}

	public void setApproveUnitName(String approveUnitName) {
		this.approveUnitName = approveUnitName;
	}

	public String getDeclareUnitId() {
		return declareUnitId;
	}

	public void setDeclareUnitId(String declareUnitId) {
		this.declareUnitId = declareUnitId;
	}

	public String getDeclareUnitName() {
		return declareUnitName;
	}

	public void setDeclareUnitName(String declareUnitName) {
		this.declareUnitName = declareUnitName;
	}

	public String getProjectStatusId() {
		return projectStatusId;
	}

	public void setProjectStatusId(String projectStatusId) {
		this.projectStatusId = projectStatusId;
	}

	public String getProjectStatusName() {
		return projectStatusName;
	}

	public void setProjectStatusName(String projectStatusName) {
		this.projectStatusName = projectStatusName;
	}

	public String getLogStatusId() {
		return logStatusId;
	}

	public void setLogStatusId(String logStatusId) {
		this.logStatusId = logStatusId;
	}

	public String getLogStatusName() {
		return logStatusName;
	}

	public void setLogStatusName(String logStatusName) {
		this.logStatusName = logStatusName;
	}

	public Integer getStepNumber() {
		return stepNumber;
	}

	public void setStepNumber(Integer stepNumber) {
		this.stepNumber = stepNumber;
	}

	public Integer getStepParentNumber() {
		return stepParentNumber;
	}

	public void setStepParentNumber(Integer stepParentNumber) {
		this.stepParentNumber = stepParentNumber;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public Long getApproverId() {
		return approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public Long getDeclarerId() {
		return declarerId;
	}

	public void setDeclarerId(Long declarerId) {
		this.declarerId = declarerId;
	}

	public Long getActualApproverId() {
		return actualApproverId;
	}

	public void setActualApproverId(Long actualApproverId) {
		this.actualApproverId = actualApproverId;
	}

	public Integer getLogStepNumber() {
		return logStepNumber;
	}

	public void setLogStepNumber(Integer logStepNumber) {
		this.logStepNumber = logStepNumber;
	}

	public Integer getLogNumber() {
		return logNumber;
	}

	public void setLogNumber(Integer logNumber) {
		this.logNumber = logNumber;
	}

	public Date getProjectDeclareTime() {
		return projectDeclareTime;
	}

	public void setProjectDeclareTime(Date projectDeclareTime) {
		this.projectDeclareTime = projectDeclareTime;
	}

	public String getApproveUnitGradeId() {
		return approveUnitGradeId;
	}

	public void setApproveUnitGradeId(String approveUnitGradeId) {
		this.approveUnitGradeId = approveUnitGradeId;
	}

	public String getApproveUnitGradeName() {
		return approveUnitGradeName;
	}

	public void setApproveUnitGradeName(String approveUnitGradeName) {
		this.approveUnitGradeName = approveUnitGradeName;
	}

	public String getLogReviewstatusId() {
		return logReviewstatusId;
	}

	public void setLogReviewstatusId(String logReviewstatusId) {
		this.logReviewstatusId = logReviewstatusId;
	}

	public String getProjectLastLogId() {
		return projectLastLogId;
	}

	public void setProjectLastLogId(String projectLastLogId) {
		this.projectLastLogId = projectLastLogId;
	}

	public String getProjectDeclareTimeBegin() {
		return projectDeclareTimeBegin;
	}

	public void setProjectDeclareTimeBegin(String projectDeclareTimeBegin) {
		this.projectDeclareTimeBegin = projectDeclareTimeBegin;
	}

	public String getProjectDeclareTimeEnd() {
		return projectDeclareTimeEnd;
	}

	public void setProjectDeclareTimeEnd(String projectDeclareTimeEnd) {
		this.projectDeclareTimeEnd = projectDeclareTimeEnd;
	}

	public String getLogEndtimeBegin() {
		return logEndtimeBegin;
	}

	public void setLogEndtimeBegin(String logEndtimeBegin) {
		this.logEndtimeBegin = logEndtimeBegin;
	}

	public String getLogEndtimeEnd() {
		return logEndtimeEnd;
	}

	public void setLogEndtimeEnd(String logEndtimeEnd) {
		this.logEndtimeEnd = logEndtimeEnd;
	}

	public String getProjectReviewStatusName() {
		return projectReviewStatusName;
	}

	public void setProjectReviewStatusName(String projectReviewStatusName) {
		this.projectReviewStatusName = projectReviewStatusName;
	}

	public String getProjectTypeId() {
		return projectTypeId;
	}

	public void setProjectTypeId(String projectTypeId) {
		this.projectTypeId = projectTypeId;
	}

	public String getStepUnitId() {
		return stepUnitId;
	}

	public void setStepUnitId(String stepUnitId) {
		this.stepUnitId = stepUnitId;
	}

	public Long getStepUserId() {
		return stepUserId;
	}

	public void setStepUserId(Long stepUserId) {
		this.stepUserId = stepUserId;
	}

	public Long getStepRoleId() {
		return stepRoleId;
	}

	public void setStepRoleId(Long stepRoleId) {
		this.stepRoleId = stepRoleId;
	}

	public String getProjectClassifyId() {
		return projectClassifyId;
	}

	public void setProjectClassifyId(String projectClassifyId) {
		this.projectClassifyId = projectClassifyId;
	}

	public String getProjectPhone() {
		return projectPhone;
	}

	public void setProjectPhone(String projectPhone) {
		this.projectPhone = projectPhone;
	}

	public String getProjectContacts() {
		return projectContacts;
	}

	public void setProjectContacts(String projectContacts) {
		this.projectContacts = projectContacts;
	}

	public String getProjectClassifyName() {
		return projectClassifyName;
	}

	public void setProjectClassifyName(String projectClassifyName) {
		this.projectClassifyName = projectClassifyName;
	}

	public String getStepBackStatusId() {
		return stepBackStatusId;
	}

	public void setStepBackStatusId(String stepBackStatusId) {
		this.stepBackStatusId = stepBackStatusId;
	}

	public String getActualApproveUnitId() {
		return actualApproveUnitId;
	}

	public void setActualApproveUnitId(String actualApproveUnitId) {
		this.actualApproveUnitId = actualApproveUnitId;
	}

	public String getActualApproveUnitName() {
		return actualApproveUnitName;
	}

	public void setActualApproveUnitName(String actualApproveUnitName) {
		this.actualApproveUnitName = actualApproveUnitName;
	}

	
	
}
