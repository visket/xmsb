package com.cody.service.valid.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.utils.object.ValidInfo;
import com.cody.mapper.valid.ValidMapper;
import com.cody.service.valid.IValidService;



/**
 * 通用验证Service对象
 * @author user
 *
 */
@Service
public class ValidServiceImpl implements IValidService {

	
	@Resource
	private ValidMapper validMapper;
	
	
	/**
	 * @return 0:true , 1:false
	 */
	public boolean findPageByCondition(ValidInfo valid) {
		// TODO Auto-generated method stub
		
		int count = validMapper.findCountByCondition(valid);
		
		return count == 0 ? true : false;
	}


	@Override
	public Integer findBaseCount(CustomDatabaseHandle cdh) {
		return validMapper.findBaseCount(cdh);
	}

}
