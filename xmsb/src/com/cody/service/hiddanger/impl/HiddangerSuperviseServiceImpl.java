package com.cody.service.hiddanger.impl;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerSupervise;
import com.cody.entity.project.Project;
import com.cody.mapper.hiddanger.HiddangerSuperviseMapper;
import com.cody.service.hiddanger.IHiddangerSuperviseService;

@Service
public class HiddangerSuperviseServiceImpl implements IHiddangerSuperviseService {

	@Resource
	HiddangerSuperviseMapper hiddangerSuperviseMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = hiddangerSuperviseMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(hiddangerSuperviseMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Project>());
		}
	}
	
	@Override
	@Transactional
	public int insertSelective(HiddangerSupervise hiddangerSupervise) {
		hiddangerSupervise.setId(IDUtil.UUID());
		return hiddangerSuperviseMapper.insertSelective(hiddangerSupervise);
	}
	
	@Override
	@Transactional
	public int updateByPrimaryKeySelective(HiddangerSupervise hiddangerSupervise) {
		return hiddangerSuperviseMapper.updateByPrimaryKeySelective(hiddangerSupervise);
	}
	
	public int deleteByHiddangerId(String hiddangerId){
		return hiddangerSuperviseMapper.deleteByHiddangerId(hiddangerId);
	}
	
	public int deleteByPrimaryKey(String id){
		return hiddangerSuperviseMapper.deleteByPrimaryKey(id);
	}
	
}
