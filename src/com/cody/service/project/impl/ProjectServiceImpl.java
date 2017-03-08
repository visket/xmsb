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
import com.cody.service.project.IProjectService;




/**
 * 项目基础信息实现
 * @author around
 *
 */
@Service
public class ProjectServiceImpl implements IProjectService {
	
	@Resource
	private ProjectMapper projectMapper;
	
	@Resource
	private ApproveStepMapper project_stepMapper;
	
	@Resource
	private ApproveProcessMapper project_processMapper;
	
	@Resource
	private ApproveLogMapper project_logMapper;
	
	@Resource
	private UnitMapper project_unitMapper;

	@Override
	@Transactional
	public int deleteByPrimaryKey(String id) {
		return projectMapper.deleteByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int insert(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.insert(project);
	}

	@Override
	@Transactional
	public int insertSelective(Project project) {
		// TODO Auto-generated method stub
		project.setId(IDUtil.UUID());
		return projectMapper.insertSelective(project);
	}

	@Override
	public Project selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return projectMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.updateByPrimaryKeySelective(project);
	}

	@Override
	@Transactional
	public int updateByPrimaryKey(Project project) {
		// TODO Auto-generated method stub
		return projectMapper.updateByPrimaryKey(project);
	}

	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = projectMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(projectMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Project>());
		}
		
	}

	@Override
	@Transactional
	public int deleteByPrimaryKeys(String[] ids) {
		return projectMapper.deleteByPrimaryKeys(ids);
	}

	@Override
	@Transactional
	public int updateByCustoms(CustomDatabaseHandle customHandle) {
		return projectMapper.updateByCustoms(customHandle);
	}

	@Override
	@Transactional
	public int processDeclare(Project project) {
		// TODO Auto-generated method stub
		int type = 0;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		Map<String, Object> orders = customDatabase.getOrders();
		
		//查询当月审批流程条数
		customDatabase.setTablename("t_approve_process");
		int thedayCount = project_processMapper.findToMonthDataCount(customDatabase);
		
		//查询当前单位,用于提交上级单位
		Unit unit = project_unitMapper.selectByPrimaryKey(project.getUnitId());
		
		//获取指定审批流程及节点
		ApproveProcess process = new ApproveProcess();
		process.setId("07011eaa-3daf-4f6b-9367-fdb641c83a29");
		
		customDatabase.setTablename("t_approve_step");
		condition.clear();
		condition.put("process_id = ", process.getId());
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = project_stepMapper.findByCondition(customDatabase);
		
		/*customDatabase.setTablename("t_approve_log");
		condition.put("business_id = ", project.getId());
		customDatabase.setCondition(condition);
		List<ApproveLog> logList = project_logMapper.findByCondition(customDatabase);*/
		
		
		//单位固定审批流程申报第一步 
		ApproveStep firstStep = stepList.get(0);
		
		
		//确定流程问题，这里需要获取到最后一次审批日志操作，然后找出对应的审批步骤，
		ApproveLog currentProjectApproveLog = null;
		ApproveStep currentProjectApproveStep = null;

		int logNumber = 1;
		
		//当为新申报的时候
		if(StringUtils.isEmpty(project.getLogId())) {
			logNumber = 1;
		} else {
			//当存在旧审批操作时，不考虑其内容，只取当前操作编号，自增，并绑定新的下级审批节点，从头发起
			currentProjectApproveLog = project_logMapper.selectByPrimaryKey(project.getLogId());
			logNumber = currentProjectApproveLog.getLognumber() + 1;
			
			//查出下级步骤的权限等内容，生成新的
			//currentProjectApproveStep = project_stepMapper.selectByPrimaryKey(currentProjectApproveLog.getStepId());
		}
		
		ApproveLog log = LogUtils.getNewApproveLog(project, firstStep, logNumber);
		
		if(DataUtil.isNotNull(firstStep.getStepuserId())) {//是否指定审核人
			//log.se(firstStep.getStepuserId());
		} else if(DataUtil.isNotNull(firstStep.getStepunitId())) {//是否指定审核部门
			log.setStepunitId(firstStep.getStepunitId());
		} else if(DataUtil.isNotNull(firstStep.getSteproleId())) {//是否指定角色用户
			log.setStepunitId(firstStep.getStepunitId());
		} else if(firstStep.getIsparentunit() == 1) {//是否上级单位审核
			//查询当前单位,用于提交上级单位
			log.setStepunitId(unit.getHigherLevelIdentifier());
		}
		
		
		type = project_logMapper.insertSelective(log);
		
		
		//修改项目状态
		customDatabase.setTablename("t_project_base");
		
		condition = new HashMap<String, Object>();
		condition.put("id =", project.getId());
		customDatabase.setCondition(condition);
		
		Map<String, Object> updates = customDatabase.getUpdates();
		updates.put("status_id =", "XMZT_SBZ");
		updates.put("process_id = ", process.getId());
		updates.put("log_id = ", log.getId());
		customDatabase.setUpdates(updates);
		
		type = projectMapper.updateByCustoms(customDatabase);

		return type;
	}

	@Override
	public int findToMonthCount() {
		int type = 0;
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_project_base");
		return projectMapper.findToMonthDataCount(customHandle);
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
