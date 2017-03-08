package com.cody.controller.science;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.DateUtils;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.ProAutoCode;
import com.cody.common.utils.enums.CompareEnum;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.finals.UnitGradeType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.common.utils.tuple.ThreeTuple;
import com.cody.common.utils.tuple.TwoTuple;
import com.cody.controller.BaseController;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.project.Project;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Role;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.approve.IApproveLogService;
import com.cody.service.equip.EquipService;
import com.cody.service.prokeyper.ProjectKeypersonService;
import com.cody.service.science.IScienceService;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.UnitService;
import com.cody.service.sys.UserService;
import com.cody.service.valid.IValidService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.science.ScienceBaseVo;
import com.cody.vo.sys.UserVo;

/**
 * 科技项目Controller
 * @author around
 * @data 2017-2-13
 */
@SuppressWarnings("unused")
@Controller
@RequestMapping("/science")
public class ScienceController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(ScienceController.class);

	@Resource
	private IScienceService scienceService;
	
	@Resource
	private ProjectKeypersonService projectKeypersonService;
	
	@Resource
	private ItemService science_itemService;
	
	@Resource
	private UnitService science_unitService;
	
	@Resource
	private IApproveLogService science_LogService;
	
	@Resource
	private UserService science_userService;
	
	@Resource
	private IValidService science_validService;

	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/science/scienceList";
	}

	
	/**
	 * 新增页面
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/addNew", method = {RequestMethod.POST, RequestMethod.GET})
	public String addNew(HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		Unit unit = science_unitService.findUnitByCombobox(user.getUnitId());
		Item item = science_itemService.selectByPrimaryKey(unit.getGradetype());
		request.setAttribute("unit", unit);
		request.setAttribute("item", item);  
		return "/science/scienceEdit";
	}
	

	/**
	 * 查询项目信息-list
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody//反射只能反本身类而不能反射继承类所以如果这里是scienceBaseVo就只能得到scienceBaseVo中的字段无法得到其父类scienceBase
	public PageInfo findList(ScienceBase scienceBase, Integer page, Integer rows,
			String sort, String order,HttpSession session) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		//执行动态参数绑定   方式1 查询具体记录数
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(scienceBase);
		User user = (User)session.getAttribute("currentUser");
		Unit unit = science_unitService.findUnitByCombobox(user.getUnitId());
		//设置数据权限参数
		condition.put("unit.id = ",unit.getId());
		pageInfo.setCondition(condition);
		scienceService.find(pageInfo);
		return pageInfo;
	}
	
	
	/**
	 * 保存
	 * @param scienceBase
	 * @param pageType
	 * @param eqbaseId
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(ScienceBaseVo scienceBase, String pageType,String eqbaseId,String pagetypeScience) {

	 if("add".equals(pagetypeScience)){
		ScienceBase sb = new ScienceBase();
		sb.setUnitId(scienceBase.getUnitId());
		sb.setApplytimeBegin(scienceBase.getApplytimeBegin());
		sb.setApplytimeEnd(scienceBase.getApplytimeEnd());
		Result result = isApply(sb);
		if(result.isSuccess()==false)
			return result;
     }
		
		int type = 0;
		Date date = new Date();
		if(DataUtil.isNotNull(scienceBase.getApplytimeStr()))
			scienceBase.setApplytime(DateFormatUtil.stringToDate(scienceBase.getApplytimeStr()));
		
		scienceBase.setStatusId(ProjectType.XMZT_TBZ);
		scienceBase.setDelstatus(1);
		if(DataUtil.isNull(scienceBase.getId())){
			//自动生成编码
			ProAutoCode pac = new ProAutoCode();
			
			if("".equals(scienceBase.getApplytime()))
				scienceBase.setApplytime(null);
			
			Object obj = scienceService.findLastData();
			scienceBase.setApplycode(pac.getNewCode("KJXM", obj,"ScienceBase",12));
			
			//默认值设置 
			//scienceBase.setTypeId("XMLX_KJXM");
			
			scienceBase.setCreatetime(date);
			type = scienceService.insertSelective(scienceBase);
		}else{
			scienceBase.setLastupdatetime(date);
			type = scienceService.updateByPrimaryKeySelective(scienceBase);
		}
			
		return new Result(type, scienceBase.getId(), "", pageType);
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
		
		type = scienceService.declare(project);
		status = type == 1 ? "申报中" : status;
		
		return new Result(type, null, "", OperateType.DECLARE, status);
	}
	
	
	/**
	 * 审核页面-list
	 * @return
	 */
	@RequestMapping(value = "/auditFind")
	public String auditFind() {
		return "/science/scienceAuditList";
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
		
		ScienceBaseVo science = scienceService.selectByPrimaryKeyByVo(project.getProjectId());
		
		Unit unit = science_unitService.findUnitByCombobox(user.getUnitId());
		Object obj2 = JSON.toJSONString(science);
		
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(project.getLogStatusId()))
			scienceService.beforeAudit(project);
		
		request.setAttribute("unit", unit);
		request.setAttribute("auditscience", obj2);
		return "/science/scienceDataSearch";
	}
	
	
	
	/**
	 * 科技项目审核查询
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
		//if("logStarttime".equals(sort)) sort = "applytime";
		
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		
		UserVo thisUser = science_userService.findUserVoById(user.getId());
		
		//判断是否存在多组织
		if(thisUser.getRolesList().size() > 0) {
			Role role = thisUser.getRolesList().get(0);
			user.setRoleId(String.valueOf(role.getId()));
		} else {
			user.setRoleId("1");
		}
		
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
		
		scienceService.findAuditData(pageInfo);
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
		
		ApproveLog log = science_LogService.selectByPrimaryKey(project.getLogId());
		
		model.addObject("currentLog", log);
		model.setViewName("/science/scienceAuditProcess");
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
		
		scienceService.findAuditProcessLogData(pageInfo);
		return pageInfo;
	}
	
	
	@RequestMapping(value = "/beforeAudit")
	public String beforeAudit(ProjectAuditVO project, String operateType) {
		
		ApproveLog log = science_LogService.selectByPrimaryKey(project.getLogId());
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(log.getStatusId()))
			scienceService.beforeAudit(project);
		
		if("office".equals(operateType))
			return "/science/scienceDisponseOffice";
		else if("expert".equals(operateType))
			return "/science/scienceDisponseExpert";
		else
			return "/science/scienceAudit";
	}
	
	
	@RequestMapping(value = "/audit", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result audit(ProjectAuditVO project, String userId, String auditType, String disponseStatus) {
		//后台校验审核人与实际权限审批人的一致性
		/*if(!userId.equals(project.getActualApproverId())) {
			return new Result(0, null, ",您没有审批权限!", OperateType.AUDIT);
		}*/
		int type = 0;
		
		if("1".equals(disponseStatus)) {
			PageInfo pageInfo = new PageInfo();
			Map<String, Object> condition = pageInfo.getCondition();
			condition.put("parent_id = ", project.getLogId());
			pageInfo.setCondition(condition);
			ApproveLog lastLog = science_LogService.selectByPrimaryKeyAndCustom(pageInfo);
			lastLog.setStepuserId(project.getStepUserId());
			lastLog.setStepunitId(project.getStepUnitId());
			lastLog.setSteproleId(project.getStepRoleId());
			lastLog.setLastupdatetime(new Date());
			type = science_LogService.updateByPrimaryKeySelective(lastLog);
		} else {
			type = scienceService.audit(project, auditType);
		}
		
		
		return new Result(type, null, "", OperateType.AUDIT);
	}
	
	
	@RequestMapping(value = "/disponseOffice", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result disponseOffice(ProjectAuditVO project, String userId, String auditType) {
		//后台校验审核人与实际权限审批人的一致性
		/*if(!userId.equals(project.getActualApproverId())) {
			return new Result(0, null, ",您没有审批权限!", OperateType.AUDIT);
		}*/
		
		int type = scienceService.audit(project, auditType);
		
		return new Result(type, null, "", OperateType.AUDIT);
	}
	
	
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String ids) {
		ScienceBase scienceBase = null;
		Result result = new Result();
        String[] idsstr = ids.split("[, ]+");
	    try {
	        for(int i=0;i<idsstr.length;i++){
		    	//scienceBase=scienceService.selectByPrimaryKey(idsstr[i]);
		    	
		    	//先删除子表
	        	projectKeypersonService.deleteByScienceBaseId(idsstr[i]);
		    	
	        	//后删除主表
	        	scienceService.deleteByPrimaryKey(idsstr[i]);
	        	
		   }
	        result.setMsg("删除成功！");
	        result.setSuccess(true);
	        return result;
	    } catch (RuntimeException e) {
	        result.setMsg(e.getMessage());
	        return result;
	    }
	}
	
	/**
	 * 判断单位是否申报过
	 * @param request
	 * @param session
	 * @return
	 */	
	public Result isApply(ScienceBase scienceBase) {
		String msg=null;
		Result result = new Result();
		Map<String, Object> condition = ParamsReflect.setAllParamsByReflect(scienceBase);
		
		CustomDatabaseHandle cdh = new CustomDatabaseHandle();
		
		cdh.setTablename("t_science_base");
		cdh.setCondition(condition);
		
		int count = science_validService.findBaseCount(cdh);
		
		boolean flag = count == 0 ? true : false;
		
		return new Result(flag, flag ? "" : "每个单位每年仅允许申报一次科技项目!", null);
	}
	
}
