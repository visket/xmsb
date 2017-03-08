package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.equip.EquipBase;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 装备项目实体封装
 * 
 * @author around
 * @date 2017-1-13
 *
 */
public class EquipUtils {

	
	/**
	 * 封装审核完成后返回给装备项目的意见信息
	 * @param equip EquipBase
	 * @param step ApproveStep
	 * @param log ApproveLog
	 * @param auditType 
	 * @return EquipBase
	 */
	public static EquipBase getAuditReturnData(EquipBase equip, ApproveStep step, ApproveLog log, String auditType) {
		if(equip == null) equip = new EquipBase();
		Date date = new Date();
		equip.setId(log.getBusinessId());
		
		if(step.getIslaststep() == 1) {
			if(auditType.equals(ProcessType.LCZT_BH)) equip.setStatusId(ProjectType.XMZT_TH);
			else if(auditType.equals(ProcessType.LCZT_BTG)) equip.setStatusId(ProjectType.XMZT_BTG);
			else {
				equip.setStatusId(ProjectType.XMZT_YSP);
			}
			equip.setEndtime(date);
		}
		
		if(auditType.equals(ProcessType.LCZT_BH)) equip.setStatusId(ProjectType.XMZT_TH);
		else if(auditType.equals(ProcessType.LCZT_BTG)) equip.setStatusId(ProjectType.XMZT_BTG);
		
		equip.setLastupdatetime(date);
		equip.setReviewstatusId(log.getStatusId());
		equip.setReviewcotent(log.getReviewcontent());
		
		return equip;
	}
	
	
	public static EquipBase getDeclareData(ProjectAuditVO project, ApproveLog log, String processId) {
		EquipBase equip = new EquipBase();
		Date date = new Date();
		
		equip.setId(project.getProjectId());
		equip.setStatusId(ProjectType.XMZT_SBZ);
		equip.setReviewstatusId(ProcessType.LCZT_DSL);
		equip.setProcessId(processId);
		equip.setLogId(log.getId());
		equip.setStarttime(date);
		equip.setEndtime(date);
		
		return equip;
	}
	
}
