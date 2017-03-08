package com.cody.mapper.sys;

import java.util.List;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysApp;

public interface SysAppMapper {
	
    int deleteByPrimaryKey(Long id);

    int insert(SysApp record);

    int insertSelective(SysApp record);

    SysApp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SysApp record);

    int updateByPrimaryKey(SysApp record);

	List findAppPageCondition(PageInfo pageInfo);

	int findAppPageCount(PageInfo pageInfo);

	SysApp findAppByCode(String appCode);
}