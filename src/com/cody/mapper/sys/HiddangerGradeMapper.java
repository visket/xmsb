package com.cody.mapper.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.HiddangerGrade;
import com.cody.mapper.BaseMapper;

public interface HiddangerGradeMapper  extends BaseMapper{
	int deleteByPrimaryKey(String id);

	int insert(HiddangerGrade record);

	int insertSelective(HiddangerGrade record);

	HiddangerGrade selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(HiddangerGrade record);

	int updateByPrimaryKey(HiddangerGrade record);

	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<HiddangerGrade>
	 */
	List<HiddangerGrade> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);

}