package com.cody.mapper.hiddanger;

import com.cody.entity.hiddanger.HiddangerSupervise;
import com.cody.mapper.BaseMapper;

public interface HiddangerSuperviseMapper extends BaseMapper{
    int deleteByPrimaryKey(String id);

    int insert(HiddangerSupervise record);

    int insertSelective(HiddangerSupervise record);

    HiddangerSupervise selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HiddangerSupervise record);

    int updateByPrimaryKeyWithBLOBs(HiddangerSupervise record);

    int updateByPrimaryKey(HiddangerSupervise record);
    
    int deleteByHiddangerId(String scienceId);
    
}