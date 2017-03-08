package com.cody.controller.storage;

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

import com.cody.common.core.Result;
import com.cody.common.utils.DataUtil;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.storage.StorageScience;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.storage.IStorageScienceService;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.UnitService;
import com.cody.vo.storage.StorageScienceVo;

/**
 * 科技项目Controller
 * @author around
 * @data 2017-2-13
 */
@Controller
@RequestMapping("/storageScience")
public class StorageScienceController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(StorageScienceController.class);

	@Resource
	private IStorageScienceService science_StorageService;
	
	@Resource
	private ItemService science_itemService;
	
	@Resource
	private UnitService science_unitService;
	

	/**
	 * init页面加载
	 */
	@RequestMapping(value = "/manager")
	public String manager() {
		return "/storage/storageScienceList";
	}

	
	/**
	 * 
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/edit", method = {RequestMethod.POST, RequestMethod.GET})
	public String edit(HttpServletRequest request, HttpSession session) {
		User user = (User)session.getAttribute("currentUser");
		Unit unit = science_unitService.findUnitByCombobox(user.getUnitId());
		Item item = science_itemService.selectByPrimaryKey(unit.getGradetype());
		request.setAttribute("unit", unit);
		request.setAttribute("item", item);  
		
		return "/storage/storageScienceEdit";
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
	@ResponseBody
	public PageInfo find(StorageScienceVo Storage, Integer page, Integer rows,
			String sort, String order,HttpSession session) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		
		User user = (User)session.getAttribute("currentUser");
		
		//执行动态参数绑定   方式1 查询具体记录数
		Map<String, Object> condition = ParamsReflect.setParamsByReflect(Storage);

		//设置数据权限参数
		//condition.put("unit.id = ",user.getUnitId());
		pageInfo.setCondition(condition);
		
		science_StorageService.find(pageInfo);
		return pageInfo;
	}
	
	
	/**
	 * 保存
	 * @param storageScience
	 * @param pageType
	 * @param eqbaseId
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result saveOrUpdate(StorageScienceVo storageScience, String pageType,String eqbaseId, HttpSession session) {
		int type = 0;
		Date date = new Date(); 
		
		if(DataUtil.isNotNull(storageScience.getAccepttimeStr())) 
			storageScience.setAccepttime(DateFormatUtil.stringToDate(storageScience.getAccepttimeStr()));
		
		User user = (User) session.getAttribute("currentUser");
		
		storageScience.setLastupdatetime(date);
		storageScience.setUpdatorId(user.getId());
		
		if(DataUtil.isNull(storageScience.getId())) {
			
			storageScience.setId(IDUtil.UUID());
			storageScience.setCreatetime(date);
			storageScience.setCreatorId(user.getId());
			
			type = science_StorageService.insertSelective(storageScience);
		} else {
			type = science_StorageService.updateByPrimaryKeySelective(storageScience);
		}
		
		
		return new Result(type, storageScience.getId(), "", pageType);
	}


	

	
}
