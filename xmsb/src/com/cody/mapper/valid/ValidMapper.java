package com.cody.mapper.valid;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.object.ValidInfo;
import com.cody.mapper.BaseMapper;





public interface ValidMapper extends BaseMapper  {

	
	/**
	 * 通用校验对象
	 * @param valid
	 * @return Integer
	 */
	Integer findCountByCondition(ValidInfo valid);
	
	
	/**
	 * 全局数据通用查询校验数据是否存在
	 * @param cdh
	 * @return Integer
	 */
	Integer findBaseCount(CustomDatabaseHandle cdh);
	
}
