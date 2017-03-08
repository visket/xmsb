package com.cody.shiro;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.cody.common.shiro.ShiroUser;
import com.cody.common.utils.UserUtils;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.Role;
import com.cody.entity.sys.User;
import com.cody.service.sys.OrganizationService;
import com.cody.service.sys.RoleService;
import com.cody.service.sys.UserService;
import com.cody.vo.sys.UserVo;
import com.google.common.collect.Sets;

/**
 * @description：shiro权限认证
 * @author：wanhuan
 * @date：2016/11/21
 */
public class ShiroDbRealm extends AuthorizingRealm {

    private static Logger LOGGER = LoggerFactory.getLogger(ShiroDbRealm.class);

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;

    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserVo user = userService.findUserByLoginName(token.getUsername());
        // 账号不存在
        if (user == null) {
            return null;
        }
        // 账号未启用
        if (user.getStatus() == 0) {
            return null;
        }
        List<Long> roleList = roleService.findRoleIdListByUserId(user.getId());
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginname(), user.getName(), roleList);
        
        if(CollectionUtils.isNotEmpty(roleList)){
        	Role firstRole = roleService.findRoleById(roleList.get(0));
        	shiroUser.setFirstRoleName(firstRole.getName());
        }
        
        setCurrentUser(user);//修改用户session
        
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), getName());

    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {

        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        List<Long> roleList = shiroUser.roleList;

        Set<String> urlSet = Sets.newHashSet();
        for (Long roleId : roleList) {
            List<Map<Long, String>> roleResourceList = roleService.findRoleResourceListByRoleId(roleId);
            if (CollectionUtils.isNotEmpty(roleResourceList)) {
                for (Map<Long, String> map : roleResourceList) {
                    if (MapUtils.isNotEmpty(map) && StringUtils.isNoneBlank(map.get("url"))) {
                        urlSet.add(map.get("url"));
                    }
                }
            }
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(urlSet);
        return info;
    }
    
    public void setCurrentUser(User user){
    	if(user.getOrganization().getId() == null){
    		Organization organization = organizationService.findOrganizationById(Long.valueOf(user.getOrganizationId()));
            user.setOrganization(organization);
    	}
        UserUtils.setCurrentUser(user);
    }
}
