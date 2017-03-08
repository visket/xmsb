package com.cody.service.expert.impl;

import java.util.ArrayList;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.utils.PageInfo;
import com.cody.mapper.expert.ExpertReviewMapper;
import com.cody.service.expert.ExpertReviewService;
import com.cody.vo.project.ProjectAuditVO;

/**
 * 专家评审记录服务实现
 * @author wanhuan
 * @data 2017-02-17
 */

@Service
public class ExpertReviewServiceImpl implements ExpertReviewService {

	@Resource
	private ExpertReviewMapper expertReviewMapper;
	
	@Override
	public void find(PageInfo pageInfo) {
		int total = expertReviewMapper.findPageCountByCondition(pageInfo);
		pageInfo.setTotal(total);
		if(total > 0) {
			pageInfo.setRows(expertReviewMapper.findPageByCondition(pageInfo));
		} else {
			pageInfo.setRows(new ArrayList<ProjectAuditVO>());
		}
	}

}
