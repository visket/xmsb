package com.cody.controller.sys;

import java.util.Date;
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
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.sys.EquipStandard;
import com.cody.entity.sys.HiddangerExamine;
import com.cody.entity.unit.UnitAward;
import com.cody.service.sys.HiddangerExamineService;

@Controller
@RequestMapping("/hiddangerExamine")
public class HiddangerExamineController extends BaseController {

	@Resource
	private HiddangerExamineService hiddangerExamineService;

	/**
	 * 跳转列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/system/hiddangerstandard/hiddangerExamineList";
	}

	@RequestMapping(value = "/find")
	@ResponseBody
	public PageInfo findList(HiddangerExamine record, Integer page,
			Integer rows, String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		// 执行动态参数绑定 方式1
		Map<String, Object> condition = ParamsReflect
				.setParamsByReflect(record);
		pageInfo.setCondition(condition);
		hiddangerExamineService.find(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加修改页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addNew", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String editNew(HttpServletRequest request, HttpSession session) {
		return "/system/hiddangerstandard/hiddangerExamineEdit";
	}

	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result saveOrUpdate(HiddangerExamine record, String pageType) {
		int type = 0;
		if (StringUtils.isEmpty(record.getId())) {
			record.setCreatetime(new Date());
			record.setId(IDUtil.UUID());
			record.setStatus(0);
			type = hiddangerExamineService.insertSelective(record);
		} else {
			type = hiddangerExamineService.updateByPrimaryKeySelective(record);
		}
		return new Result(type, record.getId(), "", pageType);
	}
	
	
	/**
	 * 逻辑删除
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public Result deletes(String ids) {
		HiddangerExamine h = new HiddangerExamine();
		h.setStatus(-1);
		int type = 0;
		if (!StringUtils.isEmpty(ids)) {
			String [] id =(ids.split("[, ]+"));
			for (int i = 0; i < id.length; i++) {
				h.setId(id[i]);
				type = hiddangerExamineService.updateByPrimaryKeySelective(h);
			}
		}
		return new Result(type, null, "", OperateType.DELETE);
	}
}
