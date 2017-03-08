package com.cody.controller.sys;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.utils.DataUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.sys.UnitService;
import com.cody.vo.sys.UnitVo;
import com.google.common.collect.Maps;

@RequestMapping("unit")
@Controller
public class UnitController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(UnitController.class);

	@Resource
	private UnitService unitService;

	/**
	 * 页面跳转到企业信息管理的方法
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "system/unit/unitList";
	}

	// @RequestMapping("edit")
	// @ResponseBody
	// public Result edit(Unit record ,String pagetype){
	// int temp = unitService.insertSelective(record);
	// return new Result(temp, record.getId(), null, pagetype);
	//
	// }
	@RequestMapping("find")
	@ResponseBody
	public PageInfo findList(Unit record, Integer page, Integer rows,
			String sort, String order, HttpSession session) {
		User user = (User) session.getAttribute("currentUser");
		unitService.selectByOrganizationId(user.getOrganizationId());
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = pageInfo.getCondition();
		 

		if(StringUtils.isNoneBlank(record.getName())){
			condition.put("name", record.getName());
		}
		if(StringUtils.isNoneBlank(record.getUnitLinkman())){
			condition.put("unitLinkman", record.getUnitLinkman());
		}
		if(StringUtils.isNoneBlank(record.getGradetype())){
			condition.put("gradetype",record.getGradetype());
		}
		if(record.getSysareaId()!=null){
			condition.put("sysareaId", record.getSysareaId());
		}
		
		condition.put("unitId", user.getUnitId());
		pageInfo.setCondition(condition);
		unitService.find(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/addNew")
	public String addNew() {
		return "system/unit/unitEdit";
	}

	@RequestMapping("findByUnitId")
	@ResponseBody
	public PageInfo findByUnitIdList(String q, String typeId, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		// 执行动态参数绑定 方式1
		pageInfo.setQ(q);
		
		Map<String, Object> condition = pageInfo.getCondition();
		if(DataUtil.isNotNull(typeId)) condition.put("type = ", typeId);
		if(DataUtil.isNotNull(q)) condition.put("name like ", "%" + q + "%");
		pageInfo.setCondition(condition);
		

		unitService.findByUnitId(pageInfo);
		return pageInfo;
	}

	@RequestMapping("findUnitByCombobox")
	@ResponseBody
	Unit findUnitByCombobox(String id) {
		Unit unit = unitService.findUnitByCombobox(id);
		return unit;
	}

	@RequestMapping("findAllUnitByCombobox")
	@ResponseBody
	List<Unit> findAllUnitByCombobox() {
		List<Unit> list = unitService.findAllUnitByCombobox();
		return list;
	}

	@RequestMapping("findToAward")
	@ResponseBody
	List<Unit> findToAward() {
		return unitService.findToAward();
	}

	@RequestMapping("deleteByOrganizationId")
	@ResponseBody
	int deleteByOrganizationId(Long id) {
		return unitService.deleteByOrganizationId(id);

	}

	@RequestMapping("findUnitByTypeId")
	@ResponseBody
	public PageInfo findUnitByTypeId(String q, Integer page, Integer rows) {
		PageInfo pageInfo = new PageInfo(page, rows);
		pageInfo.setQ(q);
		
		
		unitService.findUnitByTypeId(pageInfo);
		
		return pageInfo;
	}

}
