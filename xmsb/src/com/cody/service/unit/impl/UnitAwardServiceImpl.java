package com.cody.service.unit.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Unit;
import com.cody.entity.unit.UnitAward;
import com.cody.mapper.unit.UnitAwardMapper;
import com.cody.service.unit.UnitAwardService;

@Service
public class UnitAwardServiceImpl implements UnitAwardService {

	@Resource
	private UnitAwardMapper unitAwardMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return unitAwardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UnitAward record) {
		return unitAwardMapper.insert(record);
	}

	@Override
	public int insertSelective(UnitAward record) {
		record.setId(IDUtil.UUID());
		return unitAwardMapper.insertSelective(record);
	}

	@Override
	public UnitAward selectByPrimaryKey(String id) {
		return unitAwardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UnitAward record) {
		return unitAwardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UnitAward record) {
		return unitAwardMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = unitAwardMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(unitAwardMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<UnitAward>());
		}
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return unitAwardMapper.deleteByPrimaryKeys(ids);
	}

}
