package com.cody.controller.sys;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cody.common.core.Result;
import com.cody.common.vo.Tree;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Item;
import com.cody.entity.sys.Organization;
import com.cody.service.sys.ItemService;
import com.cody.entity.sys.Unit;
import com.cody.service.sys.OrganizationService;
import com.cody.service.sys.SysAreaService;
import com.cody.service.sys.UnitService;

/**
 * @description：部门管理
 * @author：wanhuan
 * @date：2016/12/08
 */
@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

	private static Logger LOGGER = LoggerFactory
			.getLogger(OrganizationController.class);

	@Autowired
	private OrganizationService organizationService;

	@Resource
	private ItemService itemService;

	@Resource
	private UnitService unitService;

	/**
	 * 省级部门树
	 */
	@RequestMapping(value = "/treeProvince", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> treeProvince() {
		Item item = itemService.findByCode("DWLB_SJBM");
		List<Tree> trees = organizationService.findTreeProvince(item.getId());
		return trees;
	}

	@Resource
	private SysAreaService sysAreaService;

	/**
	 * 部门管理主页
	 * 
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "/system/organization/organization";
	}

	/**
	 * 部门资源树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/tree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> tree() {
		List<Tree> trees = organizationService.findTree();
		return trees;
	}

	/**
	 * 注册放行资源树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/anonTree", method = RequestMethod.POST)
	@ResponseBody
	public List<Tree> anonTree(String type) {
		List<Tree> trees = organizationService.findAnonTree(type);
		return trees;
	}

	/**
	 * 部门列表
	 * 
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Organization> treeGrid() {
		List<Organization> treeGrid = organizationService.findTreeGrid();
		return treeGrid;
	}

	/**
	 * 添加部门页
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/addPage") public String addPage() { return
	 * "/admin/organizationAdd"; }
	 */

	/**
	 * 添加部门
	 * 
	 * @param organization
	 * @return
	 */
	/*
	 * @RequestMapping("/add")
	 * 
	 * @ResponseBody public Result add(Organization organization) { Result
	 * result = new Result(); try {
	 * organizationService.addOrganization(organization);
	 * result.setSuccess(true); result.setMsg("添加成功！"); return result; } catch
	 * (RuntimeException e) { LOGGER.info("添加部门失败：{}", e);
	 * result.setMsg(e.getMessage()); return result; } }
	 */

	/**
	 * 编辑部门页
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, Long id) {
		if (id != null) {
			Organization organization = organizationService
					.findOrganizationById(id);
			request.setAttribute("organization", organization);
		}
		return "/system/organization/organizationEdit";
	}

	/**
	 * 编辑部门
	 * 
	 * @param organization
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Result edit(Organization organization, String linkmanname,
			String phone) {

		Result result = new Result();
		byte b = 1;
		if (organization.getId() == null) {
			try {

				organization.setStatus(b);
				organizationService.addOrganization(organization);
				
				if (organization.getType().equals("DWLB_AJJ")
						|| organization.getType().equals(
								"1504ff35-4c8c-4299-9d7c-16300871c619")) {
					Unit u = new Unit();
					if (unitService.selectByOrganizationId(Integer
							.parseInt(organization.getPid() + "")) != null) {
						u.setHigherLevelIdentifier(unitService
								.selectByOrganizationId(
										Integer.parseInt(organization.getPid()
												+ "")).getId());
					}
					u.setOrganizationId(Integer.parseInt(organization.getId()
							+ ""));
					if (phone == "" || phone.equals("") || phone == null) {
					} else {
						u.setTelephone(phone);
					}
					if (linkmanname == "" || linkmanname.equals("")
							|| linkmanname == null) {
					} else {
						u.setUnitLinkman(linkmanname);
					}
					u.setName(organization.getName());

					if (organization.getAreaId() == ""
							|| organization.getAreaId().equals("")
							|| organization.getAreaId() == null) {
					} else {
						u.setSysareaId(organization.getAreaId());
						u.setGradetype(sysAreaService.findById(
								organization.getAreaId()).getGradetype());
					}
					if (organization.getType() == ""
							|| organization.getType().equals("")
							|| organization.getType() == null) {
					} else {
						u.setType(organization.getType());
					}
					u.setCreatetime(new Date());
					unitService.insertSelective(u);
				}
				result.setSuccess(true);
				result.setMsg("添加成功！");
				return result;
			} catch (RuntimeException e) {
				LOGGER.info("添加部门失败：{}", e);
				result.setMsg(e.getMessage());
				return result;
			}
		} else {
			try {
				organizationService.updateOrganization(organization);
				if (organization.getType().equals("DWLB_AJJ")
						|| organization.getType().equals(
								"1504ff35-4c8c-4299-9d7c-16300871c619")) {
					int oid = Integer.valueOf(String.valueOf(organization
							.getId()));
					Unit u = unitService.selectByOrganizationId(oid);
					u.setOrganizationId(oid);
					if (unitService.selectByOrganizationId(Integer
							.parseInt(organization.getPid() + "")) != null) {
						u.setHigherLevelIdentifier(unitService
								.selectByOrganizationId(
										Integer.parseInt(organization.getPid()
												+ "")).getId());
					}
					// if (organization.getType().equals(
					// "9349e73d-69a4-46d8-ae4e-9893f552dd3d")) {
					// u.setType("9349e73d-69a4-46d8-ae4e-9893f552dd3d");
					// }
					if (phone == "" || phone.equals("") || phone == null) {
					} else {
						u.setTelephone(phone);
					}
					if (linkmanname == "" || linkmanname.equals("")
							|| linkmanname == null) {
					} else {
						u.setUnitLinkman(linkmanname);
					}
					u.setName(organization.getName());
					if (organization.getAreaId() == ""
							|| organization.getAreaId().equals("")
							|| organization.getAreaId() == null) {
					} else {
						u.setSysareaId(organization.getAreaId());
						u.setGradetype(sysAreaService.findById(
								organization.getAreaId()).getGradetype());
					}
					if (organization.getType() == ""
							|| organization.getType().equals("")
							|| organization.getType() == null) {
					} else {
						u.setType(organization.getType());
					}
					u.setCreatetime(new Date());
					unitService.updateByPrimaryKeySelective(u);
				}
				result.setSuccess(true);
				result.setMsg("编辑成功！");
				return result;
			} catch (RuntimeException e) {
				LOGGER.info("编辑部门失败：{}", e);
				result.setMsg(e.getMessage());
				return result;
			}
		}

	}

	/**
	 * 删除组织架构
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id) {
		Result result = new Result();
		try {
			// 逻辑删除
			organizationService.deleteOrganizationAllByPid(id);
			Unit u = new Unit();u.setOrganizationId(Integer.parseInt(""+id));
			unitService.deleteByStatus(u);
			result.setMsg("删除成功！");
			result.setSuccess(true);
			return result;
		} catch (RuntimeException e) {
			LOGGER.info("删除部门失败：{}", e);
			result.setMsg(e.getMessage());
			return result;
		}
	}

//	public void addUnit(Organization organization, String linkmanname,
//			String phone) {
//		if (organization.getType().equals("DWLB_AJJ")
//				|| organization.getType().equals(
//						"1504ff35-4c8c-4299-9d7c-16300871c619")) {//判断是否是安监局 
//			if(unitService.selectByOrganizationId(organizationId)==null&&)
//			Unit u = new Unit();
//			if (unitService.selectByOrganizationId(Integer
//					.parseInt(organization.getPid() + "")) != null) {
//				u.setHigherLevelIdentifier(unitService.selectByOrganizationId(
//						Integer.parseInt(organization.getPid() + "")).getId());
//			}
//			u.setOrganizationId(Integer.parseInt(organization.getId() + ""));
//
//			// if (organization.getType().equals(
//			// "9349e73d-69a4-46d8-ae4e-9893f552dd3d")) {
//			// u.setType("9349e73d-69a4-46d8-ae4e-9893f552dd3d");
//			// }
//			if (phone == "" || phone.equals("") || phone == null) {
//			} else {
//				u.setTelephone(phone);
//			}
//			if (linkmanname == "" || linkmanname.equals("")
//					|| linkmanname == null) {
//			} else {
//				u.setUnitLinkman(linkmanname);
//			}
//			u.setName(organization.getName());
//
//			if (organization.getAreaId() == ""
//					|| organization.getAreaId().equals("")
//					|| organization.getAreaId() == null) {
//			} else {
//				u.setSysareaId(organization.getAreaId());
//				u.setGradetype(sysAreaService
//						.findById(organization.getAreaId()).getGradetype());
//			}
//			if (organization.getType() == ""
//					|| organization.getType().equals("")
//					|| organization.getType() == null) {
//			} else {
//				u.setType(organization.getType());
//			}
//			u.setCreatetime(new Date());
//			unitService.insertSelective(u);
//		}
//	}
}