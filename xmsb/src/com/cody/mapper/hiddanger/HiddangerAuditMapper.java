package com.cody.mapper.hiddanger;

import java.util.List;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 隐患项目审核-Mapper
 * @author around
 *
 */
public interface HiddangerAuditMapper {

	
	int updateByCustoms(CustomDatabaseHandle customHandle);
	
	
	Integer findAuditDataCount(PageInfo pageInfo);
	
	/**
	 * 查询项目待审核信息
	 * @author around
	 * @param pageInfo 封装user
	 * 
	 */
	List<ProjectAuditVO> findAuditData(PageInfo pageInfo);
	
	
	/**
	 * 项目申报
	 * @author around
	 * @param ScienceBase
	 * @return 
	 */
	int declare(ProjectAuditVO project);
	

	/**
	 * 项目审核
	 * @param ScienceBase
	 * @return
	 */
	int audit(ProjectAuditVO project, String auditType);
	
	
	Integer findAuditProcessLogDataCount(String projectId);
	
	/**
	 * 查询项目相关的审核流程信息
	 * @param pageInfo
	 */
	List<ProjectAuditVO> findAuditProcessLogData(PageInfo pageInfo);
	
	
}
