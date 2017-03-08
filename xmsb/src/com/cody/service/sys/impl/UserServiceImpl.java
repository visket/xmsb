package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cody.common.cache.redis.KeyRedis;
import com.cody.common.shiro.DataScope;
import com.cody.common.utils.IDUtil;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.Unit;
import com.cody.entity.sys.User;
import com.cody.entity.sys.UserRole;
import com.cody.mapper.sys.OrganizationMapper;
import com.cody.mapper.sys.SysAreaMapper;
import com.cody.mapper.sys.UnitMapper;
import com.cody.mapper.sys.UserMapper;
import com.cody.mapper.sys.UserRoleMapper;
import com.cody.service.BaseService;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UserVo;

/**
 * @author：wanhuan
 * @date：2016/11/18
 */

@Service
public class UserServiceImpl extends BaseService implements UserService {

	private static Logger LOGGER = LoggerFactory
			.getLogger(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private KeyRedis keyRedis;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private SysAreaMapper areaMapper;
	@Autowired
	private UnitMapper unitMapper;

	@Override
	public UserVo findUserByLoginName(String username) {
		return userMapper.findUserByLoginName(username);
	}

	@Override
	public User findUserById(Long id) {
		return userMapper.findUserById(id);
	}

	@Override
	public void findDataGrid(PageInfo pageInfo) {
		// 数据权限过滤
		DataScope dataScope = new DataScope();
		User user = getCurrentUser();
		dataScope.setUser(user);
		dataScope.setDataFilterSql(dataScopeFilter(user, "s", "t"));
		pageInfo.setDataScope(dataScope);

		// 如果一个用户有多个角色，查询时会出现重复，可以在程序中去重
		/*
		 * List<UserVo> list = userMapper.findUserPageCondition(pageInfo);
		 * List<UserVo> userVoList= new ArrayList<UserVo>(); for(UserVo userVo :
		 * list){ int i=0; for (UserVo userVoloop : list) {
		 * if(userVoloop.getId()==userVoloop.getId()) i++; } if(i==2) continue;
		 * userVoList.add(userVo); } pageInfo.setRows(userVoList);
		 */
		// System.out.println("大银行="+userMapper.findUserPageCondition(pageInfo).size());
		pageInfo.setRows(userMapper.findUserPageCondition(pageInfo));
		pageInfo.setTotal(userMapper.findUserPageCount(pageInfo));
	}

	@Override
	public Long addUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		// 这里不注释会报异常
		// user.setId(keyRedis.nextUserID());//分布式生成用户id
		 user.setUsertype(0);
		// if (user.getNewusertype() == null||) {
		// user.setNewusertype("DWLB_AJJ");
		// }
		userMapper.insert(user);

		Long id = user.getId();
		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();

		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}
		return id;
	}

	@Override
	public void updateUserPwdById(Long userId, String pwd) {
		userMapper.updateUserPwdById(userId, pwd);
	}

	@Override
	public UserVo findUserVoById(Long id) {
		return userMapper.findUserVoById(id);
	}

	@Override
	public void updateUser(UserVo userVo) {
		User user = new User();
		try {
			PropertyUtils.copyProperties(user, userVo);
		} catch (Exception e) {
			LOGGER.error("类转换异常：{}", e);
			throw new RuntimeException("类型转换异常：{}", e);
		}
		Unit temp=	unitMapper.selectByOrganizationId(userVo.getOrganizationId());//判断是否有企业
		if(temp==null&&temp.getId()==null){
			Unit u = new Unit();
			Organization o= organizationMapper.findOrganizationById(Long.valueOf(userVo.getOrganizationId()));
			u.setName(o.getName());
			u.setCreatetime(new Date());
			u.setUnitLinkman(userVo.getName());
			u.setCreatorId(userVo.getId());
			u.setOrganizationId(Integer.parseInt(""+o.getId()));
			u.setSysareaId(o.getAreaId());
			u.setGradetype(areaMapper.selectByPrimaryKey(o.getAreaId()).getId());
			u.setId(IDUtil.UUID());
			u.setType("DWLB_AJJ");
			u.setTelephone(userVo.getPhone());
			unitMapper.insertSelective(u);
		}
		user.setUnitId(unitMapper.selectByOrganizationId(userVo.getOrganizationId()).getId());
		userMapper.updateUser(user);

		/**
		 * 修改组织时候 企业随之修改区域
		 */
		if (user.getOrganizationId() != null) {
			Organization o = organizationMapper.findOrganizationById(Long
					.valueOf(user.getOrganizationId()));
			Unit u = new Unit();
			u.setSysareaId(o.getAreaId());
			u.setId(userVo.getUnitId());
			unitMapper.updateByPrimaryKeySelective(u);
		}

		Long id = userVo.getId();
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}

		String[] roles = userVo.getRoleIds().split(",");
		UserRole userRole = new UserRole();
		for (String string : roles) {
			userRole.setUserId(id);
			userRole.setRoleId(Long.valueOf(string));
			userRoleMapper.insert(userRole);
		}

	}

	@Override
	public void deleteUserById(Long id) {
		userMapper.deleteById(id);
		List<UserRole> userRoles = userRoleMapper.findUserRoleByUserId(id);
		if (userRoles != null && (!userRoles.isEmpty())) {
			for (UserRole userRole : userRoles) {
				userRoleMapper.deleteById(userRole.getId());
			}
		}
	}

	@Override
	public void findByCombogrid(PageInfo pageInfo) {
		List<UserVo> userList = userMapper.findCombogridCondition(pageInfo);
		pageInfo.setTotal(userList.size());
		pageInfo.setRows(userList);
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateUser(user);

	}

	@Override
	public int deleteByPrimaryKeys(String[] ids) {
		return userMapper.deleteByPrimaryKeys(ids);
	}

	@Override
	public void findUserByTypeId(PageInfo pageInfo) {
		List<UserVo> userList = userMapper.findUserByTypeId(pageInfo);
		
		pageInfo.setTotal(userList.size());
		pageInfo.setRows(userList);
		
	}

	
	
	
	
}
