package com.cody.controller.project;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.cody.common.core.Result;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.enums.CompareEnum;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.common.utils.tuple.ThreeTuple;
import com.cody.common.utils.tuple.TwoTuple;
import com.cody.controller.BaseController;
import com.cody.entity.project.Project;
import com.cody.service.project.IProjectService;

/**
 * 项目基础信息
 * @author around
 * @data 2016-12-20
 */
@Controller
@RequestMapping("project")
public class ProjectController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ProjectController.class);

	@Resource
	private IProjectService projectService;

	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/project/projectList";
	}
	
	@RequestMapping(value = "/addNew", method = {RequestMethod.POST, RequestMethod.GET})
	public String addNew() {
		return "/project/projectEdit";
	}
	

	/**
	 * 查询项目信息
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo findList(Project project, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);

		//执行动态参数绑定   方式1
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(project);
		pageInfo.setCondition(condition);
		
		//TwoTuple<List<String>, List<Object>> twoTuple = ParamsReflect.setParamsByReflect(project, "");
		//pageInfo.setConditionKey(twoTuple.a);
		//pageInfo.setConditionValue(twoTuple.b);
		
		projectService.find(pageInfo);
		
		return pageInfo;
	}
	
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(Project project, String pageType) {
		int type = 0;
		if(StringUtils.isEmpty(project.getId())) {
			type = projectService.insertSelective(project);
		} else {
			type = projectService.updateByPrimaryKeySelective(project);
		}
		
		return new Result(type, project.getId(), "", pageType);
	}
	
	
	@RequestMapping(value = "/deletes", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(String ids) {
		int type = 0;
		if(!StringUtils.isEmpty(ids)) {
			type = projectService.deleteByPrimaryKeys(ids.split("[, ]+"));
		} 
		
		return new Result(type, null, "", OperateType.DELETE);
	}
	
	
	@RequestMapping(value = "/projectDeclare", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result projectDeclare(Project project) {
		int type = 0;
		String status = "";
		
		type = projectService.processDeclare(project);
		status = type == 1 ? "申报中" : status;
		
		return new Result(type, null, "", OperateType.DECLARE, status);
	}
	

/*	public static void main(String[] args) {
		System.out.println(CompareEnum.AMP.toString());
		
	}*/
	
	/**
	 * 
	 * @Title: options 
	 * @Description: 通过反射得到枚举选择项
	 * @param @param className
	 * @return List<>    返回类型 
	 * @throws
	 */
	/*@RequestMapping("/options")
	@ResponseBody
	public List<SelectorVo> options(String className){
		List<SelectorVo> list = new ArrayList<SelectorVo>();
		try {
			Class clazz = Class.forName(className);
			Method getType = clazz.getMethod("getType"); 
            Method getDescription = clazz.getMethod("getDescription"); 
            //得到enum的所有实例 
            Object[] objs = clazz.getEnumConstants();
            
            for(Object object : objs){
            	SelectorVo box = new SelectorVo();
            	box.setId(getType.invoke(object)+"");
            	box.setText(getDescription.invoke(object)+"");
            	list.add(box);
            }
		} catch (Exception e) {
			LOGGER.error("获取枚举失败：{}", e);
		}
		return list;
	}*/
	
}
