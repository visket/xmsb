package com.cody.mapper.project;

import java.util.List;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.entity.project.Project;
import com.cody.mapper.BaseMapper;


/**
 * 项目基础信息mapper
 * @author around
 *
 */
public interface ProjectMapper extends BaseMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(Project record);

    int insertSelective(Project record);

    Project selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Project record);

    int updateByPrimaryKey(Project record);
    
    int updateByCustoms(CustomDatabaseHandle customHandle);
    
    /**
     * 查询指定数据
     * @param pageinfo
     * @return List<Project>
     */
    List<Project> findPageByCondition(PageInfo pageInfo);
    
    /**
     * 查询数据的行数
     * @return
     */
    Integer findPageCountByCondition(PageInfo pageInfo);
    
    List<Project> findStandardCondition(CustomDatabaseHandle customDatabase);
    
}