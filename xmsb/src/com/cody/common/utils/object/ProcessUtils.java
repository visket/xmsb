package com.cody.common.utils.object;

import java.util.Date;

import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.NumberUtil;
import com.cody.entity.approve.ApproveProcess;
import com.cody.entity.project.Project;

public class ProcessUtils {

	
	/**
	 * 项目申报发起 -- 自动封装初始化  审批流程ApproveProcess数据
	 * @param project	封装的project对象
	 * @param process	没有默认为null
	 * @param thedayCount	当天新建的总流程数
	 * @return
	 */
	public static ApproveProcess getNewApproveProcess(Project project, ApproveProcess process, int thedayCount) {
		if(process == null) process = new ApproveProcess();
		Date date = new Date();
		
		process.setId(IDUtil.UUID());
		process.setName("ApproveProcess");
		process.setNumber(DateFormatUtil.dateToString(new Date(), "yyyy-MM") + "-" + NumberUtil.formatToString("0000", thedayCount));
		process.setProjectId(project.getId());
		
		process.setCreatorId(project.getCreatorId());
		process.setCreatetime(date);
		process.setUpdatorId(project.getCreatorId());
		process.setLastupdatetime(date);
		
		process.setStarttime(date);
		process.setEndtime(null);
		
		process.setDatasignId("1");
		process.setStatusId("2187eeed-67c8-4cfe-8343-8db5e161f471"); //审批中
		process.setDescription("base");
		
		return process;
	}
	
}
