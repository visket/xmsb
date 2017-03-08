package com.cody.controller.sys;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.Message;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.project.Project;
import com.cody.entity.sys.Dictionary;
import com.cody.service.sys.DictionaryService;
import com.cody.vo.sys.UserVo;
import com.google.common.collect.Maps;

/**
 * 
 * @author yaoxia
 * @data 2016-12-14
 */
@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(DictionaryController.class);

	@Resource
	private DictionaryService dictionaryService;

	/**
	 * 跳转列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/system/dictionary/dictionaryList";
	}

	/**
	 * 查询项目信息
	 * 
	 * @param record
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find")
	@ResponseBody
	public PageInfo findList(Dictionary record, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);

		// 执行动态参数绑定 方式1
		Map<String, Object> condition = ParamsReflect
				.setParamsByReflect(record);
		pageInfo.setCondition(condition);
		dictionaryService.find(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加或者编辑字典页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage", method = RequestMethod.GET)
	public String addPage() {
		return "/system/dictionary/dictionaryEdit";
	}

	/**
	 * 编辑字典
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result insert(Dictionary record, String pagetype) {
		if (record.getId() == null || record.getId() == ""
				|| record.getId().equals("")) {
			int temp = dictionaryService.insertSelective(record);
			return new Result(temp, record.getId(), null, pagetype);
		} else {
			int temp = dictionaryService.updateByPrimaryKeySelective(record);
			return new Result(temp, record.getId(), null, pagetype);
		}
	}

	/**
	 * 删除字典
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Result delete(String id) {
		int temp = dictionaryService.deleteByPrimaryKey(id);
		return new Result(temp, id, null, "del");
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
			type = dictionaryService.deleteByPrimaryKeys(ids.split("[, ]+"));
		}
		return new Result(type, null, "", OperateType.DELETE);
	}
}
