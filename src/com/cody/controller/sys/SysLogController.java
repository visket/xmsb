package com.cody.controller.sys;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.SysLog;
import com.cody.service.sys.LogService;
import com.google.common.collect.Maps;

/**
 * @description：日志管理
 * @author：wanhuan
 * @date：2016/12/08
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    private static Logger logger = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private LogService logService;


    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/system/log/syslog";
    }


    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(SysLog sysLog, Integer page, Integer rows) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();
        pageInfo.setCondition(condition);
        logService.findDataGrid(pageInfo);
        return pageInfo;
    }
}
