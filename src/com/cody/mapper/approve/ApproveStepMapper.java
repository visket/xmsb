package com.cody.mapper.approve;

import com.cody.entity.approve.ApproveStep;
import com.cody.mapper.BaseMapper;

public interface ApproveStepMapper extends BaseMapper {
	
	int deleteByPrimaryKey(String id);

    int insert(ApproveStep record);

    int insertSelective(ApproveStep record);

    ApproveStep selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ApproveStep record);

    int updateByPrimaryKey(ApproveStep record);
}