package com.cody.service.sys.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.EquipStandard;
import com.cody.entity.sys.HiddangerExamine;
import com.cody.mapper.sys.HiddangerExamineMapper;
import com.cody.service.sys.HiddangerExamineService;

@Service
public class HiddangerExamineServiceImpl implements HiddangerExamineService {

	@Resource
	private HiddangerExamineMapper hiddangerExamineMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return hiddangerExamineMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(HiddangerExamine record) {
		return hiddangerExamineMapper.insert(record);
	}

	@Override
	public int insertSelective(HiddangerExamine record) {
		return hiddangerExamineMapper.insertSelective(record);
	}

	@Override
	public HiddangerExamine selectByPrimaryKey(String id) {
		return hiddangerExamineMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HiddangerExamine record) {
		return hiddangerExamineMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HiddangerExamine record) {
		return hiddangerExamineMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = hiddangerExamineMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(hiddangerExamineMapper
					.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<EquipStandard>());
		}
	}

}
