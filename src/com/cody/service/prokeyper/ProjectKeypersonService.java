package com.cody.service.prokeyper;

import com.cody.common.utils.PageInfo;
import com.cody.entity.prokeyper.ProjectKeyperson;

public interface ProjectKeypersonService {

	void find(PageInfo pageInfo);
	
	int insertSelective(ProjectKeyperson projectKeyperson);
	
	int updateByPrimaryKeySelective(ProjectKeyperson projectKeyperson);
	
	int deleteByPrimaryKey(String id);
	
	int deleteByScienceBaseId(String id);
	
}
