package com.cody.service.prokeyper.impl;

import java.util.ArrayList;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.project.Project;
import com.cody.entity.prokeyper.ProjectKeyperson;
import com.cody.mapper.prokeyper.ProjectKeypersonMapper;
import com.cody.service.prokeyper.ProjectKeypersonService;

@Service
public class ProjectKeypersonServiceImpl implements ProjectKeypersonService {
	
	@Resource
	ProjectKeypersonMapper projectKeypersonMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		//先查count，再具体数据,放入pageinfo
		int total = projectKeypersonMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(projectKeypersonMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<Project>());
		}
	}

	@Override
	public int insertSelective(ProjectKeyperson projectKeyperson) {
		projectKeyperson.setId(IDUtil.UUID());
		return projectKeypersonMapper.insertSelective(projectKeyperson);
	}

	@Override
	public int updateByPrimaryKeySelective(ProjectKeyperson projectKeyperson) {
		return projectKeypersonMapper.updateByPrimaryKeySelective(projectKeyperson);
	}
	
	public int deleteByPrimaryKey(String id){
		return projectKeypersonMapper.deleteByPrimaryKey(id);
	}
	
	public int deleteByScienceBaseId(String scienceId){
		return projectKeypersonMapper.deleteByScienceBaseId(scienceId);
	}

}
