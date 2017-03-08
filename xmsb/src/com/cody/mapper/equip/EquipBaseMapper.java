package com.cody.mapper.equip;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cody.common.utils.PageInfo;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.project.Project;
import com.cody.entity.sys.EquipStandard;
import com.cody.mapper.BaseMapper;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;

public interface EquipBaseMapper extends BaseMapper {
	
	int insertSelective(EquipBase equipBase);
	
	int updateByPrimaryKeySelective(EquipBase equipBase);
	
	//EquipBaseVo selectUnitByOrgId(String id);
	
	EquipBase selectByPrimaryKey(String id);
	
	EquipBaseVo selectByOrgIdKey(String id);
	
	EquipBaseVo selectByPrimaryKeyByVO(String id);
	
    //int insert(EquipBase record);

    //int insertSelective(EquipBase record);
    
    /**
     * 查询数据的行数
     * @return
     */
    Integer findPageCountByCondition();
    
    /**
     * 查询指定数据
     * @param pageinfo
     * @return List<Project>
     */
   // List<Project> findPageByCondition(PageInfo pageInfo);
    
	/**
	 * 查询装备项目待审核信息
	 * @param pageInfo 封装user
	 */
	List<ProjectAuditVO> findAuditData(PageInfo pageInfo);
	
	Integer findAuditDataCount(PageInfo pageInfo);
	
	List<ProjectAuditVO> findAuditProcessLogData(PageInfo pageInfo);
	
	Integer findAuditProcessLogDataCount(String projectId);
	
	int deleteByPrimaryKey(String id);
	
	/**
	 * 查询指定数据
	 * 
	 * @param pageinfo
	 * @return List<Project>
	 */
	//List<EquipBase> findPageByCondition(PageInfo pageInfo);

	/**
	 * 查询数据的行数
	 * 
	 * @return
	 */
	//Integer findPageCountByCondition(EquipBase equipBase);
	
	//List findPageByCondition(PageInfo pageInfo,@Param("gradeTypeData") String gradeType);
	
	//Integer findPageCountByCondition(PageInfo pageInfo,@Param("gradeTypeData") String gradeType);
	
}