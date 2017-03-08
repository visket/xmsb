package com.cody.service.approve;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveLog;


/**
 * 审批流程 -- 推送用户操作
 * @author around
 * @date 2016-12-30
 */
public interface IApproveLogService {

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
	 * @param log
	 * @return
	 */
	int insert(ApproveLog log);

	/**
	 * 添加
	 * 
	 * @param log
	 * @return
	 */
	int insertSelective(ApproveLog log);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	ApproveLog selectByPrimaryKey(String id);
	
	ApproveLog selectByPrimaryKeyAndCustom(PageInfo pageInfo);

	/**
	 * 修改
	 * 
	 * @param log
	 * @return
	 */
	int updateByPrimaryKeySelective(ApproveLog log);

	/**
	 * 修改
	 * 
	 * @param step
	 * @return
	 */
	int updateByPrimaryKey(ApproveLog log);
	
	int updateByCustoms(CustomDatabaseHandle customDatabase);

	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
}
