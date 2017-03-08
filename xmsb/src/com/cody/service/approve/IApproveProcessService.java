package com.cody.service.approve;

import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveProcess;



/**
 * 审批流程service
 * @author around
 * @date 2016-12-26
 */
public interface IApproveProcessService {

	
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
	 * @param process
	 * @return
	 */
	int insert(ApproveProcess process);

	/**
	 * 添加
	 * 
	 * @param project
	 * @return
	 */
	int insertSelective(ApproveProcess process);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	ApproveProcess selectByPrimaryKey(String id);

	/**
	 * 修改
	 * 
	 * @param process
	 * @return
	 */
	int updateByPrimaryKeySelective(ApproveProcess process);

	/**
	 * 修改
	 * 
	 * @param process
	 * @return
	 */
	int updateByPrimaryKey(ApproveProcess process);
	
	int updateByCustoms(ApproveProcess process);

	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	
	
}
