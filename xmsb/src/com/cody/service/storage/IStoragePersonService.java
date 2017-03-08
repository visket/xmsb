package com.cody.service.storage;

import com.cody.common.utils.PageInfo;
import com.cody.entity.storage.StoragePerson;
import com.cody.vo.storage.StorageScienceVo;


/**
 * 项目入库 -验收人员名单  service
 * @author around
 * @date 2017-3-6
 */
public interface IStoragePersonService {

	/**
	 * 查询列表
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	/**
	 * 添加
	 * 
	 * @param person
	 * @return
	 */
	int insertSelective(StoragePerson person);
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 修改
	 * @param person
	 * @return
	 */
	int updateByPrimaryKeySelective(StoragePerson person);
	
	/**
	 * 查询总记录条数
	 * @return
	 */
	int findPageCountByCondition(PageInfo pageInfo);
	
	
	StorageScienceVo selectByPrimaryKeyByVo(String id);
	
	
	
}
