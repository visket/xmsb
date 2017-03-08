package com.cody.mapper.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cody.common.utils.TreeInfo;
import com.cody.entity.sys.SysArea;

public interface SysAreaMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    SysArea selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);

	int deleteByPids(String parentIds);

	List<SysArea> findListByCondition(TreeInfo treeInfo);

	SysArea selectByCode(String code);
	
	List<SysArea> findSysAreaAll();
	
	List<SysArea> findSysAreaAllByPid(@Param("pid") String pid);
	
	List<SysArea> findAreaCodeAll();
	
	int deleteAreaAllByCode(String code);
}