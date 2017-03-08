package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.DateUtils;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Item;
import com.cody.mapper.sys.ItemMapper;
import com.cody.service.sys.ItemService;

@Service
public class ItemServiceImpl implements ItemService {

	@Resource
	private ItemMapper itemMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return itemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Item record) {
		return itemMapper.insert(record);
	}

	@Override
	public int insertSelective(Item record) {
		record.setId(IDUtil.UUID());
		record.setCreatetime(new Date());
		return itemMapper.insertSelective(record);
	}

	@Override
	public Item selectByPrimaryKey(String id) {
		return itemMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Item record) {
		return itemMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Item record) {
		return itemMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Item> findByDictionarycode(String dictionarycode) {
		return itemMapper.findByDictionarycode(dictionarycode);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = itemMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(itemMapper.findPageByCondition(pageInfo));
		}else{
			pageInfo.setRows(new ArrayList<Item>());
		}
	}

	@Override
	public Item findByCode(String itemcode) {
		Item i=itemMapper.findByCode(itemcode);
		return itemMapper.findByCode(itemcode);
	}

	@Override
	public List<Item> findToGradetype() {
		return itemMapper.findToGradetype();
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return itemMapper.deleteByPrimaryKeys(ids);
	}

}
