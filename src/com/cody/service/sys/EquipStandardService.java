package com.cody.service.sys;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.EquipStandard;

public interface EquipStandardService {

	int deleteByPrimaryKey(String id);

	int insert(EquipStandard record);

	int insertSelective(EquipStandard record);

	EquipStandard selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(EquipStandard record);

	int updateByPrimaryKey(EquipStandard record);
	
	/**
	 * 装备标准目录分页
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	PageInfo findEquipChild(String unitType,String standarTypeId,String typeId,String eqbaseId, String selectStatus);
	
	PageInfo findEquipChildNew(String unitType,String standarTypeId,String typeId,String eqbaseId);
	
	//PageInfo findStandardCondition(String typeId);
	
	int deleteByPrimaryKeys(String[] ids);

}
