package com.cody.controller.sys;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.sys.HiddangerExamine;
import com.cody.entity.sys.HiddangerGrade;
import com.cody.service.sys.HiddangerGradeService;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("/hiddangerGrade")
public class HiddangerGradeController extends BaseController {

	@Resource
	private HiddangerGradeService hiddangerGradeService;

	@RequestMapping(value = "/find")
	@ResponseBody
	public PageInfo findList(HiddangerGrade record, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		// 执行动态参数绑定 方式1
		Map<String, Object> condition = Maps.newHashMap();
		if (StringUtils.isNoneBlank(record.getExamineId())) {
			condition.put("examineId", record.getExamineId());
		}
		pageInfo.setCondition(condition);
		hiddangerGradeService.find(pageInfo);
		return pageInfo;
	}

	/**
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addNew", method = { RequestMethod.POST,
			RequestMethod.GET })
	public String addNew(HttpServletRequest request, HttpSession session) {
		return "/system/hiddangerstandard/hiddangerGradeEdit";
	}

	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result saveOrUpdate(HiddangerGrade record, String pageType) {
		int type = 0;
		if (StringUtils.isEmpty(record.getId())) {
			record.setCreatetime(new Date());
			record.setId(IDUtil.UUID());
			record.setStatus(0);
			type = hiddangerGradeService.insertSelective(record);
		} else {
			type = hiddangerGradeService.updateByPrimaryKeySelective(record);
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
		HiddangerGrade h = new HiddangerGrade();
		h.setStatus(-1);
		int type = 0;
		if (!StringUtils.isEmpty(ids)) {
			String[] id = (ids.split("[, ]+"));
			for (int i = 0; i < id.length; i++) {
				h.setId(id[i]);
				type = hiddangerGradeService.updateByPrimaryKeySelective(h);
			}
		}
		return new Result(type, null, "", OperateType.DELETE);
	}

}
