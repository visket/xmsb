package com.cody.controller.approve;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.utils.PageInfo;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Dictionary;
import com.cody.service.approve.IApproveStepService;
import com.cody.service.sys.DictionaryService;
import com.cody.vo.sys.UserVo;
import com.google.common.collect.Maps;

/**
 * 审批流程步骤
 * @author around
 * @data 2016-12-18
 */
@Controller
@RequestMapping("approveStep")
public class ApproveStepController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ApproveStepController.class);

	@Resource
	private IApproveStepService approveStepService;

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
	 * 
	 * @param dictionary
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Dictionary dictionary, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();
		if (dictionary.getDictionarycode() != null) {
			condition.put("dictionarycode", dictionary.getDictionarycode());
		}
		if (dictionary.getDictionaryvalue() != null) {
			condition.put("dictionaryvalue", dictionary.getDictionaryvalue());
		}
		pageInfo.setCondition(condition);
		return pageInfo;
	}

	
}
