package com.cody.controller.sys;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
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
import com.cody.controller.BaseController;
import com.cody.entity.equip.EquipBase;
import com.cody.entity.sys.Role;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.sys.UnitService;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UserVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @description：用户管理
 * @author wanhuan
 * @date 2016年12月08日
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	@Resource
	private UnitService unitService;

	/**
	 * 用户管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		return "/system/user/user";
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
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
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

		if (userVo.getCreatedateStartStr() != null)
			condition.put("startTime",
					DateFormatUtil.toBeginDate(userVo.getCreatedateStartStr()));

		if (userVo.getCreatedateEndStr() != null)
			condition.put("endTime",
					DateFormatUtil.toEndDate(userVo.getCreatedateEndStr()));
		condition.put("newusertype", "DWLB_AJJ");
		pageInfo.setCondition(condition);
		userService.findDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 添加用户页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/system/user/userEdit";
	}

	/**
	 * 添加用户
	 * 
	 * @param userVo
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public Result add(UserVo userVo) {
		Result result = new Result();
		Unit unit = unitService.selectByOrganizationId(userVo
				.getOrganizationId());
		if (userVo.getId() == null) {
			User u = userService.findUserByLoginName(userVo.getLoginname());
			if (u != null) {
				result.setMsg("用户名已存在!");
				return result;
			}
			try {
				userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
				// 到这里了,下面的代码为测试
				userVo.setUnitId(unit.getId());// 放入企业ID
				userVo.setNewusertype("DWLB_AJJ");// 放入安监局用户类别
				userVo.setCreatedate(new Date());
				userService.addUser(userVo);
				result.setSuccess(true);
				result.setMsg("添加成功");
				return result;
			} catch (RuntimeException e) {
				LOGGER.error("添加用户失败：{}", e);
				result.setMsg(e.getMessage());
				return result;
			}
		} else {
			User user = userService.findUserByLoginName(userVo.getLoginname());
			if (user != null
					&& !user.getId().toString()
							.equals(userVo.getId().toString())) {
				return new Result(false, "用户名已存在!", null, null);
			}
			try {
				// 由于用户密码MD5加密，编辑时不提供修改密码功能
				/*
				 * if (StringUtils.isNotBlank(userVo.getPassword())) {
				 * userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword()));
				 * }else{ userVo.setPassword(null);//null不修改密码 }
				 */
				userVo.setUnitId(unit.getId());
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

		/*
		 * Result result = new Result(); User user =
		 * userService.findUserByLoginName(userVo.getLoginname()); if (user !=
		 * null && user.getId() != userVo.getId()) { result.setMsg("用户名已存在!");
		 * return result; } try { if
		 * (StringUtils.isNotBlank(userVo.getPassword())) {
		 * userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword())); }else{
		 * userVo.setPassword(null);//null不修改密码 }
		 * userService.updateUser(userVo); result.setSuccess(true);
		 * result.setMsg("修改成功！"); return result; } catch (RuntimeException e) {
		 * LOGGER.error("修改用户失败：{}", e); result.setMsg(e.getMessage()); return
		 * result; }
		 */

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
		// ids.add((long) 23);
		ids.add((long) 15);
		model.addAttribute("roleIds", ids);
		model.addAttribute("user", userVo);
		return "/system/user/userEdit";
	}

	/**
	 * 编辑用户
	 * 
	 * @param userVo
	 * @return
	 */
	/*
	 * @RequestMapping("/edit")
	 * 
	 * @ResponseBody public Result edit(UserVo userVo) { Result result = new
	 * Result(); User user =
	 * userService.findUserByLoginName(userVo.getLoginname()); if (user != null
	 * && user.getId() != userVo.getId()) { result.setMsg("用户名已存在!"); return
	 * result; } try { if (StringUtils.isNotBlank(userVo.getPassword())) {
	 * userVo.setPassword(DigestUtils.md5Hex(userVo.getPassword())); }else{
	 * userVo.setPassword(null);//null不修改密码 } userService.updateUser(userVo);
	 * result.setSuccess(true); result.setMsg("修改成功！"); return result; } catch
	 * (RuntimeException e) { LOGGER.error("修改用户失败：{}", e);
	 * result.setMsg(e.getMessage()); return result; } }
	 */

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
	 * 删除用户
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(String ids) {
		/*
		 * public Result delete(Long id) { Result result = new Result(); try {
		 * userService.deleteUserById(id); result.setMsg("删除成功！");
		 * result.setSuccess(true); return result; } catch (RuntimeException e)
		 * { LOGGER.error("删除用户失败：{}", e); result.setMsg(e.getMessage()); return
		 * result; }
		 */

		User user = null;
		Result result = new Result();
		String[] idsstr = ids.split("[, ]+");

		try {

			for (int i = 0; i < idsstr.length; i++) {

				user = userService.findUserById(Long.parseLong(idsstr[i]));
				user.setStatus(-1);
				userService.updateUser(user);

			}

			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			result.setMsg(e.getMessage());
			return result;
		}

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

	@RequestMapping(value = "/find", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public PageInfo w(String q, Integer page, Integer rows, String sort,
			String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		String userTypeId = "32bd5204-f1a4-4798-ba61-25d4d711bf74";
		condition.put("newusertype = ", userTypeId);
		pageInfo.setCondition(condition);

		pageInfo.setQ(q);

		userService.findByCombogrid(pageInfo);
		return pageInfo;
	}

	@RequestMapping(value = "/disponseExpert", method = { RequestMethod.POST,
			RequestMethod.GET })
	@ResponseBody
	public PageInfo disponseExpert(Integer page, Integer rows, String sort,
			String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);

		userService.findUserByTypeId(pageInfo);

		return pageInfo;
	}

}
