package com.cody.mapper.storage;

import com.cody.entity.storage.StorageScience;
import com.cody.mapper.BaseMapper;

/**
 * 科技项目入库Mapper
 * @author user
 * @date 2017-2-27
 */
public interface StorageScienceMapper extends BaseMapper {
	
    int deleteByPrimaryKey(String id);

    int insert(StorageScience record);

    int insertSelective(StorageScience record);

    StorageScience selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(StorageScience record);

    int updateByPrimaryKey(StorageScience record);
}