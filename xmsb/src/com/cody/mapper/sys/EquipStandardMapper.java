package com.cody.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.EquipStandard;
import com.cody.mapper.BaseMapper;

public interface EquipStandardMapper extends BaseMapper {
	int deleteByPrimaryKey(String id);

	int insert(EquipStandard record);

	int insertSelective(EquipStandard record);

	EquipStandard selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(EquipStandard record);

	int updateByPrimaryKey(EquipStandard record);

	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<Project>
	 */
	List<EquipStandard> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);

	// Integer findCountEquipChild();

	List<EquipStandard> findEquipChild(@Param("unitType") String unitType,
			@Param("standartypeid") String standarTypeId,
			@Param("typecode") String typeId,
			@Param("eqbaseId") String eqbaseId,
			@Param("selectStatus") String selectStatus);

	List<EquipStandard> findEquipChildNew(@Param("unitType") String unitType,
			@Param("standartypeid") String standarTypeId,
			@Param("typecode") String typeId, @Param("eqbaseId") String eqbaseId);
}