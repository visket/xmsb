package com.cody.service.equip.impl;

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
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.sys.Unit;
import com.cody.mapper.approve.ApproveLogMapper;
import com.cody.mapper.approve.ApproveStepMapper;
import com.cody.mapper.equip.EquipBaseMapper;
import com.cody.mapper.sys.UnitMapper;
import com.cody.service.equip.EquipService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;

@Service
public class EquipServiceImpl implements EquipService {

	@Resource 
	private EquipBaseMapper equipBaseMapper;
	
	@Resource
	private ApproveStepMapper equip_stepMapper;
	
	@Resource
	private ApproveLogMapper equip_logMapper;
	
	@Resource
	private UnitMapper equip_unitMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = equipBaseMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(equipBaseMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<EquipBaseVo>());
		}
	}
	
	public EquipBaseVo findProvince(String id){
		return equipBaseMapper.selectByOrgIdKey(id);
	}

	@Override
	@Transactional
	public int insertSelective(EquipBase equipBase) {
		// TODO Auto-generated method stub
		equipBase.setId(IDUtil.UUID());
		return equipBaseMapper.insertSelective(equipBase);
	}
	
	@Override
	public int findPageCountByCondition(PageInfo pageInfo){
		return equipBaseMapper.findPageCountByCondition(pageInfo);
	}

	@Override
	public int findToMonthCount() {
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_equip_base");
		return equipBaseMapper.findToMonthDataCount(customHandle);
	}

	@Override
	public void findAuditData(PageInfo pageInfo) {
		Integer count = equipBaseMapper.findAuditDataCount(pageInfo);
		if(count > 0) {
			List<ProjectAuditVO> projectList = ConnectionUtils.getOnlyDataForProject(equipBaseMapper.findAuditData(pageInfo));
			pageInfo.setTotal(projectList.size());
			pageInfo.setRows(projectList);
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
	}

	//申报
	@Override
	@Transactional
	public int declare(ProjectAuditVO project) {
		int type = 0;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		
		//查询当前单位,用于提交上级单位
		Unit unit = equip_unitMapper.selectByPrimaryKey(project.getDeclareUnitId());
		//根据单位级别判断选择使用流程
		//在当前装备项目没有历史流程时，取当前单位判断所属的级别
		String processId = DataUtil.isNull(project.getProcessId()) ? getProcess(unit.getGradetype()) : project.getProcessId();
		
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", processId);
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = equip_stepMapper.findByCondition(customDatabase);
		
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
			currentProjectApproveLog = equip_logMapper.selectByPrimaryKey(project.getLogId());
			logNumber = currentProjectApproveLog.getLognumber() + 1;
			
			defaultLog = LogUtils.getDeclareLog(project, defaultStep, logNumber++);
			//查出下级步骤的权限等内容，生成新的
			//currentProjectApproveStep = project_stepMapper.selectByPrimaryKey(currentProjectApproveLog.getStepId());
		}
		
		//此处处理申报发起的状态，记录下来申报单位发起的日志
		//setLogSteplimit(defaultStep, defaultLog, unit);
		type = equip_logMapper.insertSelective(defaultLog);
		
		ApproveLog log = LogUtils.getNewApproveLog(project, firstStep, logNumber);
		setLogSteplimit(firstStep, log, unit);
		type = equip_logMapper.insertSelective(log);
		
		//修改项目状态
		EquipBase equip = EquipUtils.getDeclareData(project, log, processId);
		type = equipBaseMapper.updateByPrimaryKeySelective(equip);
		
		return type;
	}

	
	/**
	 * 根据单位类别，指定对应的流程
	 * @param unitType
	 * @return processId
	 */
	private String getProcess(String unitType) {
		String processId = null;
		if(UnitGradeType.DWDJ_XJ.equals(unitType)) {
			processId = ProcessID.ZBXM_XJ;
		} else if(UnitGradeType.DWDJ_CITY.equals(unitType)) {
			processId = ProcessID.ZBXM_SJ;
		}
		return processId;
	}
	
	//受理前缀
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
		type = equip_logMapper.updateByPrimaryKeySelective(log);
		
		//变更装备项目状态 为审核中，绑定实时审批操作对象
		EquipBase equip = new EquipBase();
		equip.setId(project.getProjectId());
		equip.setStatusId(ProjectType.XMZT_SHZ);
		//equip.setReviewstatusId(ProcessType.LCZT_YSL);
		equip.setLogId(project.getLogId());
		equip.setLastupdatetime(date);
		
		type = equipBaseMapper.updateByPrimaryKeySelective(equip);
		
		return type;
	}

	//审核
	@Override
	@Transactional
	public int audit(ProjectAuditVO project, String auditType) {
		int type = 0;
		String temp = auditType;
		CustomDatabaseHandle customDatabase = new CustomDatabaseHandle();
		Map<String, Object> condition = customDatabase.getCondition();
		
		//查询当前单位,用于提交上级单位
		Unit unit = equip_unitMapper.selectByPrimaryKey(project.getApproveUnitId());
		
		//查询当前节点步骤的结束状态
		ApproveStep step = equip_stepMapper.selectByPrimaryKey(project.getStepId());
		
		//设置当前日志最后审核状态与打回状态
		auditType = "1".equals(auditType) ? step.getEndstatusId() : step.getBackstatusId();
		
		ApproveLog log = LogUtils.getUpdateLogData(project, null, auditType);
		
		//保存现有的流程日志内容
		type = equip_logMapper.updateByPrimaryKeySelective(log);
		
		log = equip_logMapper.selectByPrimaryKey(project.getLogId());
		
		//查询出当前流程节点及下一步节点
		ApproveStep thisStep = null;
		ApproveStep parentStep = null;
		customDatabase.setTablename("t_approve_step");
		condition.put("process_id = ", project.getProcessId());
		customDatabase.setCondition(condition);
		List<ApproveStep> stepList = equip_stepMapper.findByCondition(customDatabase);
		
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
		if(parentStep != null  && "1".equals(temp)) {
			ApproveLog newLog = LogUtils.getNewApproveLog(project, parentStep, log.getLognumber()+1);
			
			setLogSteplimit(parentStep, newLog, unit);
			
			type = equip_logMapper.insertSelective(newLog);
			lastLogId = newLog.getId();
		} else {
			
		}
		
		//处理装备项目状态及审核内容
		EquipBase equip = EquipUtils.getAuditReturnData(null, thisStep, log, auditType);
		equip.setLogId(lastLogId);
		
		
		type = equipBaseMapper.updateByPrimaryKeySelective(equip);
		
		return type;
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
			log.setSteproleId(step.getSteproleId());
		}
		if(step.getIsparentunit() == 1) {//是否上级单位审核
			//查询当前单位,用于提交上级单位
			log.setStepunitId(unit.getHigherLevelIdentifier());
		}
	}

	@Override
	public int updateByPrimaryKeySelective(EquipBase equipBase) {
		// TODO Auto-generated method stub
		//equipBase.setId(IDUtil.UUID());
		return equipBaseMapper.updateByPrimaryKeySelective(equipBase);
	}
	
	@Override
	public void findAuditProcessLogData(PageInfo pageInfo) {
		Integer count = equipBaseMapper.findAuditProcessLogDataCount(pageInfo.getQ());
		
		if(count > 0) {
			pageInfo.setTotal(count);
			pageInfo.setRows(equipBaseMapper.findAuditProcessLogData(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
	}

	@Override
	public EquipBase selectByPrimaryKey(String id) {
		return equipBaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public EquipBaseVo selectByPrimaryKeyByVO(String id) {
		return equipBaseMapper.selectByPrimaryKeyByVO(id);
	}


	public int deleteByPrimaryKey(String id){
		return equipBaseMapper.deleteByPrimaryKey(id);
	}
	
	public Object findLastData(){
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_equip_base");
		Object obj = equipBaseMapper.findLastData(customHandle);
		return obj;
	}
	
}
