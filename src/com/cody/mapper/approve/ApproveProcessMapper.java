package com.cody.mapper.approve;

import com.cody.entity.approve.ApproveProcess;
import com.cody.mapper.BaseMapper;

public interface ApproveProcessMapper extends BaseMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(ApproveProcess record);

    int insertSelective(ApproveProcess record);

    ApproveProcess selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApproveProcess record);

    int updateByPrimaryKey(ApproveProcess record);
    
    int updateByCustoms(ApproveProcess process);
}