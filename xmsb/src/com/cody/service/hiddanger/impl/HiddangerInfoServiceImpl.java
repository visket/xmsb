package com.cody.service.hiddanger.impl;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerInfo;
import com.cody.entity.project.Project;
import com.cody.entity.science.ScienceBase;
import com.cody.mapper.hiddanger.HiddangerInfoMapper;
import com.cody.service.hiddanger.IHiddangerInfoService;

@Service
public class HiddangerInfoServiceImpl implements IHiddangerInfoService {

	@Resource
	HiddangerInfoMapper hiddangerInfoMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = hiddangerInfoMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(hiddangerInfoMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Project>());
		}
	}
	
	@Override
	@Transactional
	public int insertSelective(HiddangerInfo hiddangerInfo) {
		hiddangerInfo.setId(IDUtil.UUID());
		return hiddangerInfoMapper.insertSelective(hiddangerInfo);
	}
	
	@Override
	@Transactional
	public int updateByPrimaryKeySelective(HiddangerInfo hiddangerInfo) {
		return hiddangerInfoMapper.updateByPrimaryKeySelective(hiddangerInfo);
	}
	
	public int deleteByHiddangerId(String hiddangerId){
		return hiddangerInfoMapper.deleteByHiddangerId(hiddangerId);
	}
	
	public int deleteByPrimaryKey(String id){
		return hiddangerInfoMapper.deleteByPrimaryKey(id);
	}
	
}
