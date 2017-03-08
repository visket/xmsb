package com.cody.service.equip;

import com.cody.common.utils.PageInfo;
import com.cody.entity.equip.EquipChild;

public interface EquipChildService {

	void find(PageInfo pageInfo);
	
	int insertSelective(EquipChild equipChild);
	
	int updateByPrimaryKeySelective(EquipChild equipChild);
	
	int deleteByEqbaseId(String eqbaseId);
}
