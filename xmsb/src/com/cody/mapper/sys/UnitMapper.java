package com.cody.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.mapper.BaseMapper;
import com.cody.vo.sys.UnitVo;

public interface UnitMapper extends BaseMapper {

	int deleteByPrimaryKey(String id);

	int insert(Unit record);

	int insertSelective(Unit record);

	Unit selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(Unit record);

	int updateByPrimaryKey(Unit record);

	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<Unit>
	 */
	List<Unit> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);

	/**
	 * 通过组织ID查询单位
	 * 
	 * @param organizationId
	 * @return
	 */
	Unit selectByOrganizationId(Integer organizationId);

	List<Unit> findByUserId(Long id);

	/**
	 * 通过企业ID搜索本企业及下属企业的分页
	 * 
	 * @param pageInfo
	 * @return List<Unit>
	 */
	List<UnitVo> findPageByUnitId(PageInfo pageInfo);

	/**
	 * 通过企业ID搜索本企业及下属企业的分页 * @return
	 */
	Integer findPageCountByUnitId(PageInfo pageInfo);

	Unit findUnitByCombobox(@Param("unitId") String unitId);

	List<Unit> findAllUnitByCombobox();

	/**
	 * 个人中心通过用户ID查询
	 * 
	 * @param id
	 * @return
	 */
	UnitVo findVoByUserId(String id);

	List<Unit> findAllUnit();

	/**
	 * 过滤获取所有的获奖励企业
	 * 
	 * @return
	 */
	List<Unit> findToAward();

	/**
	 * 通过组织ID 删除对应的企业
	 * 
	 * @param id
	 * @return
	 */
	int deleteByOrganizationId(Long id);

	/**
	 * 逻辑删除
	 * 
	 * @return
	 */
	int deleteByStatus(Unit record);
	
	Unit selectUnitByOrgId(String id);
	
	/**
	 * 查询单位信息 by 单位类别
	 * @param typeId
	 * @return
	 */
	List<UnitVo> selectUnitByType(PageInfo pageInfo);
	
	Integer selectUnitByTypeCount(PageInfo pageInfo);

}