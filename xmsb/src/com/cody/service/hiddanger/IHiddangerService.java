package com.cody.service.hiddanger;

import com.cody.common.utils.PageInfo;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.sys.Unit;
import com.cody.vo.hiddanger.HiddangerDeclareVo;
import com.cody.vo.project.ProjectAuditVO;

/**
 * 隐患项目服务层
 * @author wanhuan
 * @data 2017-02-17
 */

public interface IHiddangerService {

	void find(PageInfo pageInfo);
	
	/**
	 * 查询最后一条记录
	 */
	Object findLastData();
	
	int insertSelective(HiddangerDeclare hiddangerDeclare);
	
    int updateByPrimaryKeySelective(HiddangerDeclare hiddangerDeclare);
    
    HiddangerDeclareVo selectByPrimaryKeyByVo(String id);
    
    int deleteByPrimaryKey(String id);
	
}
