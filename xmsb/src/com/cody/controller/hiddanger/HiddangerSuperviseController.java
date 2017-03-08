package com.cody.controller.hiddanger;

import java.util.ArrayList;
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
import com.cody.entity.hiddanger.HiddangerSupervise;
import com.cody.entity.prokeyper.ProjectKeyperson;
import com.cody.service.hiddanger.IHiddangerInfoService;
import com.cody.service.hiddanger.IHiddangerSuperviseService;
import com.cody.service.prokeyper.ProjectKeypersonService;
import com.cody.vo.hiddanger.HiddangerDeclareVo;
import com.cody.vo.science.ScienceBaseVo;

/**
 * 隐患项目Controller
 * @author wanhuan
 * @data 2017-2-25
 */

@Controller
@RequestMapping("/hiddangersupervise")
public class HiddangerSuperviseController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(HiddangerSuperviseController.class);
	
	@Resource
	private IHiddangerSuperviseService hiddangerSuperviseService;
	
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
	public PageInfo findList(HiddangerSupervise hiddangerSupervise,Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(hiddangerSupervise);	
		condition.put("hiddanger_id=", hiddangerSupervise.getHiddangerId());
		pageInfo.setCondition(condition);
		hiddangerSuperviseService.find(pageInfo);
		//pageInfo.setRows(new ArrayList<HiddangerSupervise>());//注意如这里不查询数据库一定要模拟一个list对象返回，否则前台异常
		return pageInfo;
	}
	
	/**
	 * 保存
	 * @param HiddangerSupervise
	 * @param pageType
	 * @param eqbaseId
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(HiddangerSupervise hiddangerSupervise, String pageType,String eqbaseId) {
		int type = 0;
		Date date = new Date();
		
		if(DataUtil.isNotNull(hiddangerSupervise.getListingtimestr()))
			hiddangerSupervise.setListingtime(DateFormatUtil.stringToDate(hiddangerSupervise.getListingtimestr()));
		
		if(DataUtil.isNotNull(hiddangerSupervise.getDisannultermstr()))
			hiddangerSupervise.setDisannulterm(DateFormatUtil.stringToDate(hiddangerSupervise.getDisannultermstr()));
		
		if(DataUtil.isNotNull(hiddangerSupervise.getGoverntermstr()))
			hiddangerSupervise.setGovernterm(DateFormatUtil.stringToDate(hiddangerSupervise.getGoverntermstr()));
		
		if(DataUtil.isNull(hiddangerSupervise.getId())){
			hiddangerSupervise.setCreatetime(date);			
			type = hiddangerSuperviseService.insertSelective(hiddangerSupervise);
		}else{
			hiddangerSupervise.setLastupdatetime(date);
			type = hiddangerSuperviseService.updateByPrimaryKeySelective(hiddangerSupervise);
		}
			
		return new Result(type, hiddangerSupervise.getId(), "", pageType);
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String id) {
		
		Result result = new Result();
	
		try{
			hiddangerSuperviseService.deleteByPrimaryKey(id);
			result.setMsg("删除成功！");
	        result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
	        result.setMsg(e.getMessage());
	        return result;
		}
		
	}
	
}
