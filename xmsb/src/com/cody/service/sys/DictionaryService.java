package com.cody.service.sys;


import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Dictionary;

/***
 * 
 * @author yaoxia
 * @data 2016-12-14
 * 
 */
public interface DictionaryService {

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insert(Dictionary record);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Dictionary record);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Dictionary selectByPrimaryKey(String id);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Dictionary record);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Dictionary record);

	/**
	 * 字典列表
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);
	
	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByPrimaryKeys(String[] ids);

}
