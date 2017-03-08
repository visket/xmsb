package com.cody.controller.personal;

import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cody.common.core.Result;
import com.cody.common.utils.DateUtils;
import com.cody.controller.BaseController;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.SysArea;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.service.sys.OrganizationService;
import com.cody.service.sys.SysAreaService;
import com.cody.service.sys.UnitService;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UnitVo;
import com.cody.vo.sys.UserVo;
import com.sun.org.apache.regexp.internal.recompile;

@RequestMapping("/personalUnit")
@Controller
public class personalUnit extends BaseController {

	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;
	@Resource
	private SysAreaService sysAreaService;

	@Resource
	private OrganizationService organizationService;

	/**
	 * 页面跳转到企业信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manager")
	public String manager() {
		return "personal/unitSearch";
	}

	/**
	 * 页面跳转到个人信息
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/manageruser")
	public String manageruser() {
		return "personal/userSearch";
	}

	/**
	 * 
	 */
	@RequestMapping("find")
	@ResponseBody
	public Unit findPsersonalUnit(Long id) {
		User user = userService.findUserById(id);
		UnitVo unit = unitService.findVoByUserId(user.getUnitId());
		unit.setUserId(id);
		unit.setUserorganizationId(user.getOrganizationId());
		unit.setLoginname(user.getLoginname());//放入 登录名 密码修改页面
		return unit;
	}

	@RequestMapping("save")
	@ResponseBody
	public Result save(UnitVo unitVo) {
		User user = new User();
		user.setOrganizationId(unitVo.getUserorganizationId());// user 放入修改的组织ID
		unitVo.setUpdatorId(unitVo.getUserId());// 放入修改人ID
		unitVo.setLastupdatetime(new Date());// 放入修改时间
		user.setId(unitVo.getUserId());// 放入要修改的USER ID
		userService.updateUser(user);
		if (unitVo.getType().equals("DWLB_AJJ")) {// 安监局情况
			Organization o = organizationService.findOrganizationById(Long
					.valueOf(unitVo.getUserorganizationId()));// 查询组织对象
			SysArea s = sysAreaService.findById(o.getAreaId());// 通过区域ID获取区域对象
			unitVo.setGradetype(s.getGradetype());// 区域等级
			unitVo.setSysareaId(s.getId());// 区域ID
			// 放入组织的上级
			unitVo.setHigherLevelIdentifier("" + o.getPid());
			unitVo.setOrganizationId(unitVo.getUserorganizationId());
			unitService.updateByPrimaryKey(unitVo);
		} else {
			Organization o = organizationService.findOrganizationById(Long
					.valueOf(unitVo.getUserorganizationId()));// 查询组织对象
			SysArea s = sysAreaService.findById(o.getAreaId());
			unitVo.setGradetype(s.getGradetype());
			unitVo.setSysareaId(s.getId());
			unitVo.setOrganizationId(null);
			unitVo.setHigherLevelIdentifier(unitService.selectByOrganizationId(
					unitVo.getUserorganizationId()).getId());
			unitService.updateByPrimaryKey(unitVo);
		}
		return null;

	}

	@RequestMapping("saveuser")
	@ResponseBody
	public Result saveuser(User user, String oldpassword, String newpassword) {
		User u = userService.findUserById(user.getId());
		if (DigestUtils.md5Hex(oldpassword).equals(u.getPassword())) {
			if (user.getPassword().equals(newpassword)) {
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				int type = userService.updateUser(user);
				return new Result(type, null, "", "edit");
			} else {
				return new Result(0, null, ",新密码与确认密码不一致,请核实.", "edit");
			}
		} else {
			return new Result(0, null, ",旧密码不对.", "edit");
		}
	}
}
