/**
 * 
 */
package com.cody.controller.sys;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.core.ResultCode;
import com.cody.common.utils.PageInfo;
import com.cody.controller.BaseController;
import com.cody.entity.sys.SysApp;
import com.cody.service.sys.AppService;


/**
 * 
 * 应用管理Controller
 * 
 * DEMO-示例
 * 
 * @author jacman
 * 
 * <p>博客:http://blog.csdn.net/jacman
 * 
 * <p>Github:https://github.com/tangthis
 */
@Controller
@RequestMapping("/app")
public class AppController extends BaseController{
	private static Logger LOGGER = LoggerFactory.getLogger(AppController.class);
	
	/**
	 * 排序字段映射
	 */
	@SuppressWarnings("serial")
	private static Map<String, String> sortMap = new HashMap<String,String>(){
		{
	        put("appCode", "app_code");  
	        put("appName", "app_name");  
	    }
	};
	
	@Autowired
	private AppService appService;
	
	/**
	 * 跳转管理页面
	 * @return
	 */
	@RequestMapping(value="/manager")
	public String manager(){
		return "/admin/app";
	}
	
	/**
	 * 数据表格
	 * @param page 当前页
	 * @param rows 每页行数
	 * @param sidx 排序列
	 * @param sort asc/desc
	 * @return
	 */
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public PageInfo dataGrid(Integer page,Integer rows,String sidx,String sord,String appCode){
		sidx = sortMap.get(sidx) == null ? sidx:sortMap.get(sidx);
		PageInfo pageInfo = new PageInfo(page,rows,sidx,sord);
		pageInfo.getCondition().put("appCode", appCode);
		appService.findDataGrid(pageInfo);
		return pageInfo;
	}
	
	/**
	 * 跳转到添加页面
	 * @return
	 */
	@RequestMapping(value="/addPage")
	public String addPage(){
		return "/admin/appAdd";
	}
	
	/**
	 * 新增操作
	 * @param sysApp
	 * @return
	 */
	@RequestMapping(value="/add",method = RequestMethod.POST)
	@ResponseBody
	public Result add(SysApp sysApp){
		Result result = new Result();
        SysApp u = appService.findAppByCode(sysApp.getAppCode());
        if (u != null) {
        	result.setCode(ResultCode.FAILURE_EXISTS);
            result.setMsg("应用编码已存在!");
            return result;
        }
        try {
            sysApp.setCreateDate(new Date());
            appService.addApp(sysApp);
            result.setCode(ResultCode.SUCCESS);
            result.setMsg("添加成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("添加应用失败：{}", e);
            result.setCode(ResultCode.SERVICE_EXCEPTION);
            result.setMsg(e.getMessage());
            return result;
        }
	}
	
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/editPage")
	public String editPage(Long id,Model model){
		SysApp sysApp = appService.findAppById(id);
		model.addAttribute("app", sysApp);
		return "/admin/appEdit";
	}
	
	/**
	 * 编辑操作
	 * @param sysApp
	 * @return
	 */
	@RequestMapping(value="/edit",method = RequestMethod.POST)
	@ResponseBody
	public Result edit(SysApp sysApp){
		Result result = new Result();
        try {
            appService.updateApp(sysApp);
            result.setCode(ResultCode.SUCCESS);
            result.setMsg("编辑成功");
            return result;
        } catch (RuntimeException e) {
            LOGGER.error("编辑应用失败：{}", e);
            result.setCode(ResultCode.SERVICE_EXCEPTION);
            result.setMsg(e.getMessage());
            return result;
        }
	}
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="delete")
	@ResponseBody
	public Result delete(Long id)
	{
		Result result = Result.newInstance();
		try{
			appService.deleteAppById(id);
			result.setCode(ResultCode.SUCCESS);
			result.setMsg("删除成功");
			return result;
		}catch(RuntimeException e){
			LOGGER.error("删除失败：{}", e);
            result.setCode(ResultCode.SERVICE_EXCEPTION);
            result.setMsg(e.getMessage());
            return result;
		}
	}
	
	@RequestMapping("/appSelectPage")
	public String appSelectPage(){
		return "/admin/appSelect";
	}
}
