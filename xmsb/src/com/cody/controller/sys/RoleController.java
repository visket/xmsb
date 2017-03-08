package com.cody.controller.sys;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.PageInfo;
import com.cody.common.vo.Tree;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.Role;
import com.cody.service.sys.OrganizationService;
import com.cody.service.sys.RoleService;
import com.cody.vo.sys.OrganizationVo;
import com.cody.vo.sys.RoleVo;
import com.google.common.collect.Maps;

/**
 * @description：权限管理
 * @author wanhuan
 * @date 2016年12月08日
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

	private static Logger logger = LoggerFactory
			.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;

	@Resource
	private OrganizationService organizationService;

	/**
	 * 权限管理页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public String manager() {
		// return "/admin/role";
		return "/system/role/role";
	}

	/**
	 * 权限列表
	 * 
	 * @param role
	 * @param page
	 * @param rows
	 * @param sort
	 * @param order
	 * @return
	 */
	@RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
	@ResponseBody
	public PageInfo dataGrid(Role role, Integer page, Integer rows,
			String sort, String order) {
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = Maps.newHashMap();
		pageInfo.setCondition(condition);
		roleService.findDataGrid(pageInfo);
		return pageInfo;
	}

	/**
	 * 权限树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> tree() {
		return roleService.findTree();
	}

	/**
	 * 添加权限页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/addPage", method = RequestMethod.GET)
	public String addPage() {
		return "/system/role/roleEdit";
	}

	/**
	 * 添加权限
	 * 
	 * @param role
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/add", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public Result add(Role role) { Result result = new
	 * Result(); try { roleService.addRole(role); result.setSuccess(true);
	 * result.setMsg("添加成功！"); return result; } catch (RuntimeException e) {
	 * logger.error("添加角色失败：{}", e); result.setMsg(e.getMessage()); return
	 * result; } }
	 */

	/**
	 * 删除权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			List<Organization> list = organizationService.findByRoleId(id);
			if (list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					list.get(i).setRoleid(null);
					organizationService.updateOrganization(list.get(i));
				}
			}
			roleService.deleteRoleById(id);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			logger.error("删除角色失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 编辑权限页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id, Model model) {
		Role role = roleService.findRoleById(id);
		request.setAttribute("role", role);
		List<Organization> list = organizationService.findByRoleId(id);
		String temp = "";
		for (int i = 0; i < list.size(); i++) {
			temp += list.get(i).getId() + ",";
		}
		if (temp.length() > 0) {
			String oids = temp.substring(0, temp.length() - 1);
			request.setAttribute("oids", oids);
		}
		return "/system/role/roleEdit";
	}

	/**
	 * 编辑权限
	 * 
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(RoleVo role) {

		Result result = new Result();
		if (role.getId() == null) {
			try {
				roleService.addRole(role);
				result.setSuccess(true);
				result.setMsg("添加成功！");
				organizationService.updateToRoleId(role);
				return result;
			} catch (RuntimeException e) {
				logger.error("添加角色失败：{}", e);
				result.setMsg(e.getMessage());
				return result;
			}
		} else {
			try {
				List<Organization> list = organizationService.findByRoleId(role
						.getId());
				String pids=role.getPid();
				roleService.updateRole(role);
				result.setSuccess(true);
				result.setMsg("编辑成功！");
				organizationService.updateToRoleId(role);
				return result;
			} catch (RuntimeException e) {
				logger.error("编辑角色失败：{}", e);
				result.setMsg(e.getMessage());
				return result;
			}
		}

	}

	/**
	 * 授权页面
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(HttpServletRequest request, Long id, Model model) {
		// model.addAttribute("id", id);
		return "/system/role/roleGrant";
		// return "/admin/roleGrant";
	}

	/**
	 * 授权页面页面根据角色查询资源
	 * 
	 * @param request
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/findResourceIdListByRoleId")
	@ResponseBody
	public Result findResourceByRoleId(HttpServletRequest request, Long id,
			Model model) {
		Result result = new Result();
		try {
			List<Long> resources = roleService.findResourceIdListByRoleId(id);
			result.setSuccess(true);
			result.setObj(resources);
			return result;
		} catch (RuntimeException e) {
			logger.error("角色查询资源失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

	/**
	 * 授权
	 * 
	 * @param id
	 * @param resourceIds
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Result grant(Long id, String resourceIds) {
		Result result = new Result();

		try {
			roleService.updateRoleResource(Long.valueOf(id), resourceIds);
			result.setMsg("授权成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			logger.error("授权成功失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

}
