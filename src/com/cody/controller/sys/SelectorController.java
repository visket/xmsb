package com.cody.controller.sys;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.vo.SelectorVo;


/**
 * @ClassName: SelectorController
 * @Description: 下拉选择
 * @author wanhuan
 * @date 2016年12月14日 下午5:55:0
 * 
 */

@Controller
@RequestMapping("/selector")
public class SelectorController {
private static Logger LOGGER = LoggerFactory.getLogger(SelectorController.class);
	
	/**
	 * 
	 * @Title: options 
	 * @Description: 通过反射得到枚举选择项
	 * @param @param className
	 * @return List<SelectorVo>    返回类型 
	 * @throws
	 */
	@RequestMapping("/options")
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
	}
}
