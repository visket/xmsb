package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.DataUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.finals.ProcessType;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.project.Project;
import com.cody.vo.project.ProjectAuditVO;

public class LogUtils {

	
	
	
	
	
	
	public static ApproveLog getNewApproveLog(Project project, ApproveStep step, int logIndex) {
		ApproveLog log = new ApproveLog();
		Date createtime = new Date(); 
		
		log.setId(IDUtil.UUID());
		
		log.setProcessId(step.getProcessId());
		log.setProcessnumber(step.getProcessnumber());
		
		log.setStepId(step.getId());
		log.setStepnumber(step.getStepnumber());
		log.setLognumber(logIndex);
		
		log.setBusinessId(project.getId());
		
		log.setApprovetorId(project.getCreatorId());
		log.setCreatetime(createtime);
		log.setCreatorId(project.getCreatorId());
		log.setUpdatorId(project.getCreatorId());
		log.setLastupdatetime(createtime);
		
		log.setStarttime(createtime);
		log.setEndtime(null);
		
		log.setDatasign("1");
		log.setStatusId(step.getStartstatusId()); //待受理
		
		log.setOpinion("");
		
		return log;
	}
	


	
	public static ApproveLog getDeclareLog(ProjectAuditVO project, ApproveStep step, int logIndex) {
		ApproveLog log = new ApproveLog();
		Date createtime = new Date(); 
		
		log.setId(IDUtil.UUID());
		
		log.setApprovetorId(step.getStepuserId()); //指定用户审核
		log.setStepuserId(step.getStepuserId());//指定用户
		log.setSteproleId(step.getSteproleId());//指定角色
		log.setStepunitId(step.getStepunitId());//指定部门
		
		log.setProcessId(step.getProcessId());
		log.setProcessnumber(step.getProcessnumber());
		log.setStepId(step.getId());
		log.setStepnumber(step.getStepnumber());
		log.setLognumber(logIndex);
		//log.setParentId(project.getLogId());
		
		log.setBusinessId(project.getProjectId());
		
		log.setCreatetime(createtime);
		log.setCreatorId(project.getDeclarerId());
		log.setLastupdatetime(createtime);
		log.setUpdatorId(project.getDeclarerId());
		
		log.setApprovetorId(project.getDeclarerId());
		log.setStepunitId(project.getDeclareUnitId());
		
		log.setStarttime(createtime);
		log.setEndtime(null);
		
		log.setDatasign("1");
		log.setStatusId(step.getStartstatusId()); 
		
		return log;
	}
	
	
	public static ApproveLog getNewApproveLog(ProjectAuditVO project,
			ApproveStep step, int logNumber) {
		ApproveLog log = new ApproveLog();
		Date createtime = new Date(); 
		
		log.setId(IDUtil.UUID());
		
		
		//指定部门
		if(DataUtil.isNotNull(project.getStepUnitId())) log.setStepunitId(project.getStepUnitId());
		else log.setStepunitId(step.getStepunitId());
		
		//指定用户
		if(DataUtil.isNotNull(project.getStepUserId())) {
			log.setStepuserId(project.getStepUserId());
			log.setApprovetorId(project.getStepUserId()); 
		} else {
			log.setStepuserId(step.getStepuserId());//指定用户
			log.setApprovetorId(step.getStepuserId()); //指定用户审核
		}
		
		//指定角色
		if(DataUtil.isNotNull(project.getStepRoleId())) log.setSteproleId(project.getStepRoleId());
		else log.setSteproleId(step.getSteproleId());

		
		//log.setStepunitId(step.getStepunitId());//指定部门
		//log.setStepuserId(step.getStepuserId());//指定用户
		//log.setApprovetorId(step.getStepuserId()); //指定用户审核
		//log.setSteproleId(step.getSteproleId());//指定角色
		
		
		log.setProcessId(step.getProcessId());
		log.setProcessnumber(step.getProcessnumber());
		
		log.setParentId(project.getLogId());
		
		log.setStepId(step.getId());
		log.setStepnumber(step.getStepnumber());
		log.setLognumber(logNumber);
		log.setBusinessId(project.getProjectId());
		
		log.setCreatetime(createtime);
		log.setCreatorId(project.getDeclarerId());
		log.setLastupdatetime(createtime);
		log.setUpdatorId(project.getDeclarerId());
		
		if(DataUtil.isNotNull(project.getActualApproverId())) {
			log.setCreatorId(project.getActualApproverId());
			log.setUpdatorId(project.getActualApproverId());
		}
		
		log.setStarttime(createtime);
		log.setEndtime(null);
		
		log.setDatasign("1");
		log.setStatusId(step.getStartstatusId()); //待受理
		
		//log.setOpinion("");
		
		return log;
	}
	
	
	public static ApproveLog getUpdateLogData(ProjectAuditVO project, ApproveLog log, String auditType) {
		if(log == null) log = new ApproveLog();
		Date time = new Date();
		
		log.setId(project.getLogId());
		
		log.setApprovetorId(project.getActualApproverId());

		log.setStatusId(auditType);
		log.setReviewcontent(project.getLogReviewcontent());
		
		log.setEndtime(time);
		log.setLastupdatetime(time);
		
		return log;
	}
	
	
	
}
