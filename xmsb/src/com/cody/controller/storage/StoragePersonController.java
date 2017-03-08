package com.cody.controller.storage;

import java.util.Date;
import java.util.Map;
import javax.annotation.Resource;
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
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.controller.BaseController;
import com.cody.entity.storage.StoragePerson;
import com.cody.entity.sys.User;
import com.cody.service.hiddanger.IHiddangerSuperviseService;
import com.cody.service.storage.IStoragePersonService;

/**
 * 项目入库-验收人员名单Controller
 * @author around
 * @data 2017-3-6
 */

@Controller
@RequestMapping("/storagePerson")
public class StoragePersonController extends BaseController {

	private static Logger LOGGER = LoggerFactory.getLogger(StoragePersonController.class);
	
	@Resource
	private IStoragePersonService storagePersonService;
	
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
	public PageInfo findList(String storageId,Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo();
		Map<String, Object> condition = pageInfo.getCondition();	
		condition.put("storage_id = ", storageId);
		pageInfo.setCondition(condition);
		storagePersonService.find(pageInfo);
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
	public Result saveOrUpdate(StoragePerson person, String pageType, HttpSession session) {
		int type = 0;
		Date date = new Date();
		User user = (User) session.getAttribute("currentUser");
		
		person.setUpdatorId(user.getId());
		person.setLastupdatetime(date);
		
		if(DataUtil.isNull(person.getId()) && "add".equals(pageType)){
			person.setCreatorId(user.getId());
			person.setCreatetime(date);			
			type = storagePersonService.insertSelective(person);
		}else{
			
			type = storagePersonService.updateByPrimaryKeySelective(person);
		}
			
		return new Result(type, person.getId(), "", pageType);
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Result delete(String id) {
		
		Result result = new Result();
	
		try{
			storagePersonService.deleteByPrimaryKey(id);
			result.setMsg("删除成功！");
	        result.setSuccess(true);
			return result;
		}catch(RuntimeException e){
	        result.setMsg(e.getMessage());
	        return result;
		}
		
	}
	
}
