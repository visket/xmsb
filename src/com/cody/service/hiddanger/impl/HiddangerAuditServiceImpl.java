package com.cody.service.hiddanger.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.util.StringUtils;
import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.array.ConnectionUtils;
import com.cody.common.utils.finals.ProcessID;
import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.finals.UnitGradeType;
import com.cody.common.utils.object.HiddangerUtils;
import com.cody.common.utils.object.LogUtils;
import com.cody.common.utils.object.ScienceUtils;
import com.cody.common.utils.object.StorageDataUtils;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.sys.Unit;
import com.cody.mapper.approve.ApproveLogMapper;
import com.cody.mapper.approve.ApproveStepMapper;
import com.cody.mapper.hiddanger.HiddangerAuditMapper;
import com.cody.mapper.hiddanger.HiddangerDeclareMapper;
import com.cody.mapper.sys.UnitMapper;
import com.cody.service.hiddanger.IHiddangerAuditService;
import com.cody.service.project.IProjectAuditService;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 隐患项目service实现
 * @author around
 *
 */
@Service
public class HiddangerAuditServiceImpl implements IHiddangerAuditService {
	
	@Resource
	private HiddangerDeclareMapper hiddangerAudit_declareMapper;
	
	@Resource
	private HiddangerAuditMapper hiddangerAuditMapper;
	
	@Resource
	private UnitMapper hiddangerAudit_unitMapper;
	
	@Resource
	private ApproveStepMapper hiddangerAudit_stepMapper;
	
	@Resource
	private ApproveLogMapper hiddangerAudit_logMapper;

