package com.cody.service.sys.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysApp;
import com.cody.mapper.sys.SysAppMapper;
import com.cody.service.sys.AppService;

/**
 * @author：wanhuan
 * @date：2016/11/18
 */

@Service
public class AppServiceImpl implements AppService {

    private static Logger LOGGER = LoggerFactory.getLogger(AppServiceImpl.class);

    @Autowired
    private SysAppMapper sysAppMapper;
	@Override
	public void findDataGrid(PageInfo pageInfo) {
		pageInfo.setRows(sysAppMapper.findAppPageCondition(pageInfo));
		pageInfo.setRecords(sysAppMapper.findAppPageCount(pageInfo));
	}
	
	@Override
	public SysApp findAppByCode(String appCode) {
		return sysAppMapper.findAppByCode(appCode);
	}
	
	@Override
	public void addApp(SysApp sysApp) {
		sysAppMapper.insert(sysApp);
	}

	@Override
	public SysApp findAppById(Long id) {
		return sysAppMapper.selectByPrimaryKey(id);
	}

	@Override
	public void updateApp(SysApp sysApp) {
		sysAppMapper.updateByPrimaryKeySelective(sysApp);
	}

	@Override
	public void deleteAppById(Long id) {
		sysAppMapper.deleteByPrimaryKey(id);
	}
}
