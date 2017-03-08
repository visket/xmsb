package com.cody.controller.sys;

import java.util.ArrayList;
import java.util.Date;
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
import com.cody.common.utils.DateUtils;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.finals.UnitGradeType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.project.Project;
import com.cody.entity.sys.Dictionary;
import com.cody.entity.sys.EquipStandard;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.sys.EquipStandardService;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.UnitService;

@RequestMapping("/equipStandard")
@Controller
public class EquipStandardController extends BaseController {

	@Resource
	private EquipStandardService equipStandardService;

	@Resource
	private ItemService itemService;

	@Resource
	private UnitService unitService;

	/**
	 * 跳转列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/system/equipstandard/equipstandardList";
	}

	@RequestMapping(value = "/find")
	@ResponseBody
	public PageInfo findList(EquipStandard record, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		// 执行动态参数绑定 方式1
		Map<String, Object> condition = ParamsReflect
				.setParamsByReflect(record);
		pageInfo.setCondition(condition);
		equipStandardService.find(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/findEquipChild")
	@ResponseBody
	public PageInfo findEquipChild(String gradetype, String typeId,
			String eqbaseId, String selectStatus) {

		String standarTypeId = "";

		if (UnitGradeType.DWDJ_SJ.equals(gradetype)) {
			gradetype = "tse.province";
			standarTypeId = "tse.countprovincetype";
		}else if (UnitGradeType.DWDJ_CITY.equals(gradetype)) {
			gradetype = "tse.city";
			standarTypeId = "tse.countcitytype";
		}else if (UnitGradeType.DWDJ_XJ.equals(gradetype)) {
			gradetype = "tse.county";
			standarTypeId = "tse.countcountytype";
		}

		PageInfo pageInfo = null;
		if (eqbaseId == null || "".equals(eqbaseId))
			pageInfo = equipStandardService.findEquipChildNew(gradetype,
					standarTypeId, typeId, eqbaseId);
		else
			pageInfo = equipStandardService.findEquipChild(gradetype,
					standarTypeId, typeId, eqbaseId, selectStatus);
		if(pageInfo==null){
			pageInfo=new PageInfo();
			pageInfo.setRows(new ArrayList<EquipStandard>());
		}
		return pageInfo;
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
			type = equipStandardService.deleteByPrimaryKeys(ids.split("[, ]+"));
		}
		return new Result(type, null, "", OperateType.DELETE);
	}

	@RequestMapping(value="/deleteByStatus")
	@ResponseBody
	public Result deleteByStatus(String ids){
		EquipStandard e = new EquipStandard();
		String [] id =  ids.split(",");
		e.setStatus(-1);
		e.setId(id[0]);
		int type = equipStandardService.updateByPrimaryKeySelective(e);
		return new Result(type, null, "", OperateType.DELETE);
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
		List<Item> list = itemService.findByDictionarycode("ZBLB");
		User user = (User) session.getAttribute("currentUser");
		request.setAttribute("opers", list);
		return "/system/equipstandard/equipstandardEdit";
	}

	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result saveOrUpdate(EquipStandard record, String pageType) {
		if (pageType.equals(""))
			pageType = "add";
		int type = 0;
		if (StringUtils.isEmpty(record.getId())) {
			record.setCreatetime(new Date());
			type = equipStandardService.insertSelective(record);
		} else {
			type = equipStandardService.updateByPrimaryKeySelective(record);
		}
		return new Result(type, record.getId(), "", pageType);
	}

}
