package com.cody.controller.expert;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.core.Result;
import com.cody.common.utils.DateFormatUtil;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.UserUtils;
import com.cody.common.utils.finals.OperateType;
import com.cody.controller.BaseController;
import com.cody.entity.sys.EquipStandard;
import com.cody.entity.sys.Role;
import com.cody.entity.sys.User;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UserVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @description：专家管理
 * @author wanhuan
 * @date 2016年12月08日
 */
@Controller
@RequestMapping("/expert")
public class ExpertController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(ExpertController.class);

	@Autowired
	private UserService userService;

	/**
	 * 用户管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/expert/expertList";
	}

	/**
	 * 用户管理列表
	 * 
	 * @param userVo
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(UserVo userVo, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows);
		Map<String, Object> condition = Maps.newHashMap();

		if (StringUtils.isNoneBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}

		if (userVo.getTradetype() != null) {
			condition.put("tradetype", userVo.getTradetype());
		}

		if (userVo.getCreatedateStartStr() != null)
			condition.put("startTime",
					DateFormatUtil.toBeginDate(userVo.getCreatedateStartStr()));

		if (userVo.getCreatedateEndStr() != null)
			condition.put("endTime",
					DateFormatUtil.toEndDate(userVo.getCreatedateEndStr()));

		condition.put("newusertype", "32bd5204-f1a4-4798-ba61-25d4d711bf74");
		pageInfo.setCondition(condition);
		userService.findDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加及编辑用户页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addNew", method = RequestMethod.GET)
	public String addPage() {
		return "/expert/expertEdit";
	}

	public Result add(UserVo userVo) {
		Result result = new Result();
		UserVo u = userService.findUserByLoginName(userVo.getLoginname());
		if (u != null) {
			result.setMsg("用户名已存在!");
			return result;
		}
		try {
			userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
			// 到这里了,下面的代码为测试
			userVo.setStatus(1);
			userVo.setOrganizationId(28);
			userVo.setCreatedate(new Date());
			userVo.setNewusertype("32bd5204-f1a4-4798-ba61-25d4d711bf74");
			userVo.setRoleIds("19");
			userVo.setUnitId("28");
			userService.addUser(userVo);
			result.setSuccess(true);
			result.setMsg("添加成功");
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("添加用户失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑用户页
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(Long id, Model model) {
		UserVo userVo = userService.findUserVoById(id);
		List<Role> rolesList = userVo.getRolesList();
		List<Long> ids = Lists.newArrayList();
		for (Role role : rolesList) {
			ids.add(role.getId());
		}
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "/admin/userEdit";
	}

	/**
	 * 编辑用户
	 * 
	 * @param userVo
	 * @return
	 */
	public Result edit(UserVo userVo) {
		Result result = new Result();
		UserVo user = userService.findUserByLoginName(userVo.getLoginname());
		if (user != null && user.getId() != userVo.getId()) {
			result.setMsg("用户名已存在!");
			return result;
		}
		try {
			if (userService.findUserVoById(userVo.getId()).getPassword() == userVo
					.getPassword()
					|| userService.findUserVoById(userVo.getId()).getPassword()
							.equals(userVo.getPassword()))
				userVo.setPassword(null);
			else
				userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
				// if (StringUtils.isNotBlank(userVo.getPassword())) {
				// userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
				// } else {
				// userVo.setPassword(null);// null不修改密码
				// }
				userService.updateUser(userVo);
			result.setSuccess(true);
			result.setMsg("修改成功！");
			return result;
		} catch (RuntimeException e) {
			LOGGER.error("修改用户失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 修改密码页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
	public String editPwdPage() {
		return "/admin/userEditPassword";
		// return "/admin/userEditPwd";
	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param oldPwd
	 * @param pwd
	 * @return
	 */
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public Result editUserPwd(String oldPwd, String pwd) {
		Result result = new Result();
		User sessionUser = getCurrentUser();
		if (!sessionUser.getPassword().equals(DigestUtils.md5Hex(oldPwd))) {
			result.setMsg("老密码不正确!");
			return result;
		}

		try {
			userService.updateUserPwdById(sessionUser.getId(),
					DigestUtils.md5Hex(pwd));
			result.setSuccess(true);
			result.setMsg("密码修改成功！");

			// 重新登录
			SecurityUtils.getSubject().logout();
			UserUtils.setCurrentUser(null);

			return result;
		} catch (Exception e) {
			LOGGER.error("修改密码失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 批量删除专家
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deletes")
	@ResponseBody
	public Result delete(String ids) {
		int type = 0;
		if (!StringUtils.isEmpty(ids)) {
			type = userService.deleteByPrimaryKeys(ids.split("[, ]+"));
		}
		return new Result(type, null, "", OperateType.DELETE);
	}

	@RequestMapping(value = "/combogrid/list", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public PageInfo combogrid(String q, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();

		if (StringUtils.isNotEmpty(q)) {
			condition.put("name", q);
			condition.put("id", q);
		}

		pageInfo.setCondition(condition);
		userService.findDataGrid(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/saveOrUpdate", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public Result saveOrUpdate(UserVo userVo, String pageType) {
		System.out.println(pageType);
		if (pageType.equals("add"))
			return add(userVo);
		else {
			UserVo u = userService.findUserVoById(userVo.getId());
			List<Role> rolesList = u.getRolesList();
			List<Long> ids = Lists.newArrayList();
			for (Role role : rolesList) {
				ids.add(role.getId());
			}
			userVo.setRoleIds(String.valueOf(ids.get(0)));
			return edit(userVo);
		}
	}

}
