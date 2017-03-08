package com.cody.service.equip;

import com.cody.common.utils.PageInfo;
import com.cody.common.utils.tuple.ThreeTuple;
import com.cody.common.utils.tuple.TwoTuple;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.equip.EquipChild;
import com.cody.entity.sys.User;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;


/**
 * 项目基础信息
 * @author wanhuan
 *
 */
public interface EquipService {

	void find(PageInfo pageInfo);
	
	EquipBaseVo findProvince(String id);
	
	/**
	 * 添加
	 * 
	 * @param equipBase
	 * @return
	 */
	int insertSelective(EquipBase equipBase);
	
	/**
	 * 查询总记录条数
	 * @return
	 */
	int findPageCountByCondition(PageInfo pageInfo);
	
	/**
	 * 查询当月记录条数
	 * @return
	 */
	int findToMonthCount();
	
	
	/**
	 * 查询装备项目待审核信息
	 * @author around
	 * @param pageInfo 封装user
	 * 
	 */
	void findAuditData(PageInfo pageInfo);
	
	
	/**
	 * 装备项目申报
	 * @author around
	 * @param equipBase
	 * @return 
	 */
	int declare(ProjectAuditVO project);
	
	
	/**
	 * 装备项目审核预处理数据，对开始审核的数据
	 * @param projectId
	 * @param logId
	 * @param user
	 * @return
	 */
	int beforeAudit(ProjectAuditVO project);
	
	
	/**
	 * 装备项目审核
	 * @param equipBase
	 * @return
	 */
	int audit(ProjectAuditVO project, String auditType);
	
	
	int updateByPrimaryKeySelective(EquipBase equipBase);
	/**
	 * 查询装备项目相关的审核流程信息
	 * @param pageInfo
	 */
	void findAuditProcessLogData(PageInfo pageInfo);
	
	
	
	/**
	 * 根据ID来查询
	 * @param id
	 */
	EquipBase selectByPrimaryKey(String id);
	
	EquipBaseVo selectByPrimaryKeyByVO(String id);
	
	/**
	 * 物理删除主表
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);
	
	/**
	 * 查询最后一条记录
	 */
	Object findLastData();
	
}
