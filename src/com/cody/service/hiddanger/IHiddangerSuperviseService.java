package com.cody.service.hiddanger;

import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerSupervise;

public interface IHiddangerSuperviseService {

	void find(PageInfo pageInfo);
	
	int insertSelective(HiddangerSupervise hiddangerSupervise);
	
	int updateByPrimaryKeySelective(HiddangerSupervise hiddangerSupervise);
	
	int deleteByHiddangerId(String id);
	
	int deleteByPrimaryKey(String id);
	
}
