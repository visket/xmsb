package com.cody.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.cody.common.core.Result;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Item;
import com.cody.service.sys.ItemService;

/**
 * @data 2016-12-20
 * @author yaoxia
 * 
 */
@RequestMapping("item")
@Controller
public class ItemController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(DictionaryController.class);
	@Resource
	private ItemService itemService;

	/**
	 * 页面跳转方法
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "system/item/itemList";
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
	public PageInfo findList(Item record, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);

		// 执行动态参数绑定 方式1
		Map<String, Object> condition = ParamsReflect
				.setParamsByReflect(record);
		pageInfo.setCondition(condition);
		itemService.find(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加或者编辑子字典页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPage")
	public String editPage() {
		return "/system/item/itemEdit";
	}

	/**
	 * 编辑字典
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public Result insert(Item record, String pagetype) {
		if (record.getId() == null || record.getId() == ""
				|| record.getId().equals("")) {
			int temp = itemService.insertSelective(record);
			return new Result(temp, record.getId(), null, pagetype);
		} else {
			int temp = itemService.updateByPrimaryKeySelective(record);
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
		int temp = itemService.deleteByPrimaryKey(id);
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
			type = itemService.deleteByPrimaryKeys(ids.split("[, ]+"));
		}
		return new Result(type, null, "", OperateType.DELETE);
	}

	/**
	 * 通过父字典的dictionarcode 搜索对应子字典的值
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findByDictionarycode")
	@ResponseBody
	List<Item> findByDictionarycode(String dictionarycode) {
		return itemService.findByDictionarycode(dictionarycode);
	}

	/**
	 * 用来过滤查询 单位等级的
	 * 
	 * @return
	 */
	@RequestMapping(value="findToGradetype")
	@ResponseBody
	List<Item> findToGradetype() {
		return itemService.findToGradetype();
	}
}
