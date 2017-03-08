package com.cody.mapper.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.HiddangerExamine;
import com.cody.mapper.BaseMapper;

public interface HiddangerExamineMapper extends BaseMapper {
	int deleteByPrimaryKey(String id);

	int insert(HiddangerExamine record);

	int insertSelective(HiddangerExamine record);

	HiddangerExamine selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(HiddangerExamine record);

	int updateByPrimaryKey(HiddangerExamine record);

	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<HiddangerExamine>
	 */
	List<HiddangerExamine> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);
}