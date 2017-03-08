package com.cody.controller.unit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.cody.common.core.Result;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.unit.UnitAward;
import com.cody.service.unit.UnitAwardService;
import com.google.common.collect.Maps;

@RequestMapping("/unitAward")
@Controller
public class UnitAwardController extends BaseController {

	@Resource
	private UnitAwardService unitAwardService;

	/**
	 * 跳转列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/unit/unitAward/unitAwardList";
	}

	@RequestMapping(value = "/find")
	@ResponseBody
	public PageInfo findList(UnitAward record, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);

		Map<String, Object> condition = Maps.newHashMap();

		if (record.getUnitId() != null) {
			condition.put("unitId", record.getUnitId());
		}
		if (record.getYear() != null) {
			condition.put("year", record.getYear());
		}
		if (record.getSysareaId() != null)
			condition.put("sysareaId", record.getSysareaId());
		pageInfo.setCondition(condition);
		unitAwardService.find(pageInfo);
		return pageInfo;
	}

	/**
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addNew", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String addNew(HttpServletRequest request, HttpSession session) {
		return "/unit/unitAward/unitAwardEdit";
	}

	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result saveOrUpdate(UnitAward record, String pageType) {
		int type = 0;
		if (StringUtils.isEmpty(record.getId())) {
			record.setCreatetime(new Date());
			type = unitAwardService.insertSelective(record);
		} else {
			type = unitAwardService.updateByPrimaryKeySelective(record);
		}
		return new Result(type, record.getId(), "", pageType);
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public Result deletes(String ids) {
		int type = 0;
		if (!StringUtils.isEmpty(ids)) {
			type = unitAwardService.deleteByPrimaryKeys(ids.split("[, ]+"));
		}
		return new Result(type, null, "", OperateType.DELETE);
	}

	@RequestMapping(value = "/findYear")
	@ResponseBody
	public List<Map<String, String>> findYear() {
		List<Map<String, String>> yearList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		for (int i = 10; i >= -1; i--) {
			c.setTime(new Date());
			c.add(Calendar.YEAR, -(i));
			Date y = c.getTime();
			String year = format.format(y);
			map = new HashMap<String, String>();
			map.put("id", year);
			map.put("name", year);
			yearList.add(map);
		}
		return yearList;

	}

}
