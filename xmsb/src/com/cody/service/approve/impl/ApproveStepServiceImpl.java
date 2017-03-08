package com.cody.service.approve.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveProcess;
import com.cody.entity.approve.ApproveStep;
import com.cody.mapper.approve.ApproveStepMapper;
import com.cody.service.approve.IApproveStepService;



@Service
public class ApproveStepServiceImpl implements IApproveStepService {
	
	@Resource
	private ApproveStepMapper stepMapper;

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
	public int insert(ApproveStep step) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(ApproveStep step) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ApproveProcess selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ApproveStep step) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(ApproveStep step) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByCustoms(ApproveStep step) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void find(PageInfo pageInfo) {
		// TODO Auto-generated method stub
		
	}

	
	
}
