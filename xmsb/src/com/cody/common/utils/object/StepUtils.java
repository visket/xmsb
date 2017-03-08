package com.cody.common.utils.object;

import java.util.Date;

import com.cody.entity.approve.ApproveProcess;
import com.cody.entity.approve.ApproveStep;



public class StepUtils {

	
	public static ApproveStep getNewApproveStep(ApproveProcess process, ApproveStep step, int index) {
		if(step == null) step = new ApproveStep();
		Date createtime = process.getCreatetime();
		
		step.setProcessId(process.getId());
		step.setProcessnumber(process.getNumber());
		step.setStepnumber(1);
		step.setParentstepnumber(0);
		
		step.setBussinessId(process.getProjectId());
		step.setCreateorId(process.getCreatorId());
		step.setCreatetime(createtime);
		step.setUpdatorId(process.getCreatorId());
		step.setLastupdatetime(createtime);
		
		step.setStarttime(createtime);
		step.setEndtime(null);
		
		step.setDatasign("1");
		step.setStatusId("2187eeed-67c8-4cfe-8343-8db5e161f471");//审批中
		
		step.setStepname(process.getName() + "-" + step.getStepnumber());
		
		//step.setSteporganizeId(Long.valueOf(1));//组织权限
		//step.setSteproleId(Long.valueOf(1));//角色权限
		//step.setStepuserId(Long.valueOf(1));//用户权限
		//step.setStepjobId(null);//部门权限
		
		
		return step;
	}
	
	
	
}
