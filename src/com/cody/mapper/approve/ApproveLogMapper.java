package com.cody.mapper.approve;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.approve.ApproveLog;
import com.cody.mapper.BaseMapper;

public interface ApproveLogMapper extends BaseMapper {
	
	
	int deleteByPrimaryKey(String id);

    int insert(ApproveLog record);

    int insertSelective(ApproveLog record);

    ApproveLog selectByPrimaryKey(String id);
    
    ApproveLog selectByPrimaryKeyAndCustom(PageInfo pageInfo);

    int updateByPrimaryKeySelective(ApproveLog record);

    int updateByPrimaryKey(ApproveLog record);
    
    /**
     * 通用查询数据的行数
     * @return
     */
    Integer findPageCountByCondition(PageInfo pageInfo);
    
    /**
     * 通用查询指定数据
     * @param pageinfo
     * @return List<ApproveProcess>
     */
    List findPageByCondition(PageInfo pageInfo);
    
}