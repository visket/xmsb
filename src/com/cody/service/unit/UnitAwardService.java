package com.cody.service.unit;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.unit.UnitAward;

public interface UnitAwardService {
	int deleteByPrimaryKey(String id);

	int insert(UnitAward record);

	int insertSelective(UnitAward record);

	UnitAward selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(UnitAward record);

	int updateByPrimaryKey(UnitAward record);
	
	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	int deleteByPrimaryKeys(String[] ids);

}