	@Override
	public void findAuditData(PageInfo pageInfo) {
		Integer count = hiddangerAuditMapper.findAuditDataCount(pageInfo);
		
		if(count > 0) {
			List<ProjectAuditVO> projectList = ConnectionUtils.getOnlyDataForProject(hiddangerAuditMapper.findAuditData(pageInfo));
			pageInfo.setTotal(projectList.size());
			pageInfo.setRows(projectList);
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
	}

	@Override
	@Transactional
	public int declare(ProjectAuditVO project) {
		int type = 0;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		
		//查询当前单位,用于提交上级单位
		Unit unit = hiddangerAudit_unitMapper.selectByPrimaryKey(project.getDeclareUnitId());
		//根据单位级别判断选择使用流程
		//在当前装备项目没有历史流程时，取当前单位判断所属的级别
		String processId = DataUtil.isNull(project.getProcessId()) ? getProcessByHiddanger(unit.getGradetype()) : project.getProcessId();
		
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", processId);
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = hiddangerAudit_stepMapper.findByCondition(customDatabase);
		
		//单位固定审批流程申报第一步 
		ApproveStep firstStep = stepList.get(1);
		
		//确定流程问题，这里需要获取到最后一次审批日志操作，然后找出对应的审批步骤，
		ApproveLog currentProjectApproveLog = null;
		
		ApproveStep defaultStep = stepList.get(0);
		ApproveLog defaultLog = null;
		
		int logNumber = 2;
		
		//当为新申报的时候
		if(StringUtils.isEmpty(project.getLogId())) {
			defaultLog = LogUtils.getDeclareLog(project, defaultStep, 1);
			logNumber = 2;
		} else {
			//当存在旧审批操作时，不考虑其内容，只取当前操作编号，自增，并绑定新的下级审批节点，从头发起
			currentProjectApproveLog = hiddangerAudit_logMapper.selectByPrimaryKey(project.getLogId());
			logNumber = currentProjectApproveLog.getLognumber() + 1;
			
			defaultLog = LogUtils.getDeclareLog(project, defaultStep, logNumber++);
			//查出下级步骤的权限等内容，生成新的
			//currentProjectApproveStep = project_stepMapper.selectByPrimaryKey(currentProjectApproveLog.getStepId());
		}
		
		//此处处理申报发起的状态，记录下来申报单位发起的日志
		//setLogSteplimit(defaultStep, defaultLog, unit);
		type = hiddangerAudit_logMapper.insertSelective(defaultLog);
		
		ApproveLog log = LogUtils.getNewApproveLog(project, firstStep, logNumber);
		
		setLogSteplimit(firstStep, log, unit);
		
		type = hiddangerAudit_logMapper.insertSelective(log);

		HiddangerDeclare hiddanger = HiddangerUtils.getDeclareData(null, project, defaultStep, log, processId);
		
		type = hiddangerAudit_declareMapper.updateByPrimaryKeySelective(hiddanger);
		
		return type;
	}

	@Override
	@Transactional
	public int beforeAudit(ProjectAuditVO project) {
		int type = 0;
		Date date = new Date();

		//变更审批操作状态和指定审核人
		ApproveLog log = new ApproveLog();
		log.setId(project.getLogId());
		log.setStatusId(ProcessType.LCZT_YSL);
		log.setApprovetorId(project.getActualApproverId());
		log.setLastupdatetime(date);
		type = hiddangerAudit_logMapper.updateByPrimaryKeySelective(log);
		
		HiddangerDeclare hiddanger = new HiddangerDeclare();//ScienceUtils.getBeforeAuditData(null, project);
		hiddanger.setId(project.getProjectId());
		hiddanger.setStatusId(ProjectType.XMZT_SHZ);
		//hiddanger.setReviewstatusId(ProcessType.LCZT_YSL);
		hiddanger.setLogId(project.getLogId());
		hiddanger.setLastupdatetime(date);
		
		type = hiddangerAudit_declareMapper.updateByPrimaryKeySelective(hiddanger);
		
		return type;
	}

	@Override
	@Transactional
	public int audit(ProjectAuditVO project, String auditType) {
		int type = 0;
		String temp = auditType;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		
		//查询当前单位,用于提交上级单位
		Unit unit = hiddangerAudit_unitMapper.selectByPrimaryKey(project.getApproveUnitId());
		//查询当前节点步骤的结束状态
		ApproveStep step = hiddangerAudit_stepMapper.selectByPrimaryKey(project.getStepId());
		
		//设置当前日志最后审核状态与打回状态
		auditType = "1".equals(auditType) ? step.getEndstatusId() : step.getBackstatusId();
		
		//变更当前操作状态
		ApproveLog log = LogUtils.getUpdateLogData(project, null, auditType);
		//保存现有的流程日志内容
		type = hiddangerAudit_logMapper.updateByPrimaryKeySelective(log);
		
		//查询出完整的当前操作日志信息
		log = hiddangerAudit_logMapper.selectByPrimaryKey(project.getLogId());
		
		//查询出当前流程节点及下一步节点
		ApproveStep thisStep = null;
		ApproveStep parentStep = null;
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", project.getProcessId());
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = hiddangerAudit_stepMapper.findByCondition(customDatabase);
		
		for(int i = 0; i < stepList.size(); i++) {
			thisStep = stepList.get(i);
			//找出当前对应的步骤
			if(log.getStepId().equals(thisStep.getId())) {
				//判断项目是否为终极节点,不是则取下级节点
				if(thisStep.getIslaststep() == 0) {
					parentStep = stepList.get(i+1);
					break;
				}
			}
		}
		
		String lastLogId = log.getId();
		
		//当存在下级节点任务时，进入处理下级任务
		if(parentStep != null && "1".equals(temp)) {
			ApproveLog newLog = LogUtils.getNewApproveLog(project, parentStep, log.getLognumber()+1);
			
			setLogSteplimit(parentStep, newLog, unit);
			
			type = hiddangerAudit_logMapper.insertSelective(newLog);
			lastLogId = newLog.getId();
		} else {
			//当不存在下级节点，则定义其为最后节点，完成后进入，这里进行二次校验
			if(thisStep.getIslaststep() == 1 && !auditType.equals(ProcessType.LCZT_BH)
					&& !auditType.equals(ProcessType.LCZT_BTG))
				StorageDataUtils.getNewStorageScience(project);
		}
		
		//处理项目状态及审核内容
		HiddangerDeclare hiddanger = HiddangerUtils.getAuditData(null, project, thisStep, log, auditType);
		hiddanger.setLogId(lastLogId);
		
		type = hiddangerAudit_declareMapper.updateByPrimaryKeySelective(hiddanger);
		
		return type;
	}

	@Override
	public void findAuditProcessLogData(PageInfo pageInfo) {
		Integer count = hiddangerAuditMapper.findAuditProcessLogDataCount(pageInfo.getQ());
		pageInfo.setTotal(count);
		if(count > 0) {
			pageInfo.setRows(hiddangerAuditMapper.findAuditProcessLogData(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
		
	}

	@Override
	@Transactional
	public int updateByCustoms(CustomDatabaseHandle customHandle) {
		// TODO Auto-generated method stub
		return hiddangerAuditMapper.updateByCustoms(customHandle);
	}

	
	/**
	 * 根据单位类别，指定对应的流程
	 * @param unitType
	 * @return processId
	 */
	private String getProcessByHiddanger(String unitType) {
		String processId = null;
		if(UnitGradeType.DWDJ_XJ.equals(unitType)) {
			processId = ProcessID.YHXM_XJ;
		} else if(UnitGradeType.DWDJ_CITY.equals(unitType)) {
			processId = ProcessID.YHXM_SJ;
		} else if(UnitGradeType.DWDJ_SJ.equals(unitType)) {
			processId = ProcessID.YHXM_SSJ;
		}
		return processId;
	}

	/**
	 * 根据步骤节点的权限指派，设置实际审批日志中的权限
	 * @param step
	 * @param log
	 * @param unit
	 */
	private void setLogSteplimit(ApproveStep step, ApproveLog log, Unit unit) {
		if(DataUtil.isNotNull(step.getStepuserId())) {//是否指定审核人
			log.setStepuserId(step.getStepuserId());
		} 
		if(DataUtil.isNotNull(step.getStepunitId())) {//是否指定审核部门
			log.setStepunitId(step.getStepunitId());
		}
		if(DataUtil.isNotNull(step.getSteproleId())) {//是否指定角色用户
			log.setStepunitId(step.getStepunitId());
		}
		if(step.getIsparentunit() == 1) {//是否上级单位审核
			//查询当前单位,用于提交上级单位
			log.setStepunitId(unit.getHigherLevelIdentifier());
		}
	}
	
}
