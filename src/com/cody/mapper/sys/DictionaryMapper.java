package com.cody.mapper.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.project.Project;
import com.cody.entity.sys.Dictionary;
import com.cody.mapper.BaseMapper;

public interface DictionaryMapper extends BaseMapper {

	int deleteByPrimaryKey(String id);

	int insert(Dictionary record);

	int insertSelective(Dictionary record);

	Dictionary selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Dictionary record);

	int updateByPrimaryKey(Dictionary record);

	/**
     * 查询指定数据
     * @param pageinfo
     * @return List<Project>
     */
    List<Dictionary> findPageByCondition(PageInfo pageInfo);
    
    /**
     * 查询数据的行数
     * @return
     */
    Integer findPageCountByCondition(PageInfo pageInfo);
}