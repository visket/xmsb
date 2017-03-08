package com.cody.mapper.hiddanger;

import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.mapper.BaseMapper;
import com.cody.vo.hiddanger.HiddangerDeclareVo;

public interface HiddangerDeclareMapper extends BaseMapper{
	
    int deleteByPrimaryKey(String id);

    int insert(HiddangerDeclare record);

    int insertSelective(HiddangerDeclare record);

    HiddangerDeclare selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HiddangerDeclare record);

    int updateByPrimaryKeyWithBLOBs(HiddangerDeclare record);

    int updateByPrimaryKey(HiddangerDeclare record);
    
    HiddangerDeclareVo selectByPrimaryKeyVo(String id);
    
    
}