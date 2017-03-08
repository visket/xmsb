package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.mapper.sys.UnitMapper;
import com.cody.service.sys.UnitService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.sys.UnitVo;

@Service
public class UnitServiceImpl implements UnitService {

	@Resource
	private UnitMapper unitMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return unitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Unit record) {
		record.setId(IDUtil.UUID());
		return unitMapper.insert(record);
	}

	@Override
	public int insertSelective(Unit record) {
		record.setId(IDUtil.UUID());
		record.setStatus(0);
		return unitMapper.insertSelective(record);
	}

	@Override
	public Unit selectByPrimaryKey(String id) {
		return unitMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Unit record) {
		int a = unitMapper.updateByPrimaryKeySelective(record);
		System.out.println(a);
		return unitMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Unit record) {
		return unitMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = unitMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(unitMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Unit>());
		}
	}

	@Override
	public Unit selectByOrganizationId(Integer organizationId) {
		return unitMapper.selectByOrganizationId(organizationId);
	}

	@Override
	public void findByUnitId(PageInfo pageInfo) {
		int total = unitMapper.findPageCountByUnitId(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(unitMapper.findPageByUnitId(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Unit>());
		}
	}

	@Override
	public Unit findUnitByCombobox(String unitId) {
		return unitMapper.findUnitByCombobox(unitId);
	}

	@Override
	public List<Unit> findAllUnitByCombobox() {
		return unitMapper.findAllUnitByCombobox();
	}

	@Override
	public UnitVo findVoByUserId(String id) {
		return unitMapper.findVoByUserId(id);
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return unitMapper.deleteByPrimaryKeys(ids);
	}

	@Override
	public List<Unit> findAllUnit() {
		return unitMapper.findAllUnit();
	}

	@Override
	public List<Unit> findToAward() {
		return unitMapper.findToAward();
	}

	@Override
	public int deleteByOrganizationId(Long id) {
		return unitMapper.deleteByOrganizationId(id);
	}

	@Override
	public int deleteByStatus(Unit record) {
		return unitMapper.deleteByStatus(record);
	}
	
	public Unit findUnit(String id){
		return unitMapper.selectUnitByOrgId(id);
	}

	@Override
	public void findUnitByTypeId(PageInfo pageInfo) {
		Integer count = unitMapper.selectUnitByTypeCount(pageInfo);
		pageInfo.setTotal(count);
		if(count > 0) {
			pageInfo.setRows(unitMapper.selectUnitByType(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<UnitVo>());
		}
	}

}
