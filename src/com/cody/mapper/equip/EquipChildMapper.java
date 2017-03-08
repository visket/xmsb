package com.cody.mapper.equip;

import org.apache.ibatis.annotations.Param;

import com.cody.entity.equip.EquipChild;
import com.cody.entity.sys.EquipStandard;
import com.cody.mapper.BaseMapper;

public interface EquipChildMapper extends BaseMapper{
	
    int insert(EquipChild record);

    int insertSelective(EquipChild record);
    
    int updateByPrimaryKeySelective(EquipChild record);
    
    int deleteByEqbaseId(String eqbaseId);
    
}