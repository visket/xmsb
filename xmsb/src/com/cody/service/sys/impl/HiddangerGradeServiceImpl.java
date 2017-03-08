package com.cody.service.sys.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.EquipStandard;
import com.cody.entity.sys.HiddangerGrade;
import com.cody.mapper.sys.HiddangerGradeMapper;
import com.cody.service.sys.HiddangerGradeService;

@Service
public class HiddangerGradeServiceImpl implements HiddangerGradeService {

	@Resource
	private HiddangerGradeMapper hiddangerGradeMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return hiddangerGradeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(HiddangerGrade record) {
		return hiddangerGradeMapper.insert(record);
	}

	@Override
	public int insertSelective(HiddangerGrade record) {
		return hiddangerGradeMapper.insertSelective(record);
	}

	@Override
	public HiddangerGrade selectByPrimaryKey(String id) {
		return hiddangerGradeMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(HiddangerGrade record) {
		return hiddangerGradeMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(HiddangerGrade record) {
		return hiddangerGradeMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = hiddangerGradeMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(hiddangerGradeMapper
					.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<EquipStandard>());
		}
	}

}
