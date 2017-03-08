package com.cody.mapper.sys;

import java.util.List;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysLog;

public interface SysLogMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysLog record);

    int insertSelective(SysLog record);

    SysLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysLog record);

    int updateByPrimaryKey(SysLog record);

    List findDataGrid(PageInfo pageInfo);

    int findDataGridCount(PageInfo pageInfo);
}