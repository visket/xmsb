package com.cody.controller.hiddanger;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.approve.IApproveLogService;
import com.cody.service.hiddanger.IHiddangerAuditService;
import com.cody.service.hiddanger.IHiddangerService;
import com.cody.service.sys.UnitService;
import com.cody.vo.hiddanger.HiddangerDeclareVo;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.sys.UserVo;

/**
 * 隐患项目Controller
 * @author around
 * @data 2017-3-2
 */
@Controller
@RequestMapping("/hiddangerAudit")
public class HiddangerAuditController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(HiddangerAuditController.class);

	@Resource
	private IHiddangerAuditService hiddangerAuditService;
	
	@Resource
	private IHiddangerService hiddanger_auditService;
	
	@Resource
	private UnitService hiddanger_unitService;
	
	@Resource
	private IApproveLogService hiddanger_LogService;

	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/hiddanger/hiddangerAuditList";
	}

	
	/**
	 * 项目申报
	 * @param ScienceBaseVo
	 * @return
	 */
	@RequestMapping(value = "/declare", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result projectDeclare(ProjectAuditVO project) {
		int type = 0;
		String status = "";
		
		type = hiddangerAuditService.declare(project);
		status = type == 1 ? "申报中" : status;
		
		return new Result(type, null, "", OperateType.DECLARE, status);
	}

	
	/**
	 * 审核页面-查看项目详情
	 * @param project
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/auditSearch", method = {RequestMethod.POST, RequestMethod.GET})
	public String auditSearch(ProjectAuditVO project, HttpServletRequest request,HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		
		HiddangerDeclareVo hiddanger = hiddanger_auditService.selectByPrimaryKeyByVo(project.getProjectId());
		
		Unit unit = hiddanger_unitService.findUnitByCombobox(user.getUnitId());
		Object obj2 = JSON.toJSONString(hiddanger);
		
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(project.getLogStatusId()))
			hiddangerAuditService.beforeAudit(project);
		
		request.setAttribute("unit", unit);
		request.setAttribute("audithiddanger", obj2);
		return "/hiddanger/hiddangerDataSearch";
	}
	
	
	
	/**
	 * 项目审核查询
	 * @param user
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/auditData", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo auditData(UserVo user, ProjectAuditVO project, String logStatus, String successStatus,
			Integer page, Integer rows, String sort, String order) {
		
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		
		/*UserVo thisUser = hiddanger_userService.findUserVoById(user.getId());
		
		//判断是否存在多组织
		if(thisUser.getRolesList().size() > 0) {
			Role role = thisUser.getRolesList().get(0);
			user.setRoleId(String.valueOf(role.getId()));
		} else {
			user.setRoleId("1");
		}*/
		
		//当没有状态过滤时，则插入默认过滤
		if("1".equals(logStatus) || DataUtil.isNull(logStatus))
			pageInfo.setQ("(log.status_id = '61a3bdf5-73f9-4285-b8c0-6bca56d0da6d' or log.status_id = '2239265d-bea4-4f81-b0f3-4cb7366e5558')");
		else if("2".equals(logStatus)){
			
			pageInfo.setQ("(log.status_id <> '61a3bdf5-73f9-4285-b8c0-6bca56d0da6d' and log.status_id <> '2239265d-bea4-4f81-b0f3-4cb7366e5558')");
		}
		
		
		//执行动态参数绑定   方式1
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(project);
		
		
		if(DataUtil.isNotNull(successStatus)) {
			if("1".equals(successStatus)) condition.put("log.status_id = ", "0ed99eca-9a87-40d5-aed5-dfaa481263e3");
			else condition.put("log.status_id = ", "4d5d8f12-6c6b-49f3-b27e-8d6ba2130cb0");
		}
		
		
		condition.put("1=", 1);
		pageInfo.setCondition(condition);
		pageInfo.setUser(user);
		
		hiddangerAuditService.findAuditData(pageInfo);
		pageInfo.setUser(null);
		
		return pageInfo;
	}
	
	
	/**
	 * 查询项目关联审核日志信息，并预处理审核状态信息
	 * @param project
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/searchAuditLog", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public ModelAndView searchAudit(ProjectAuditVO project) {
		ModelAndView model = new ModelAndView();
		//修改项目状态为审核中，修改当前审批日志为已受理
		//当为查看时，则返回查询页面，继续查询需要的科技项目信息，携带操作日志ID
		
		//if(ProcessType.LCZT_DSL.equals(project.getLogStatusId())) scienceService.beforeAudit(project);
		
		ApproveLog log = hiddanger_LogService.selectByPrimaryKey(project.getLogId());
		
		model.addObject("currentLog", log);
		model.setViewName("/hiddanger/hiddangerAuditProcess");
		return model;
	}
	

	/**
	 * 查询指定项目的所有审批流程
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/auditProcess", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo auditProcess(ProjectAuditVO project, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		pageInfo.setQ(project.getProjectId());
		
		hiddangerAuditService.findAuditProcessLogData(pageInfo);
		return pageInfo;
	}
	
	
	@RequestMapping(value = "/beforeAudit")
	public String beforeAudit(ProjectAuditVO project, String operateType) {
		
		ApproveLog log = hiddanger_LogService.selectByPrimaryKey(project.getLogId());
		
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(log.getStatusId()))
			hiddangerAuditService.beforeAudit(project);
		
		if("office".equals(operateType))
			return "/hiddanger/hiddangerDisponseOffice";
		else if("expert".equals(operateType))
			return "/hiddanger/hiddangerDisponseExpert";
		else
			return "/hiddanger/hiddangerAudit";
	}
	
	
	@RequestMapping(value = "/audit", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result audit(ProjectAuditVO project, String userId, String auditType, String disponseStatus) {
		int type = 0;
		
		if("1".equals(disponseStatus)) {
			PageInfo pageInfo = new PageInfo();
			Map<String, Object> condition = pageInfo.getCondition();
			condition.put("parent_id = ", project.getLogId());
			pageInfo.setCondition(condition);
			ApproveLog lastLog = hiddanger_LogService.selectByPrimaryKeyAndCustom(pageInfo);
			lastLog.setStepuserId(project.getStepUserId());
			lastLog.setStepunitId(project.getStepUnitId());
			lastLog.setSteproleId(project.getStepRoleId());
			lastLog.setLastupdatetime(new Date());
			type = hiddanger_LogService.updateByPrimaryKeySelective(lastLog);
		} else {
			type = hiddangerAuditService.audit(project, auditType);
		}
		
		/*ApproveLog lastLog = hiddanger_LogService.selectByPrimaryKey(project.getLogId());
		
		//当log状态不等于前天状态，说明已经异步，则校验真实状态，是否是待受理和已受理，只有这2个状态可以触发审核事件，其他状态是只能查看的
		if(!lastLog.getStatusId().equals(project.getLogStatusId())) {
			if(!ProcessType.LCZT_YSL.equals(lastLog.getStatusId())) {
				return new Result(false, "状态已发生改变，请关闭或刷新当前表单重试!", null);
			}
		}*/
		
		return new Result(type, null, "", OperateType.AUDIT);
	}
	
	
	@RequestMapping(value = "/disponseOffice", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result disponseOffice(ProjectAuditVO project, String userId, String auditType) {
		
		int type = hiddangerAuditService.audit(project, auditType);
		
		return new Result(type, null, "", OperateType.AUDIT);
	}
	
	
	
	
	
	
}
