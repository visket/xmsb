package com.cody.mapper.sys;

import com.cody.entity.sys.Job;

public interface JobMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(Job record);

    int insertSelective(Job record);

    Job selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Job record);

    int updateByPrimaryKey(Job record);
}