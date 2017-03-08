package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 装备项目实体封装
 * 
 * @author around
 * @date 2017-1-13
 *
 */
public class HiddangerUtils {

	/**
	 * 封装申报状态信息
	 * @param hiddanger
	 * @param project
	 * @param firstStep 
	 * @param log
	 * @param processId
	 * @return HiddangerDeclare
	 */
	public static HiddangerDeclare getDeclareData(HiddangerDeclare hiddanger, ProjectAuditVO project, 
			ApproveStep firstStep, ApproveLog log, String processId) {
		if(hiddanger == null) hiddanger = new HiddangerDeclare();
		Date date = new Date();
		hiddanger.setId(project.getProjectId());
		hiddanger.setLastupdatetime(date);
		hiddanger.setStatusId(ProjectType.XMZT_SBZ);
		hiddanger.setReviewstatusId(firstStep.getEndstatusId());
		hiddanger.setProcessId(processId);
		hiddanger.setLogId(log.getId());
		hiddanger.setStarttime(date);
		
		return hiddanger;
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
	 * 封装审核完成后返回给项目的意见信息
	 * @param hiddanger ScienceBase
	 * @param step ApproveStep
	 * @param log ApproveLog
	 * @param auditType 
	 * @return HiddangerDeclare
	 */
	public static HiddangerDeclare getAuditData(HiddangerDeclare hiddanger, ProjectAuditVO project, 
			ApproveStep step, ApproveLog log, String auditType) {
		if(hiddanger == null) hiddanger = new HiddangerDeclare();
		Date date = new Date(); 
		hiddanger.setId(project.getProjectId());
		
		if(step.getIslaststep() == 1) {
			
			if(auditType.equals(ProcessType.LCZT_BH)) hiddanger.setStatusId(ProjectType.XMZT_TH);
			else if(auditType.equals(ProcessType.LCZT_BTG)) hiddanger.setStatusId(ProjectType.XMZT_BTG);
			else {
				hiddanger.setStatusId(ProjectType.XMZT_YSP);
			}
			hiddanger.setEndtime(date);
		}
		
		
		if(auditType.equals(ProcessType.LCZT_BH)) hiddanger.setStatusId(ProjectType.XMZT_TH);
		else if(auditType.equals(ProcessType.LCZT_BTG)) hiddanger.setStatusId(ProjectType.XMZT_BTG);
		
		hiddanger.setLastupdatetime(date);
		hiddanger.setReviewstatusId(log.getStatusId());
		hiddanger.setReviewcontent(log.getReviewcontent());
		
		return hiddanger;
	}
	
	
	
	
	
}
