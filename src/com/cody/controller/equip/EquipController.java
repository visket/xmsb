package com.cody.controller.equip;

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

import com.alibaba.fastjson.JSON;
import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.ProAutoCode;
import com.cody.common.utils.finals.OperateType;
import com.cody.common.utils.finals.ProcessType;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.approve.ApproveLog;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.approve.IApproveLogService;
import com.cody.service.equip.EquipChildService;
import com.cody.service.equip.EquipService;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.UnitService;
import com.cody.vo.equip.EquipBaseVo;
import com.cody.vo.project.ProjectAuditVO;
import com.cody.vo.sys.UserVo;

/**
 * 装备项目信息
 * @author wanhuan
 * @data 2017-01-03
 */
@Controller
@RequestMapping("/equip")
public class EquipController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(EquipController.class);

	@Resource
	private EquipService equipService;
	
	@Resource
	private EquipChildService equipChildService;
	
	@Resource
	private ItemService itemService;
	
	@Resource
	private UnitService unitService;
	
	@Resource
	private IApproveLogService equip_LogService;

	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/equip/equipList";
	}
	
	@RequestMapping(value = "/manager/province")
	public String managerProvince(HttpServletRequest request,HttpSession session){
		List<Item> list = itemService.findByDictionarycode("ZBLB");
		User user = (User)session.getAttribute("currentUser");
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		Item item = itemService.selectByPrimaryKey(unit.getGradetype());
		//EquipBase eb = equipService.findSj(id);
		request.setAttribute("opers", list);
		request.setAttribute("unit", unit);
		request.setAttribute("item", item);
		return "/equip/equipProvinceList";
	}
	
	//顶部查询条件，目前查询到的是全部单位，正常应该安监局单位也就是要查询type字段(待定)
	@RequestMapping(value = "/findAllUnit", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	List<Unit> findAllUnit() {
		return unitService.findAllUnit();
	}
	
	@RequestMapping(value = "/findAllGrade", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	List<Item> findAllGrade() {
		return itemService.findByDictionarycode("DWDJ");
	}
	
	@RequestMapping(value = "/addNew", method = {RequestMethod.POST, RequestMethod.GET})
	public String addNew(HttpServletRequest request,HttpSession session) {
		List<Item> list = itemService.findByDictionarycode("ZBLB");
		User user = (User)session.getAttribute("currentUser");
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		Item item = itemService.selectByPrimaryKey(unit.getGradetype());
		request.setAttribute("opers", list);
		request.setAttribute("unit", unit);
		request.setAttribute("item", item);
		return "/equip/equipEdit";
	}

	@RequestMapping(value = "/findAllStatus", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	List<Item> findAllStatus() {
		return itemService.findByDictionarycode("XMZT");
	}
	
	
	@RequestMapping(value = "/auditSearch", method = {RequestMethod.POST, RequestMethod.GET})
	public String auditSearch(ProjectAuditVO project, HttpServletRequest request,HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		
		EquipBaseVo equip = equipService.selectByPrimaryKeyByVO(project.getProjectId());
		List<Item> list = itemService.findByDictionarycode("ZBLB");
		
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		Object obj2 = JSON.toJSONString(equip);
		
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(project.getLogStatusId()))
			equipService.beforeAudit(project);
		
		request.setAttribute("opers", list);
		request.setAttribute("unit", unit);
		request.setAttribute("auditequip", obj2);
		return "/equip/equipDataSearch";
	}
	
	/**
	 * 查询省级项目
	 * 反射只能反正类而不能反射继承类所以如果这里是EquipBaseVo就只能得到EquipBaseVo中的字段无法得到其父类EquipBase
	 * @return
	 */
	@RequestMapping(value = "/findEquipProvince", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public EquipBaseVo findEquipProvince(String id, Integer page, Integer rows,
			String sort, String order,HttpSession session) {
		
		/*如果没查到数据设置unitID到EquipBaseVo中*/
		Unit unit = unitService.findUnit(id);
		EquipBaseVo ebo = equipService.findProvince(unit.getId());
		if(ebo==null){
			ebo = new EquipBaseVo();
			ebo.setUnitId(unit.getId());
			ebo.setUnitName(unit.getName());
		}
		return ebo;
		
	}
	
	/**
	 * 查询项目信息
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody//反射只能反正类而不能反射继承类所以如果这里是EquipBaseVo就只能得到EquipBaseVo中的字段无法得到其父类EquipBase
	public PageInfo findList(EquipBase equipBase, Integer page, Integer rows,
			String sort, String order,HttpSession session) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		//执行动态参数绑定   方式1 查询具体记录数
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(equipBase);
		User user = (User)session.getAttribute("currentUser");
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		//设置数据权限参数
		condition.put("unit.id =",unit.getId());
		pageInfo.setCondition(condition);
		equipService.find(pageInfo);
		return pageInfo;
	}
	
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(EquipBaseVo equipBase, String pageType,String eqbaseId) {
		int type = 0;
		Date date = new Date();
		if(DataUtil.isNotNull(equipBase.getApplytimeStr()))
			equipBase.setApplytime(DateFormatUtil.stringToDate(equipBase.getApplytimeStr()));
		
		equipBase.setStatusId(ProjectType.XMZT_TBZ);
		equipBase.setDelstatus(1);
		if(DataUtil.isNull(equipBase.getId())){
			ProAutoCode pac = new ProAutoCode();
			if("".equals(equipBase.getApplytime())) 
				equipBase.setApplytime(null);
			
			Object obj = equipService.findLastData();
			equipBase.setApplycode(pac.getNewCode("ZBXM", obj,"EquipBase",12));
			
			//默认值设置
			equipBase.setCreatetime(date);
			type = equipService.insertSelective(equipBase);
		}else{
			equipBase.setLastupdatetime(date);
			type = equipService.updateByPrimaryKeySelective(equipBase);
		}
			
		return new Result(type, equipBase.getId(), "", pageType);
	}
	
	@RequestMapping(value = "/saveOrUpdateProvince", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdateProvince(EquipBaseVo equipBase, String pageType,String eqbaseId) {
		int type = 0;
		Date date = new Date();
		if(DataUtil.isNotNull(equipBase.getApplytimeStr()))
			equipBase.setApplytime(DateFormatUtil.stringToDate(equipBase.getApplytimeStr()));
		
		equipBase.setStatusId(ProjectType.XMZT_TBZ);
		equipBase.setDelstatus(1);
		if(DataUtil.isNull(equipBase.getId())){
			ProAutoCode pac = new ProAutoCode();
			if("".equals(equipBase.getApplytime())) equipBase.setApplytime(null);
			equipBase.setApplycode(pac.getCode("ZBXM", equipService.findToMonthCount()));
			
			//默认值设置
			equipBase.setCreatetime(date);
			
			//刷新页面重新要查一下有没有重复
			Unit unit = unitService.findUnit(equipBase.getOrgId());
			EquipBaseVo ebvo = equipService.findProvince(unit.getId());
			if(ebvo==null)			
			  type = equipService.insertSelective(equipBase);
			else{
				equipBase.setId(ebvo.getId());
				equipBase.setLastupdatetime(date);
				type = equipService.updateByPrimaryKeySelective(equipBase);
			}
		}else{
			equipBase.setLastupdatetime(date);
			type = equipService.updateByPrimaryKeySelective(equipBase);
		}
			
		return new Result(type, equipBase.getId(), "", pageType);
	}

	
	
	/**
	 * 项目申报
	 * @param equipBase
	 * @return
	 */
	@RequestMapping(value = "/declare", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result projectDeclare(ProjectAuditVO project) {
		int type = 0;
		String status = "";
		
		type = equipService.declare(project);
		status = type == 1 ? "申报中" : status;
		
		return new Result(type, null, "", OperateType.DECLARE, status);
	}
	
	
	
	@RequestMapping(value = "/auditFind")
	public String auditFind() {
		return "/equip/equipAuditList";
	}
	
	
	/**
	 * 装备项目审核查询
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
		
		condition.put("1 = ", 1);
		pageInfo.setCondition(condition);
		pageInfo.setUser(user);
		
		equipService.findAuditData(pageInfo);
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
		//当为查看时，则返回查询页面，继续查询需要的装备项目信息，携带操作日志ID
		
		//if(ProcessType.LCZT_DSL.equals(project.getLogStatusId())) equipService.beforeAudit(project);
		
		ApproveLog log = equip_LogService.selectByPrimaryKey(project.getLogId());
		
		model.addObject("currentLog", log);
		model.setViewName("/equip/equipAuditProcess");
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
		PageInfo pageInfo = new PageInfo(page, rows);
		pageInfo.setQ(project.getProjectId());
		equipService.findAuditProcessLogData(pageInfo);
		return pageInfo;
	}
	
	
	@RequestMapping(value = "/beforeAudit")
	public String beforeAudit(ProjectAuditVO project) {
		//当为待受理状态，处理当前为已受理
		if(ProcessType.LCZT_DSL.equals(project.getLogStatusId()))
			equipService.beforeAudit(project);
		
		return "/equip/equipAudit";
	}
	
	
	@RequestMapping(value = "/audit", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result audit(ProjectAuditVO project, String userId, String auditType) {
		//后台校验审核人与实际权限审批人的一致性
		/*if(!userId.equals(project.getActualApproverId())) {
			return new Result(0, null, ",您没有审批权限!", OperateType.AUDIT);
		}*/
		int type = equipService.audit(project, auditType);		
		return new Result(type, null, "", OperateType.AUDIT);
	}

	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String ids) {
		
       EquipBase equipBase=null;
	   Result result = new Result();
       String[] idsstr = ids.split("[, ]+");

	    try {
	    	
	        for(int i=0;i<idsstr.length;i++){
		    	equipBase=equipService.selectByPrimaryKey(idsstr[i]);
		    	//子表物理删除
		    	int a = equipChildService.deleteByEqbaseId(idsstr[i]);
		    	
		    	if(a>0){
			    	//逻辑删除
			    	/*equipBase.setDelstatus(-1);
			    	equipService.updateByPrimaryKeySelective(equipBase);    */
		    	   
		    		//物理删除
		    		equipService.deleteByPrimaryKey(equipBase.getId());
		    	}  
		   }
	    	
	        result.setMsg("删除成功！");
	        result.setSuccess(true);
	        return result;
	    } catch (RuntimeException e) {
	        result.setMsg(e.getMessage());
	        return result;
	    }
		
	}
	
}
