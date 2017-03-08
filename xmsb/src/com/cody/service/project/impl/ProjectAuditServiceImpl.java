package com.cody.service.project.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.enums.CompareEnum;
import com.cody.common.utils.object.LogUtils;
import com.cody.common.utils.object.ProcessUtils;
import com.cody.common.utils.object.StepUtils;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveProcess;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.project.Project;
import com.cody.entity.sys.Unit;
import com.cody.mapper.approve.ApproveLogMapper;
import com.cody.mapper.approve.ApproveProcessMapper;
import com.cody.mapper.approve.ApproveStepMapper;
import com.cody.mapper.project.ProjectMapper;
import com.cody.mapper.sys.UnitMapper;
import com.cody.service.project.IProjectAuditService;
import com.cody.service.project.IProjectService;
import com.cody.vo.project.ProjectAuditVO;




/**
 * 项目基础信息实现
 * @author around
 *
 */
@Service
public class ProjectAuditServiceImpl implements IProjectAuditService {
	

	@Resource
	private ProjectMapper projectAuditMapper;
	
	@Resource
	private ApproveStepMapper projectAudit_stepMapper;
	
	@Resource
	private ApproveProcessMapper projectAudit_processMapper;
	
	@Resource
	private ApproveLogMapper projectAudit_logMapper;
	
	@Resource
	private UnitMapper projectAudit_unitMapper;

	


	@Override
	public int declare(ProjectAuditVO project) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int beforeAudit(ProjectAuditVO project) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int audit(ProjectAuditVO project, String auditType) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void findAuditProcessLogData(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int updateByCustoms(CustomDatabaseHandle customHandle) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void findAuditData(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	/*public int processDeclare(Project project) {
		// TODO Auto-generated method stub
		int type = 0;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		
		//查询当月审批流程条数
		customDatabase.setTablename("t_approve_process");
		int thedayCount = project_processMapper.findToMonthDataCount(customDatabase);
		
		//查询当前单位,用于提交上级单位
		Unit unit = project_unitMapper.selectByPrimaryKey(project.getUnitId()); 
		
		//封装审批流程
		ApproveProcess process = ProcessUtils.getNewApproveProcess(project, null, thedayCount+1);
		
		//封装审批步骤
		ApproveStep step = StepUtils.getNewApproveStep(process, null, 1);
		
		//这里控制审批的方式，本流程测试默认为上级单位角色审核，若改动可以定义流程审批模式
		//1、插入提交审批的权限单位
		step.setStepunitId(unit.getHigherLevelIdentifier());
		
		//创建审批流程
		type = project_processMapper.insertSelective(process);
		
		//创建审批流程节点
		type = project_stepMapper.insertSelective(step);
		
		//修改项目状态
		customDatabase.setTablename("t_project_base");
		
		condition = new HashMap<String, Object>();
		condition.put("id =", project.getId());
		customDatabase.setCondition(condition);
		
		Map<String, Object> updates = customDatabase.getUpdates();
		updates.put("status_id =", "XMZT_SBZ");
		updates.put("process_id = ", process.getId());
		customDatabase.setUpdates(updates);
		
		List<String> ids = customDatabase.getIds();
		ids.add(project.getId());
		customDatabase.setIds(ids);
		
		type = projectMapper.updateByCustoms(customDatabase);

		return type;
	}*/
	

}
