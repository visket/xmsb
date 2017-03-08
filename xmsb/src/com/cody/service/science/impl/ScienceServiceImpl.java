package com.cody.service.science.impl;

import java.util.ArrayList;
import java.util.Date;
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
import com.cody.common.utils.array.ConnectionUtils;
import com.cody.common.utils.finals.ProcessID;
import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.finals.UnitGradeType;
import com.cody.common.utils.object.EquipUtils;
import com.cody.common.utils.object.LogUtils;
import com.cody.common.utils.object.ScienceUtils;
import com.cody.common.utils.object.StorageDataUtils;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.storage.StorageScience;
import com.cody.entity.sys.Unit;
import com.cody.mapper.approve.ApproveLogMapper;
import com.cody.mapper.approve.ApproveStepMapper;
import com.cody.mapper.science.ScienceBaseMapper;
import com.cody.mapper.storage.StorageScienceMapper;
import com.cody.mapper.sys.UnitMapper;
import com.cody.service.hiddanger.IHiddangerAuditService;
import com.cody.service.hiddanger.IHiddangerService;
import com.cody.service.science.IScienceService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.science.ScienceBaseVo;
import com.cody.vo.sys.UnitVo;

@Service
public class ScienceServiceImpl implements IScienceService {

	@Resource
	private ScienceBaseMapper scienceMapper;
	
	@Resource
	private ApproveStepMapper science_stepMapper;
	
	@Resource
	private ApproveLogMapper science_logMapper;
	
	@Resource
	private UnitMapper science_unitMapper;
	
