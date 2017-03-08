package com.cody.service.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Item;

public interface ItemService {
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
	int insert(Item record);

	/**
	 * 添加
	 * 
	 * @param record
	 * @return
	 */
	int insertSelective(Item record);

	/**
	 * 查找
	 * 
	 * @param id
	 * @return
	 */
	Item selectByPrimaryKey(String id);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKeySelective(Item record);

	/**
	 * 修改
	 * 
	 * @param record
	 * @return
	 */
	int updateByPrimaryKey(Item record);

	/**
	 * 分页
	 * 
	 * @param pageInfo
	 */
	void find(PageInfo pageInfo);

	/**
	 * 通过父字典的 代号 找出所有的子字典项
	 * 
	 * @param dictionarycode
	 * @return
	 */
	List<Item> findByDictionarycode(String dictionarycode);

	/**
	 * 通过子字典的代号查询子字典对象
	 * 
	 * @param itemcode
	 * @return
	 */
	Item findByCode(String itemcode);

	/**
	 * 用来过滤查询 单位等级的
	 * 
	 * @return
	 */
	List<Item> findToGradetype();

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	int deleteByPrimaryKeys(String[] ids);
}
