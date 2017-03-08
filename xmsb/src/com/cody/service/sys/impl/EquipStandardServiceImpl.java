package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Dictionary;
import com.cody.entity.sys.EquipStandard;
import com.cody.mapper.sys.EquipStandardMapper;
import com.cody.service.sys.EquipStandardService;
import com.google.common.collect.Maps;
@Service
public class EquipStandardServiceImpl implements EquipStandardService{

	@Resource
	private EquipStandardMapper equipStandardMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return equipStandardMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(EquipStandard record) {
		return equipStandardMapper.insert(record);
	}

	@Override
	public int insertSelective(EquipStandard record) {
		record.setId(IDUtil.UUID());
		record.setStatus(0);
		return equipStandardMapper.insertSelective(record);
	}

	@Override
	public EquipStandard selectByPrimaryKey(String id) {
		return equipStandardMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(EquipStandard record) {
		return equipStandardMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(EquipStandard record) {
		return equipStandardMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = equipStandardMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(equipStandardMapper.findPageByCondition(pageInfo));
		}else{
			pageInfo.setRows(new ArrayList<EquipStandard>());
		}
	}
	
	@Override
	public PageInfo findEquipChild(String unitType,String standarTypeId,String typeId,String eqbaseId, String selectStatus) {
		PageInfo pageInfo=null;
		List<EquipStandard> es = equipStandardMapper.findEquipChild(unitType,standarTypeId,typeId,eqbaseId,selectStatus);
		if(es.size()>0){
			pageInfo = new PageInfo();
			pageInfo.setRows(es);
		}
		return pageInfo;
	}
	
	@Override
	public PageInfo findEquipChildNew(String unitType,String standarTypeId,String typeId,String eqbaseId) {
		PageInfo pageInfo=null;
		List<EquipStandard> es = equipStandardMapper.findEquipChildNew(unitType,standarTypeId,typeId,eqbaseId);
		if(es.size()>0){
			pageInfo = new PageInfo();
			pageInfo.setRows(es);
		}
		return pageInfo;
	}
	
	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return equipStandardMapper.deleteByPrimaryKeys(ids);
	}

	
//通用查询方法	
/*	public PageInfo findStandardCondition(String typeId){
		
		CustomDatabaseHandle cdh = new CustomDatabaseHandle();
		cdh.setTablename("t_sys_equipstandard");
		Map<String, Object> condition = Maps.newHashMap(); 
		condition.put("typecode=", typeId);
		cdh.setCondition(condition);
		
		PageInfo pageInfo=null;
		List<EquipStandard> es  = equipStandardMapper.findStandardCondition(cdh);
		if(es.size()>0){
			pageInfo = new PageInfo();
			pageInfo.setRows(es);
		}
		return pageInfo;
	}

	}*/

}
