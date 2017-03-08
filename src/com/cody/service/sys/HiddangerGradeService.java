package com.cody.service.sys;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.HiddangerGrade;

public interface HiddangerGradeService {
	int deleteByPrimaryKey(String id);

	int insert(HiddangerGrade record);

	int insertSelective(HiddangerGrade record);

	HiddangerGrade selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(HiddangerGrade record);

	int updateByPrimaryKey(HiddangerGrade record);

	/**
	 * 分页
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
}
