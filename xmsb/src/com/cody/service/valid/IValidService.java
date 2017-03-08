package com.cody.service.valid;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.object.ValidInfo;



/**
 * 通用校验service
 * @author around
 *
 */
public interface IValidService {

	
	boolean findPageByCondition(ValidInfo valid);
	
	
	/**
	 * 全局通用查询计数或真假结果集方法，自定义参数，参考自定义封装对象
	 * @param cdh
	 * @return
	 */
	Integer findBaseCount(CustomDatabaseHandle cdh);
	
}
