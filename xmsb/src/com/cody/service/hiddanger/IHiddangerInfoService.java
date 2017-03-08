package com.cody.service.hiddanger;

import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerInfo;

public interface IHiddangerInfoService {

	void find(PageInfo pageInfo);
	
	int insertSelective(HiddangerInfo hiddangerInfo);
	
	int updateByPrimaryKeySelective(HiddangerInfo hiddangerInfo);
	
	int deleteByHiddangerId(String id);
	
	int deleteByPrimaryKey(String id);
}
