package com.cody.service.storage;

import com.cody.common.utils.PageInfo;
import com.cody.entity.storage.StorageScience;
import com.cody.vo.science.ScienceBaseVo;
import com.cody.vo.storage.StorageScienceVo;


/**
 * 科技项目入库service
 * @author around
 * @date 2017-2-27
 */
public interface IStorageScienceService {

	/**
	 * 查询列表
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	/**
	 * 添加
	 * 
	 * @param science
	 * @return
	 */
	int insertSelective(StorageScience science);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 修改
	 * @param science
	 * @return
	 */
	int updateByPrimaryKeySelective(StorageScience science);
	
	/**
	 * 查询总记录条数
	 * @return
	 */
	int findPageCountByCondition(PageInfo pageInfo);
	
	
	StorageScienceVo selectByPrimaryKeyByVo(String id);
	
	
	
}
