package com.cody.service.approve.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveLog;
import com.cody.mapper.approve.ApproveLogMapper;
import com.cody.service.approve.IApproveLogService;

@Service
public class ApproveLogServiceImpl implements IApproveLogService {

	@Resource
	private ApproveLogMapper logMapper;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ApproveLog log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ApproveLog log) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ApproveLog selectByPrimaryKey(String id) {
		return logMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ApproveLog log) {
		// TODO Auto-generated method stub
		return logMapper.updateByPrimaryKeySelective(log);
	}

	@Override
	public int updateByPrimaryKey(ApproveLog log) {
		// TODO Auto-generated method stub
		return logMapper.updateByPrimaryKey(log);
	}

	@Override
	public int updateByCustoms(CustomDatabaseHandle customDatabase) {
		// TODO Auto-generated method stub
		return logMapper.updateByCustoms(customDatabase);
	}

	@Override
	public void find(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ApproveLog selectByPrimaryKeyAndCustom(PageInfo pageInfo) {
		return logMapper.selectByPrimaryKeyAndCustom(pageInfo);
	}

}
