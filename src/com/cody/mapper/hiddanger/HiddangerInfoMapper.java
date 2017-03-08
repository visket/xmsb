package com.cody.mapper.hiddanger;

import com.cody.entity.hiddanger.HiddangerInfo;
import com.cody.mapper.BaseMapper;

public interface HiddangerInfoMapper extends BaseMapper{
	
    int deleteByPrimaryKey(String id);

    int insert(HiddangerInfo record);

    int insertSelective(HiddangerInfo record);

    HiddangerInfo selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HiddangerInfo record);

    int updateByPrimaryKey(HiddangerInfo record);
    
    int deleteByHiddangerId(String scienceId);
}