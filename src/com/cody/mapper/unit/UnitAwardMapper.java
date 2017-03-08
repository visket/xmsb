package com.cody.mapper.unit;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.unit.UnitAward;
import com.cody.mapper.BaseMapper;

public interface UnitAwardMapper extends BaseMapper {
	int deleteByPrimaryKey(String id);

	int insert(UnitAward record);

	int insertSelective(UnitAward record);

	UnitAward selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(UnitAward record);

	int updateByPrimaryKey(UnitAward record);

	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<UnitAward>
	 */
	List<UnitAward> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);
}