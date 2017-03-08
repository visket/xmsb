package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.science.ScienceBase;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 装备项目实体封装
 * 
 * @author around
 * @date 2017-1-13
 *
 */
public class ScienceUtils {

	/**
	 * 封装申报状态信息
	 * @param science
	 * @param project
	 * @param firstStep 
	 * @param log
	 * @param processId
	 * @return ScienceBase
	 */
	public static ScienceBase getDeclareData(ScienceBase science, ProjectAuditVO project, ApproveStep firstStep, ApproveLog log, String processId) {
		if(science == null) science = new ScienceBase();
		Date date = new Date();
		science.setId(project.getProjectId());
		science.setStatusId(ProjectType.XMZT_SBZ);
		science.setReviewstatusId(firstStep.getEndstatusId());
		science.setProcessId(processId);
		science.setLogId(log.getId());
		science.setLastupdatetime(date);
		science.setStarttime(date);
		
		return science;
	}
	
	
	/**
	 * 封装申报状态信息
	 * @param science
	 * @param project
	 * @return ScienceBase
	 */
	public static ScienceBase getBeforeAuditData(ScienceBase science, ProjectAuditVO project) {
		if(science == null) science = new ScienceBase();
		Date date = new Date();
		science.setId(project.getProjectId());
		science.setStatusId(ProjectType.XMZT_SHZ);
		science.setReviewstatusId(ProcessType.LCZT_YSL);
		science.setLogId(project.getLogId());
		science.setLastupdatetime(date);
		
		return science;
	}
	
	
	
	/**
	 * 封装审核完成后返回给科技项目的意见信息
	 * @param science ScienceBase
	 * @param step ApproveStep
	 * @param log ApproveLog
	 * @param auditType 
	 * @return ScienceBase
	 */
	public static ScienceBase getAuditData(ScienceBase science, ProjectAuditVO project, 
			ApproveStep step, ApproveLog log, String auditType) {
		if(science == null) science = new ScienceBase();
		Date date = new Date();
		science.setId(project.getProjectId());
		
		if(step.getIslaststep() == 1) {
			
			if(auditType.equals(ProcessType.LCZT_BH)) science.setStatusId(ProjectType.XMZT_TH);
			else if(auditType.equals(ProcessType.LCZT_BTG)) science.setStatusId(ProjectType.XMZT_BTG);
			else {
				science.setStatusId(ProjectType.XMZT_YSP);
			}
			science.setEndtime(date);
		}
		
		
		if(auditType.equals(ProcessType.LCZT_BH)) science.setStatusId(ProjectType.XMZT_TH);
		else if(auditType.equals(ProcessType.LCZT_BTG)) science.setStatusId(ProjectType.XMZT_BTG);
		
		science.setLastupdatetime(date);
		science.setReviewstatusId(log.getStatusId());
		science.setReviewcotent(log.getReviewcontent());
		
		return science;
	}
	
	
	
	
	
}
