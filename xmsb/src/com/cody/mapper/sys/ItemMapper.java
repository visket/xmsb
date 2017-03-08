package com.cody.mapper.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Dictionary;
import com.cody.entity.sys.Item;
import com.cody.mapper.BaseMapper;

public interface ItemMapper  extends BaseMapper{
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
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<Project>
	 */
	List<Item> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	Integer findPageCountByCondition(PageInfo pageInfo);

	/***
	 * 通过父字典 代号查询所有子字典
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
	 * @return
	 */
	List<Item> findToGradetype();

}