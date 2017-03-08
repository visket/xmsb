package com.cody.mapper.prokeyper;

import com.cody.entity.prokeyper.ProjectKeyperson;
import com.cody.mapper.BaseMapper;


public interface ProjectKeypersonMapper extends BaseMapper{
	
    int deleteByPrimaryKey(String id);
    
    int deleteByScienceBaseId(String scienceId);

    int insert(ProjectKeyperson record);

    int insertSelective(ProjectKeyperson record);

    ProjectKeyperson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectKeyperson record);

    int updateByPrimaryKey(ProjectKeyperson record);
}