package com.cody.service.project;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.entity.project.Project;


/**
 * 项目基础信息
 * @author around
 *
 */
public interface IProjectService {

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	
	int deleteByPrimaryKeys(String[] ids);

	/**
	 * 添加
	 * 
	 * @param project
	 * @return
	 */
	int insert(Project project);

	/**
	 * 添加
	 * 
	 * @param project
	 * @return
	 */
	int insertSelective(Project project);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Project selectByPrimaryKey(String id);

	/**
	 * 修改
	 * 
	 * @param project
	 * @return
	 */
	int updateByPrimaryKeySelective(Project project);

	/**
	 * 修改
	 * 
	 * @param project
	 * @return
	 */
	int updateByPrimaryKey(Project project);
	
	int updateByCustoms(CustomDatabaseHandle customHandle);

	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	
	int processDeclare(Project project);
	
	
	
	/**
	 * 查询当月记录条数
	 * @return
	 */
	int findToMonthCount();
	
	
	
	
}
