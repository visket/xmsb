package com.cody.mapper.science;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.science.ScienceBase;
import com.cody.mapper.BaseMapper;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.science.ScienceBaseVo;

public interface ScienceBaseMapper extends BaseMapper {
    int deleteByPrimaryKey(String id);

    int insert(ScienceBase record);

    int insertSelective(ScienceBase record);

    ScienceBase selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ScienceBase record);

    int updateByPrimaryKey(ScienceBase record);
    
    
    List<ProjectAuditVO> findAuditData(PageInfo pageInfo);
    
    Integer findAuditDataCount(PageInfo pageInfo);
	
	List<ProjectAuditVO> findAuditProcessLogData(PageInfo pageInfo);
	
	Integer findAuditProcessLogDataCount(String projectId);

	ScienceBaseVo selectByPrimaryKeyByVo(String id);
	
	ScienceBaseVo selectByUnitId(String unitId);
    
}