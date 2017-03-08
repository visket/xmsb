package com.cody.mapper.storage;

import com.cody.entity.storage.StoragePerson;
import com.cody.mapper.BaseMapper;

public interface StoragePersonMapper extends BaseMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(StoragePerson record);

    int insertSelective(StoragePerson record);

    StoragePerson selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StoragePerson record);

    int updateByPrimaryKey(StoragePerson record);
}