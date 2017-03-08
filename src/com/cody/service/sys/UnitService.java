package com.cody.service.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.sys.UnitVo;

public interface UnitService {

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByPrimaryKeys(String[] ids);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insert(Unit record);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Unit record);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Unit selectByPrimaryKey(String id);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Unit record);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Unit record);

	/**
	 * find
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);

	/**
	 * 通过组织ID查询单位
	 * 
	 * @param organizationId
	 * @return
	 */
	Unit selectByOrganizationId(Integer organizationId);

	/**
	 * 通过企业ID 查出所有企业及下属企业的 分页
	 * 
	 * @param pageInfo
	 */
	void findByUnitId(PageInfo pageInfo);

	Unit findUnitByCombobox(String unitId);

	List<Unit> findAllUnitByCombobox();

	// List<Unit> findByUserId(Long id);

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
	
	Unit findUnit(String id);


	void findUnitByTypeId(PageInfo pageInfo);
}
