package com.cody.controller.expert;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.sys.User;
import com.cody.service.expert.ExpertReviewService;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.sys.UserVo;

/**
 * 专家评审记录
 * @author wanhuan
 * @data 2017-02-17
 */

@Controller
@RequestMapping("/expertreview")
public class ExpertReviewController extends BaseController{

	private static Logger LOGGER = LoggerFactory
			.getLogger(ExpertController.class);
	
	@Autowired
	private ExpertReviewService expertReviewService;
	
	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/expert/expertReviewList";
	}
	
	/**
	 * 专家评审记录管理列表
	 * 
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo dataGrid(ProjectAuditVO projectAuditVO,UserVo user, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		//执行动态参数绑定   方式1 查询具体记录数
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(projectAuditVO);
		//User user = (User)session.getAttribute("currentUser");
		//Unit unit = science_unitService.findUnitByCombobox(user.getUnitId());
		//设置数据权限参数
		//condition.put("unit.id = ",unit.getId());
		pageInfo.setCondition(condition);
		pageInfo.setUser(user);
		expertReviewService.find(pageInfo);
		pageInfo.setUser(null);
		return pageInfo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
