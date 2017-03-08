package com.cody.service.storage.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.storage.StoragePerson;
import com.cody.mapper.storage.StoragePersonMapper;
import com.cody.service.storage.IStoragePersonService;
import com.cody.vo.storage.StoragePersonVo;
import com.cody.vo.storage.StorageScienceVo;


/**
 * 项目入库-验收人员名单  Service实现
 * @author around
 * @date 2017-3-6
 */
@Service
public class StoragePersonServiceImpl implements IStoragePersonService {

	
	@Resource
	private StoragePersonMapper storage_personMapper;
	
	
	@Override
	public void find(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		Integer count = storage_personMapper.findPageCountByCondition(pageInfo);
		if(count > 0) {
			pageInfo.setRows(storage_personMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<StoragePersonVo>());
		}
	}

	@Override
	public int insertSelective(StoragePerson person) {
		return storage_personMapper.insertSelective(person);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return storage_personMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(StoragePerson science) {
		// TODO Auto-generated method stub
		return storage_personMapper.updateByPrimaryKeySelective(science);
	}

	@Override
	public int findPageCountByCondition(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return storage_personMapper.findPageCountByCondition(pageInfo);
	}

	@Override
	public StorageScienceVo selectByPrimaryKeyByVo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
