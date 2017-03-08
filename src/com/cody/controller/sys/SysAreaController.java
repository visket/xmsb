package com.cody.controller.sys;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.core.Result;
import com.cody.common.core.ResultCode;
import com.cody.common.utils.Status;
import com.cody.common.utils.TreeInfo;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.SysArea;
import com.cody.service.sys.SysAreaService;
import com.cody.vo.sys.AreaTreeVo;

/**
 * @ClassName: SysAreaController
 * @Description: 区域管理
 * @author wanhuan
 * @date 2016年12月16日
 */

@Controller
@RequestMapping("/area")
public class SysAreaController extends BaseController{

	private static Logger LOGGER = LoggerFactory.getLogger(SysAreaController.class);
	
	@Autowired
	private SysAreaService sysAreaService;
	
	/**
	 * 
	 * @Title: manager 管理页
	 * @Description: 
	 * @return String    返回类型 
	 * @throws
	 */
	@RequestMapping(value="/manager")
	public String manager(){
		return "/system/area/areaList";
	}
	
    @RequestMapping(value = "/allTree", method = RequestMethod.POST)
    @ResponseBody
    public List<AreaTreeVo> allTree() {
        List<AreaTreeVo> trees = sysAreaService.findTree();
        return trees;
    }
	
	@RequestMapping(value="/dataGrid")
	@ResponseBody
	public List<TreeInfo> dataGrid(){
		TreeInfo treeInfo = new TreeInfo();
		sysAreaService.findDataGrid(treeInfo);
		return treeInfo.getRows();
	}
	
	@RequestMapping(value="/editPage")
	public String editPage(ModelMap map){
		return "/system/area/areaEdit";
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public Result edit(SysArea sysArea){
		Result result = Result.newInstance();
		if(sysArea.getId().equals("")){
			  try{
				  SysArea area = sysAreaService.findByCode(sysArea.getCode());
				  if(area != null){
					  result.setCode(ResultCode.FAILURE_EXISTS);
					  result.setMsg("编码已存在");
					  return result;
				  }
				  sysArea.setCreateBy(getCurrentUserId());
				  sysArea.setCreateDate(new Date());
				  sysArea.setUpdateBy(getCurrentUserId());
				  sysArea.setUpdateDate(new Date());
				  sysArea.setStatus((byte)Status.NORMAL.getType());
				  sysAreaService.save(sysArea);
				  result.setCode(ResultCode.SUCCESS);
				  result.setMsg("添加成功");
			      return result;
			  }catch(RuntimeException e){
				  LOGGER.error("添加失败：{}", e);
		          result.setCode(ResultCode.SERVICE_EXCEPTION);
		          result.setMsg(e.getMessage());
		          return result;
			  }
		}else{
			try{
				sysArea.setUpdateBy(getCurrentUserId());
				sysArea.setUpdateDate(new Date());
				sysAreaService.update(sysArea);
				result.setCode(ResultCode.SUCCESS);
				result.setMsg("修改成功");
				return result;
			}catch(RuntimeException e){
				  LOGGER.error("修改失败：{}", e);
		          result.setCode(ResultCode.SERVICE_EXCEPTION);
		          result.setMsg(e.getMessage());
		          return result;
			  }
		}
		
	}
	
	//采用逻辑删除
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public Result del(String code){
		Result result = new Result();
	    try {
	    	//物理删除
	    	//sysAreaService.deleteAreaAllByPid(id);
	    	
	    	//逻辑删除根据编码规则来进行
	    	sysAreaService.deleteAreaAllByCode(code);
	        result.setMsg("删除成功！");
	        result.setSuccess(true);
	        return result;
	    } catch (RuntimeException e) {
	        result.setMsg(e.getMessage());
	        return result;
	    }
	}
	
	@RequestMapping(value = "/findAreaCodeAll")
	@ResponseBody
	List<SysArea> findAreaCodeAll(){
		return sysAreaService.findAreaCodeAll();
	}
	
}