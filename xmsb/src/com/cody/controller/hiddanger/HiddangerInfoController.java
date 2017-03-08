package com.cody.controller.hiddanger;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.ProAutoCode;
import com.cody.common.utils.annotation.Comparator;
import com.cody.common.utils.finals.Compare;
import com.cody.common.utils.finals.ProjectType;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.hiddanger.HiddangerDeclare;
import com.cody.entity.hiddanger.HiddangerInfo;
import com.cody.entity.prokeyper.ProjectKeyperson;
import com.cody.service.hiddanger.IHiddangerInfoService;
import com.cody.service.prokeyper.ProjectKeypersonService;
import com.cody.vo.hiddanger.HiddangerDeclareVo;
import com.cody.vo.science.ScienceBaseVo;

/**
 * 隐患项目Controller
 * @author wanhuan
 * @data 2017-2-25
 */

@Controller
@RequestMapping("/hiddangerinfo")
public class HiddangerInfoController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(HiddangerInfoController.class);
	
	@Resource
	private IHiddangerInfoService hiddangerInfoService;
	
	/**
	 * 查询项目主要人员-list
	 * @param project
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public PageInfo findList(HiddangerInfo hiddangerInfo,Integer page, Integer rows,
			String sort, String order) {
		//PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		PageInfo pageInfo = new PageInfo();
		//执行动态参数绑定   方式1
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(hiddangerInfo);
		 /* condition.put("hiddanger_id=要与HiddangerInfo实体中的@Comparator(comparater=Compare.EQ column=?)column对应
		 * 如果是condition.put("h.hiddanger_id 实体中的@Comparator就应该是@Comparator(comparater=Compare.EQ column=h.hiddanger_id)*/		
		condition.put("hiddanger_id=", hiddangerInfo.getHiddangerId());
		pageInfo.setCondition(condition);
		hiddangerInfoService.find(pageInfo);
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
	public Result saveOrUpdate(HiddangerInfo hiddangerInfo, String pageType,String eqbaseId) {
		int type = 0;
		Date date = new Date();
		
		/*if(DataUtil.isNotNull(scienceBase.getApplytimeStr()))
			scienceBase.setApplytime(DateFormatUtil.stringToDate(scienceBase.getApplytimeStr()));
		
		scienceBase.setStatusId(ProjectType.XMZT_TBZ);
		scienceBase.setDelstatus(1);*/
		
		if(DataUtil.isNull(hiddangerInfo.getId())){
			//自动生成编码
			/*ProAutoCode pac = new ProAutoCode();
			
			if("".equals(scienceBase.getApplytime()))
				scienceBase.setApplytime(null);
			
			Object obj = scienceService.findLastData();
			scienceBase.setApplycode(pac.getNewCode("KJXM", obj,"ScienceBase",12));*/
			
			//默认值设置 
			//scienceBase.setTypeId("XMLX_KJXM");
			
			hiddangerInfo.setCreatetime(date);
			
			type = hiddangerInfoService.insertSelective(hiddangerInfo);
		}else{
			hiddangerInfo.setLastupdatetime(date);
			type = hiddangerInfoService.updateByPrimaryKeySelective(hiddangerInfo);
		}
			
		return new Result(type, hiddangerInfo.getId(), "", pageType);
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String id) {
		
		Result result = new Result();
	
		try{
			hiddangerInfoService.deleteByPrimaryKey(id);
			result.setMsg("删除成功！");
	        result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
	        result.setMsg(e.getMessage());
	        return result;
		}
		
	}
	
}
