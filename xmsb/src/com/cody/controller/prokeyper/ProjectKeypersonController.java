package com.cody.controller.prokeyper;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cody.common.core.Result;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.equip.EquipChild;
import com.cody.entity.prokeyper.ProjectKeyperson;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.prokeyper.ProjectKeypersonService;
import com.cody.vo.science.ScienceBaseVo;

/**
 * 项目主要人员Controller
 * @author wanhuan
 * @data 2017-2-20
 */

@Controller
@RequestMapping("/prokeyper")
public class ProjectKeypersonController extends BaseController {
	
	private static Logger LOGGER = LoggerFactory.getLogger(ProjectKeypersonController.class);
	
	@Resource
	private ProjectKeypersonService projectKeypersonService;
	
	/**
	 * 查询项目主要人员-list
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo findList(ProjectKeyperson projectKeyperson,Integer page, Integer rows,
			String sort, String order) {
		//PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		PageInfo pageInfo = new PageInfo();
		//执行动态参数绑定   方式1
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(projectKeyperson);
		condition.put("science_id=", projectKeyperson.getScienceId());
		pageInfo.setCondition(condition);
		projectKeypersonService.find(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(ProjectKeyperson projectKeyperson,String pageType) {

	  int type = 0;
      if(projectKeyperson.getId()==null){
    	  if(projectKeyperson.getPercentile()!=null)
    		  projectKeyperson.setPercentile(projectKeyperson.getPercentile()+"%");
       	   type = projectKeypersonService.insertSelective(projectKeyperson);  
      }
      else{
    	  if(projectKeyperson.getPercentile()!=null)
    		  projectKeyperson.setPercentile(projectKeyperson.getPercentile()+"%");
    	  type = projectKeypersonService.updateByPrimaryKeySelective(projectKeyperson);  
      }
      return new Result(type, projectKeyperson.getId(), "", pageType);
		
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String id) {
		
		Result result = new Result();
	
		try{
			projectKeypersonService.deleteByPrimaryKey(id);
			result.setMsg("删除成功！");
	        result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
	        result.setMsg(e.getMessage());
	        return result;
		}
		
	}
	
}