	@Resource
	private StorageScienceMapper science_storageService;
	
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = scienceMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(scienceMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<EquipBaseVo>());
		}
	}

	@Override
	@Transactional
	public int insertSelective(ScienceBase science) {
		science.setId(IDUtil.UUID());
		return scienceMapper.insertSelective(science);
	}

	@Override
	public int findPageCountByCondition(PageInfo pageInfo) {
		return scienceMapper.findPageCountByCondition(pageInfo);
	}

	@Override
	public int findToMonthCount() {
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_science_base");
		int a= scienceMapper.findToMonthDataCount(customHandle);
		return a;
	}
	
	public Object findLastData(){
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_science_base");
		Object obj = scienceMapper.findLastData(customHandle);
		return obj;
	}

	@Override
	public void findAuditData(PageInfo pageInfo) {
		Integer count = scienceMapper.findAuditDataCount(pageInfo);
		
		if(count > 0) {
			//List<ProjectAuditVO>scienceMapper.findAuditData(pageInfo);
			List<ProjectAuditVO> projectList = ConnectionUtils.getOnlyDataForProject(scienceMapper.findAuditData(pageInfo));
			//List<ProjectAuditVO> projectList = scienceMapper.findAuditData(pageInfo);
			//projectList.add(null);
			//projectList.add(null);
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
		Unit unit = science_unitMapper.selectByPrimaryKey(project.getDeclareUnitId());
		//根据单位级别判断选择使用流程
		//在当前装备项目没有历史流程时，取当前单位判断所属的级别
		String processId = DataUtil.isNull(project.getProcessId()) ? getProcessByScience(unit.getGradetype()) : project.getProcessId();
		
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", processId);
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = science_stepMapper.findByCondition(customDatabase);
		
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
			currentProjectApproveLog = science_logMapper.selectByPrimaryKey(project.getLogId());
			logNumber = currentProjectApproveLog.getLognumber() + 1;
			
			defaultLog = LogUtils.getDeclareLog(project, defaultStep, logNumber++);
			//查出下级步骤的权限等内容，生成新的
			//currentProjectApproveStep = project_stepMapper.selectByPrimaryKey(currentProjectApproveLog.getStepId());
		}
		
		//此处处理申报发起的状态，记录下来申报单位发起的日志
		//setLogSteplimit(defaultStep, defaultLog, unit);
		type = science_logMapper.insertSelective(defaultLog);
		
		ApproveLog log = LogUtils.getNewApproveLog(project, firstStep, logNumber);
		setLogSteplimit(firstStep, log, unit);
		type = science_logMapper.insertSelective(log);

		ScienceBase science = ScienceUtils.getDeclareData(null, project, defaultStep, log, processId);
		
		type = scienceMapper.updateByPrimaryKeySelective(science);
		
		return type;
	}

	@Override
	@Transactional
	public int beforeAudit(ProjectAuditVO project) {
		int type = 0;
		Date date = new Date();
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		Map<String, Object> updates = customDatabase.getUpdates();
		
		//变更审批操作状态和指定审核人
		customDatabase.setTablename("t_approve_log");
		updates.put("status_id = ", ProcessType.LCZT_YSL);
		updates.put("approvetor_id = ", project.getActualApproverId());
		updates.put("lastupdatetime = ", date);
		customDatabase.setUpdates(updates);
		condition.put("id = ", project.getLogId());
		customDatabase.setCondition(condition);
		
		type = science_logMapper.updateByCustoms(customDatabase);
		
		ScienceBase science = ScienceUtils.getBeforeAuditData(null, project);
		science.setReviewstatusId(null);
		type = scienceMapper.updateByPrimaryKeySelective(science);
		
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
		Unit unit = science_unitMapper.selectByPrimaryKey(project.getApproveUnitId());
		//查询当前节点步骤的结束状态
		ApproveStep step = science_stepMapper.selectByPrimaryKey(project.getStepId());
		
		//设置当前日志最后审核状态与打回状态
		auditType = "1".equals(auditType) ? step.getEndstatusId() : step.getBackstatusId();
		
		//变更当前操作状态
		ApproveLog log = LogUtils.getUpdateLogData(project, null, auditType);
		//保存现有的流程日志内容
		type = science_logMapper.updateByPrimaryKeySelective(log);
		
		//查询出完整的当前操作日志信息
		log = science_logMapper.selectByPrimaryKey(project.getLogId());
		
		//查询出当前流程节点及下一步节点
		ApproveStep thisStep = null;
		ApproveStep parentStep = null;
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", project.getProcessId());
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = science_stepMapper.findByCondition(customDatabase);
		
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
			
			type = science_logMapper.insertSelective(newLog);
			lastLogId = newLog.getId();
		} else {
			//当不存在下级节点，则定义其为最后节点，完成后进入，这里进行二次校验
			if(thisStep.getIslaststep() == 1 && !auditType.equals(ProcessType.LCZT_BH)
					&& !auditType.equals(ProcessType.LCZT_BTG)) {
				science_storageService.insertSelective(StorageDataUtils.getNewStorageScience(project));
			}
		}
		
		//处理项目状态及审核内容
		ScienceBase science = ScienceUtils.getAuditData(null, project, thisStep, log, auditType);
		science.setLogId(lastLogId);
		
		type = scienceMapper.updateByPrimaryKeySelective(science);
		
		return type;
	}

	@Override
	@Transactional
	public int updateByPrimaryKeySelective(ScienceBase science) {
		return scienceMapper.updateByPrimaryKeySelective(science);
	}

	@Override
	public void findAuditProcessLogData(PageInfo pageInfo) {
		Integer count = scienceMapper.findAuditProcessLogDataCount(pageInfo.getQ());
		pageInfo.setTotal(count);
		if(count > 0) {
			pageInfo.setRows(scienceMapper.findAuditProcessLogData(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
	}

	@Override
	public ScienceBase selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return scienceMapper.selectByPrimaryKey(id);
	}

	@Override
	public ScienceBaseVo selectByPrimaryKeyByVo(String id) {
		// TODO Auto-generated method stub
		return scienceMapper.selectByPrimaryKeyByVo(id);
	}
	
	
	/**
	 * 根据单位类别，指定对应的流程
	 * @param unitType
	 * @return processId
	 */
	private String getProcessByScience(String unitType) {
		String processId = null;
		if(UnitGradeType.DWDJ_XJ.equals(unitType)) {
			processId = ProcessID.KJXM_XJ;
		} else if(UnitGradeType.DWDJ_CITY.equals(unitType)) {
			processId = ProcessID.KJXM_SJ;
		} else if(UnitGradeType.DWDJ_SJ.equals(unitType)) {
			processId = ProcessID.KJXM_SSJ;
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


	public int deleteByPrimaryKey(String id){
		return scienceMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public ScienceBaseVo selectByUnitId(String unitId) {
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_science_base");
		return scienceMapper.selectByUnitId(unitId);
	}
	
}
