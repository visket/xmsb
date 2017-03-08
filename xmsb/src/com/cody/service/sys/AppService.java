package com.cody.service.sys;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysApp;

/**
 * @description：用户管理
 * @author：wanhuan
 * @date：2016/11/18
 */
public interface AppService {

    /**
     * 应用列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

	SysApp findAppByCode(String appCode);

	void addApp(SysApp sysApp);

	SysApp findAppById(Long id);

	void updateApp(SysApp sysApp);

	void deleteAppById(Long id);


}
