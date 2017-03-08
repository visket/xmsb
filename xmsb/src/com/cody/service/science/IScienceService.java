package com.cody.service.science;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.science.ScienceBase;
import com.cody.vo.science.ScienceBaseVo;
import com.cody.vo.sys.UnitVo;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 科技项目Service
 * @author around
 * @date 2017-2-13
 */
public interface IScienceService {

	void find(PageInfo pageInfo);
	
	/**
	 * 添加
	 * 
	 * @param science
	 * @return
	 */
	int insertSelective(ScienceBase science);
	
	/**
	 * 查询总记录条数
	 * @return
	 */
	int findPageCountByCondition(PageInfo pageInfo);
	
	/**
	 * 查询当月记录条数
	 * @return
	 */
	int findToMonthCount();
	
	/**
	 * 查询最后一条记录
	 */
	Object findLastData();
	
	
	/**
	 * 查询科技项目待审核信息
	 * @author around
	 * @param pageInfo 封装user
	 * 
	 */
	void findAuditData(PageInfo pageInfo);
	
	
	/**
	 * 科技项目申报
	 * @author around
	 * @param ScienceBase
	 * @return 
	 */
	int declare(ProjectAuditVO project);
	
	
	/**
	 * 科技项目审核预处理数据，对开始审核的数据
	 * @param projectId
	 * @param logId
	 * @param user
	 * @return
	 */
	int beforeAudit(ProjectAuditVO project);
	
	
	/**
	 * 科技项目审核
	 * @param ScienceBase
	 * @return
	 */
	int audit(ProjectAuditVO project, String auditType);
	
	
	int updateByPrimaryKeySelective(ScienceBase science);
	
	/**
	 * 查询项目相关的审核流程信息
	 * @param pageInfo
	 */
	void findAuditProcessLogData(PageInfo pageInfo);
	
	/**
	 * 根据ID来查询
	 * @param id
	 */
	ScienceBase selectByPrimaryKey(String id);
	
	ScienceBaseVo selectByPrimaryKeyByVo(String id);
	
	int deleteByPrimaryKey(String id);
	
	/**
	 * 根据单位ID来查询一年是否只申报一次
	 * @param id
	 */
	ScienceBaseVo selectByUnitId(String unitId);
}
