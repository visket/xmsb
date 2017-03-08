package com.cody.service.approve.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveProcess;
import com.cody.mapper.approve.ApproveProcessMapper;
import com.cody.service.approve.IApproveProcessService;


/**
 * 审批流程service
 * @author around
 * @date 2016-12-26
 */
@Service
public class ApproveProcessServiceImpl implements IApproveProcessService {

	@Resource
	private ApproveProcessMapper processMapper;
	
	
	@Override
	public int deleteByPrimaryKey(String id) {
		return processMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		// TODO Auto-generated method stub
		return processMapper.deleteByPrimaryKeys(ids);
	}

	@Override
	public int insert(ApproveProcess process) {
		// TODO Auto-generated method stub
		return processMapper.insert(process);
	}

	@Override
	public int insertSelective(ApproveProcess process) {
		// TODO Auto-generated method stub
		return processMapper.insertSelective(process);
	}

	@Override
	public ApproveProcess selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return processMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ApproveProcess process) {
		// TODO Auto-generated method stub
		return processMapper.updateByPrimaryKeySelective(process);
	}

	@Override
	public int updateByPrimaryKey(ApproveProcess process) {
		// TODO Auto-generated method stub
		return processMapper.updateByPrimaryKey(process);
	}

	@Override
	public int updateByCustoms(ApproveProcess process) {
		// TODO Auto-generated method stub
		return processMapper.updateByCustoms(process);
	}

	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = processMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(processMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<ApproveProcess>());
		}
		
	}

}
