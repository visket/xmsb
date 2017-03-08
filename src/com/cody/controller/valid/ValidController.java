package com.cody.controller.valid;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.object.ValidInfo;
import com.cody.controller.BaseController;
import com.cody.service.valid.IValidService;


/**
 * 通用验证模块Controller
 * @deprecated：通用校验功能，传递对应参数绑定结果集
 * @author around
 * @data 2017-2-24
 */
@Controller
@RequestMapping("/valid")
public class ValidController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ValidController.class);
	
	@Resource
	private IValidService vaildService;
	
	
	@RequestMapping(value = "/base", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result validate(ValidInfo valid, String fields, String values, String titles) {
		String[] fieldArray = fields.split("[,]+");
		String[] valueArray = values.split("[,]+");
		String[] titleArray = titles.split("[,]+");
		
		StringBuffer error = new StringBuffer();
		
		
		
		//多字段校验，因为校验一次只能精确校验一个字段，加入其它的是无法精确指出问题的
		for (int i = 0; i < fieldArray.length; i++) {
			valid.setField(fieldArray[i]);
			valid.setValue(valueArray[i]);
			if(!vaildService.findPageByCondition(valid)){
				error.append("<p>" + titleArray[i]+",存在重复!</p>");
			}
		}
		
		boolean flag = error.length() > 0 ? false : true;
		
		return new Result(flag, error.toString(), "");
	}
	
	
	
}