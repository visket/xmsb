package com.cody.controller.equip;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.core.Result;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.equip.EquipChild;
import com.cody.entity.equip.EquipTest;
import com.cody.service.equip.EquipChildService;
import com.alibaba.fastjson.JSONArray;

/**
 * 装备项目子表信息
 * @author wanhuan
 * @data 2017-01-07
 */
@Controller
@RequestMapping("/equipchild")
public class EquipChildController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(EquipChildController.class);
	
	@Resource
	private EquipChildService equipChildService;
	
	
	/**
	 * 查询装备子表项目信息
	 * @param equipChild
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo findList(EquipChild equipChild, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		//执行动态参数绑定   方式1
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(equipChild);
		pageInfo.setCondition(condition);
		equipChildService.find(pageInfo);
		return pageInfo;
		//return null;
	}
	
    @RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(String json, String pageType) {
    	
    	int type = 0;
    	json = json.replaceAll("&quot;","'");
    	List<EquipChild> list=JSONArray.parseArray(json,EquipChild.class);
    	for (EquipChild equipChild : list) {
          if(equipChild.getId()==null) 
        	  type = equipChildService.insertSelective(equipChild);
          else
        	  type = equipChildService.updateByPrimaryKeySelective(equipChild);
		}
    	return new Result(type, "", "", pageType);
		
	}
	
}
