package com.cody.service.storage.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.storage.StorageScience;
import com.cody.mapper.storage.StorageScienceMapper;
import com.cody.service.storage.IStorageScienceService;
import com.cody.vo.storage.StorageScienceVo;


/**
 * 科技项目入库Service实现
 * @author around
 * @date 2017-2-27
 */
@Service
public class StorageScienceServiceImpl implements IStorageScienceService {

	
	@Resource
	private StorageScienceMapper storage_scienceMapper;
	
	
	@Override
	public void find(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		Integer count = storage_scienceMapper.findPageCountByCondition(pageInfo);
		if(count > 0) {
			pageInfo.setRows(storage_scienceMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<StorageScienceVo>());
		}
	}

	@Override
	public int insertSelective(StorageScience science) {
		return storage_scienceMapper.insertSelective(science);
	}

	@Override
	public int deleteByPrimaryKey(String id) {
		return storage_scienceMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(StorageScience science) {
		// TODO Auto-generated method stub
		return storage_scienceMapper.updateByPrimaryKeySelective(science);
	}

	@Override
	public int findPageCountByCondition(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return storage_scienceMapper.findPageCountByCondition(pageInfo);
	}

	@Override
	public StorageScienceVo selectByPrimaryKeyByVo(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
