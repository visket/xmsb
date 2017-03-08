package com.cody.service.approve;

import java.util.List;
import java.util.Map;

import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveProcess;
import com.cody.entity.approve.ApproveStep;
import com.cody.entity.sys.Dictionary;
import com.cody.entity.sys.Item;

/***
 * 审批流程节点service
 * @author around
 * @data 2016-12-14
 * 
 */
public interface IApproveStepService {

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
	int insert(ApproveStep step);

	/**
	 * 添加
	 * 
	 * @param project
	 * @return
	 */
	int insertSelective(ApproveStep step);

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
	 * @param step
	 * @return
	 */
	int updateByPrimaryKeySelective(ApproveStep step);

	/**
	 * 修改
	 * 
	 * @param step
	 * @return
	 */
	int updateByPrimaryKey(ApproveStep step);
	
	int updateByCustoms(ApproveStep step);

	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);

}
