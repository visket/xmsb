package com.cody.mapper.project;

import com.cody.entity.project.ProjectDeclare;


/**
 * 项目申报 mapper
 * @author around
 *
 */
public interface ProjectDeclareMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(ProjectDeclare record);

    int insertSelective(ProjectDeclare record);

    ProjectDeclare selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ProjectDeclare record);

    int updateByPrimaryKeyWithBLOBs(ProjectDeclare record);

    int updateByPrimaryKey(ProjectDeclare record);
}