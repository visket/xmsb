package com.cody.service.equip.impl;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.equip.EquipChild;
import com.cody.entity.project.Project;
import com.cody.mapper.equip.EquipChildMapper;
import com.cody.service.equip.EquipChildService;

@Service
public class EquipChildServiceImpl implements EquipChildService {

	
	@Resource 
	private EquipChildMapper equipChildMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = equipChildMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(equipChildMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Project>());
		}
	}
	
	@Override
	public int insertSelective(EquipChild equipChild) {
		equipChild.setId(IDUtil.UUID());
		return equipChildMapper.insertSelective(equipChild);
	}
	
	@Override
	public int updateByPrimaryKeySelective(EquipChild equipChild) {
		return equipChildMapper.updateByPrimaryKeySelective(equipChild);
	}
	
	public int deleteByEqbaseId(String id){
		return equipChildMapper.deleteByEqbaseId(id);
	}
	
}
