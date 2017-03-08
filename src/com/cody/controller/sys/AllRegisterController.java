package com.cody.controller.sys;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.DateUtils;
import com.cody.common.utils.LoginUtil;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.SysArea;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.sys.ItemService;
import com.cody.service.sys.OrganizationService;
import com.cody.service.sys.SysAreaService;
import com.cody.service.sys.UnitService;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UserVo;

@Controller
@RequestMapping("/register")
public class AllRegisterController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(AllRegisterController.class);

	@Resource
	private UserService userService;

	@Resource
	private UnitService unitService;

	@Resource
	private ItemService itemService;

	@Resource
	private SysAreaService sysAreaService;

	@Resource
	private OrganizationService organizationService;

	/**
	 * 添加用户
	 * 
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value = "/addUserAndUnit", method = RequestMethod.POST)
	@ResponseBody
	public Result add(UserVo userVo, Unit record, String username,
			String unitphone, String unittelephone, Integer userorganizationId) {
		Result result = new Result();
		User u = userService.findUserByLoginName(userVo.getLoginname());
		if (u != null) {
			result.setMsg("用户名已存在!");
			return result;
		}
		try {
			return insertUnit(userVo, record, username, unitphone,
					unittelephone, userorganizationId);
		} catch (RuntimeException e) {
			// LOGGER.error("添加用户失败：{}", e);
			// result.setMsg(e.getMessage());
			return result;
		}

	}

	public Result insertUnit(UserVo userVo, Unit record, String username,
			String unitphone, String unittelephone, Integer userorganizationId) {
		Result result = new Result();
		record.setTelephone(unittelephone); // 联系电话
		record.setPhone(unitphone);// 电话
		record.setCreatetime(new Date());// 创建时间
		Organization o = organizationService.findOrganizationById(Long
				.valueOf(userorganizationId));// 获取组织ID
		record.setType("DWLB_QY");// 注册类别 企业
		SysArea area = sysAreaService.findById(o.getAreaId());// 通过组织获取区域ID
		record.setGradetype(area.getGradetype());// 区域等级
		record.setSysareaId(o.getAreaId());// 区域ID
		record.setStatus(0);// 状态 0
		record.setHigherLevelIdentifier(unitService.selectByOrganizationId(
				Integer.parseInt("" + o.getId())).getId());// 放入上级ID
		unitService.insertSelective(record);
		userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));// 用户放入密码
		userVo.setName(record.getUnitLinkman());// 放入联系人
		userVo.setUnitId(record.getId());// 放入单位ID
		userVo.setPhone(unitphone);// 放入联系电话
		userVo.setNewusertype(record.getType());//放入用户类型 "企业"
		userVo.setTradetype(record.getTradeType());//放入行业类型 "煤矿..."
		// 到这里了,下面的代码为测试
		userVo.setCreatedate(new Date());//用户创建时间
		userVo.setStatus(1);//用户状态 1 可用
		userVo.setRoleIds("15");//用户权限 "企业权限"
		userVo.setOrganizationId(userorganizationId);//"组织ID"
		userVo.setUsertype(0);//用户类型 ""
		Long id = userService.addUser(userVo);
		record.setCreatorId(id);// 创建人 
		unitService.updateByPrimaryKeySelective(record);
		result.setSuccess(true);
		result.setMsg("添加成功");
		LoginUtil.login(userVo.getLoginname(), userVo.getPassword());
		return result;
	}
}
