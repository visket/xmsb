package com.cody.service.sys;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.HiddangerExamine;

public interface HiddangerExamineService{
	int deleteByPrimaryKey(String id);

	int insert(HiddangerExamine record);

	int insertSelective(HiddangerExamine record);

	HiddangerExamine selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(HiddangerExamine record);

	int updateByPrimaryKey(HiddangerExamine record);
	
	/**
	 * 分页
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
}
