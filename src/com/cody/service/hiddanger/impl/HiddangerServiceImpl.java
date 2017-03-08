package com.cody.service.hiddanger.impl;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.mapper.hiddanger.HiddangerDeclareMapper;
import com.cody.mapper.science.ScienceBaseMapper;
import com.cody.service.hiddanger.IHiddangerService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.hiddanger.HiddangerDeclareVo;

@Service
public class HiddangerServiceImpl implements IHiddangerService {

	@Resource
	private HiddangerDeclareMapper hiddangerDeclareMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = hiddangerDeclareMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(hiddangerDeclareMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<EquipBaseVo>());
		}
	}
	
	public Object findLastData(){
		CustomDatabaseHandle customHandle = new CustomDatabaseHandle();
		customHandle.setTablename("t_hiddanger_declare");
		Object obj = hiddangerDeclareMapper.findLastData(customHandle);
		return obj;
	}
	
	@Override
	@Transactional
	public int updateByPrimaryKeySelective(HiddangerDeclare hiddangerDeclare) {
		return hiddangerDeclareMapper.updateByPrimaryKeySelective(hiddangerDeclare);
	}
	
	@Override
	@Transactional
	public int insertSelective(HiddangerDeclare hiddangerDeclare) {
		hiddangerDeclare.setId(IDUtil.UUID());
		return hiddangerDeclareMapper.insertSelective(hiddangerDeclare);
	}

	@Override
	public HiddangerDeclareVo selectByPrimaryKeyByVo(String id) {
		return hiddangerDeclareMapper.selectByPrimaryKeyVo(id);
	}
	
	public int deleteByPrimaryKey(String id){
		return hiddangerDeclareMapper.deleteByPrimaryKey(id);
	}

	
}
