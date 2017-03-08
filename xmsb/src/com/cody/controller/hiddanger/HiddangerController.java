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

import com.cody.common.core.CustomDatabaseHandle;
import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.ProAutoCode;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.science.ScienceBase;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.hiddanger.IHiddangerInfoService;
import com.cody.service.hiddanger.IHiddangerService;
import com.cody.service.hiddanger.IHiddangerSuperviseService;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.UnitService;
import com.cody.service.valid.IValidService;
import com.cody.vo.hiddanger.HiddangerDeclareVo;
import com.cody.vo.science.ScienceBaseVo;

/**
 * 隐患项目Controller
 * @author wanhuan
 * @data 2017-2-25
 */

@Controller
@RequestMapping("/hiddanger")
public class HiddangerController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(HiddangerController.class);
	
	@Resource
	private IHiddangerService hiddangerService;
	
	@Resource
	private UnitService unitService;
	
	@Resource
	private IValidService hiddanger_validService;
	
	@Resource
	private ItemService itemService;
	
	@Resource
	private IHiddangerInfoService hiddangerInfoService;
	
	@Resource
	private IHiddangerSuperviseService hiddangerSuperviseService;
	
	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/hiddanger/hiddangerList";
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
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		Item item = itemService.selectByPrimaryKey(unit.getGradetype());
		request.setAttribute("unit", unit);
		request.setAttribute("item", item);  
		return "/hiddanger/hiddangerEdit";
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
	public PageInfo findList(HiddangerDeclare hiddangerDeclare, Integer page, Integer rows,
			String sort, String order,HttpSession session) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		//执行动态参数绑定   方式1 查询具体记录数
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(hiddangerDeclare);
		User user = (User)session.getAttribute("currentUser");
		Unit unit = unitService.findUnitByCombobox(user.getUnitId());
		//设置数据权限参数
		condition.put("unit.id = ",unit.getId());
		pageInfo.setCondition(condition);
		hiddangerService.find(pageInfo);
		return pageInfo;
	}
	
	/**
	 * 判断单位是否申报过
	 * @param request
	 * @param session
	 * @return
	 */
	public Result isApply(HiddangerDeclare hiddangerDeclare) {
		
		String msg=null;
		Result result = new Result();
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(hiddangerDeclare);
		
		CustomDatabaseHandle cdh = new CustomDatabaseHandle();
		
		cdh.setTablename("t_hiddanger_declare");
		cdh.setCondition(condition);
		
		int count = hiddanger_validService.findBaseCount(cdh);
		
        boolean flag = count == 0 ? true : false;
		
		return new Result(flag, flag ? "" : "每个单位每年仅允许申报一次隐患项目!", null);
		
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
	public Result saveOrUpdate(HiddangerDeclareVo hiddangerDeclare, String pageType,String eqbaseId,String applytimeBegin,String applytimeEnd,String pagetypeScience){
	
	 if("add".equals(pagetypeScience)){	
		HiddangerDeclare hd = new HiddangerDeclare();
		hd.setUnitId(hiddangerDeclare.getUnitId());
		hd.setApplytimeBegin(hiddangerDeclare.getApplytimeBegin());
		hd.setApplytimeEnd(hiddangerDeclare.getApplytimeEnd());
		Result result = isApply(hd);

		if(result.isSuccess()==false)
			return result;
	    }	
		
		int type = 0;
		Date date = new Date();
		if(DataUtil.isNotNull(hiddangerDeclare.getApplytimeStr()))
			hiddangerDeclare.setApplytime(DateFormatUtil.stringToDate(hiddangerDeclare.getApplytimeStr()));
		
		if(DataUtil.isNull(hiddangerDeclare.getId())){
			//自动生成编码
			ProAutoCode pac = new ProAutoCode();
			
			if("".equals(hiddangerDeclare.getApplytime()))
				hiddangerDeclare.setApplytime(null);
			
			Object obj = hiddangerService.findLastData();
			hiddangerDeclare.setApplycode(pac.getNewCode("YHXM", obj,"HiddangerDeclare",12));
			
			//默认值设置 
			//scienceBase.setTypeId("XMLX_KJXM");
			hiddangerDeclare.setStatusId("XMZT_TBZ");
			hiddangerDeclare.setCreatetime(date);
			type = hiddangerService.insertSelective(hiddangerDeclare);
		}else{
			hiddangerDeclare.setLastupdatetime(date);
			type = hiddangerService.updateByPrimaryKeySelective(hiddangerDeclare);
		}
			
		return new Result(type, hiddangerDeclare.getId(), "", pageType);
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String ids) {
		HiddangerDeclare hiddangerDeclare = null;
		Result result = new Result();
        String[] idsstr = ids.split("[, ]+");
	    try {
	        for(int i=0;i<idsstr.length;i++){
		    	//scienceBase=scienceService.selectByPrimaryKey(idsstr[i]);
		    	
		    	//先删除子表
	        	hiddangerInfoService.deleteByHiddangerId(idsstr[i]);
	        	hiddangerSuperviseService.deleteByHiddangerId(idsstr[i]);
	        	
	        	
	        	//后删除主表
	        	hiddangerService.deleteByPrimaryKey(idsstr[i]);
	        	
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
