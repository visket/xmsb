package com.cody.service.project;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.entity.project.Project;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 抽象  项目流程Service
 * @author around
 *
 */
public interface IProjectAuditService {

	
	int updateByCustoms(CustomDatabaseHandle customHandle);
	
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
	
	/**
	 * 查询项目相关的审核流程信息
	 * @param pageInfo
	 */
	void findAuditProcessLogData(PageInfo pageInfo);
}
