package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import com.cody.common.utils.DateUtils;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Dictionary;
import com.cody.mapper.sys.DictionaryMapper;
import com.cody.service.sys.DictionaryService;

/**
 * 
 * @author yaoxia
 * @data 2016-12-14
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

	@Resource
	private DictionaryMapper dictionaryMapper;

	@Override
	public int deleteByPrimaryKey(String id) {
		return dictionaryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Dictionary record) {
		record.setId(IDUtil.UUID());
		return dictionaryMapper.insert(record);
	}

	@Override
	public int insertSelective(Dictionary record) {
		record.setId(IDUtil.UUID());
		record.setCreatetime(new Date());
		return dictionaryMapper.insertSelective(record);
	}

	@Override
	public Dictionary selectByPrimaryKey(String id) {
		return dictionaryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Dictionary record) {
		return dictionaryMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Dictionary record) {
		return dictionaryMapper.updateByPrimaryKey(record);
	}

	@Override
	public void find(PageInfo pageInfo) {
		int total = dictionaryMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if (total > 0) {
			pageInfo.setRows(dictionaryMapper.findPageByCondition(pageInfo));
		}else{
			pageInfo.setRows(new ArrayList<Dictionary>());
		}
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return dictionaryMapper.deleteByPrimaryKeys(ids);
	}

}
