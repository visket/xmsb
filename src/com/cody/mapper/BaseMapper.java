package com.cody.mapper;

import java.util.List;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.object.ValidInfo;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.project.Project;


/**
 * Mapper基类，包含封装的标准方法
 * @author around
 * @date 2016-12-26
 */
public interface BaseMapper {
	
	
	

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
    
    
    /**
     * 查询数据对象返回结果集
     * @param customDatabase
     * @return
     */
    List findByCondition(CustomDatabaseHandle customDatabase);
    
    
    /**
     * 通用删除多行记录
     * @return
     */
    int deleteByPrimaryKeys(String[] ids);
    
    
    
    int updateByCustoms(CustomDatabaseHandle customDatabase);
    
    
    List findStandardCondition(CustomDatabaseHandle customDatabase);
    
    
    int findStandardCount(CustomDatabaseHandle customDatabase);
    
    
    /**
     * 查询当天某表创建的数据 ： 默认为 是createtime
     * @param customDatabase 对象中需绑定对应的表
     * @return count
     */
    int findToDayDataCount(CustomDatabaseHandle customDatabase);
    
    /**
     * 查询当月某表创建的数据 ： 默认为 是createtime
     * @param customDatabase 对象中需绑定对应的表
     * @return count
     */
    int findToMonthDataCount(CustomDatabaseHandle customDatabase);

    /**
     * 查询最后一条记录
     */
    Object findLastData(CustomDatabaseHandle customDatabase);
    
}
