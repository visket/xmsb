package com.cody.service.sys;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysLog;


/**
 * @description：操作日志
 * @author：wanhuan
 * @date：2016/11/18
 */
public interface LogService {

    void insertLog(SysLog sysLog);

    void findDataGrid(PageInfo pageInfo);
}
