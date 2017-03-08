package com.cody.service.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.User;
import com.cody.vo.sys.UserVo;


/**
 * @description：用户管理
 * @author：wanhuan
 * @date：2016/11/18
 */
public interface UserService {
    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    UserVo findUserByLoginName(String username);

    /**
     * 根据用户id查询用户
     *
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 用户列表
     *
     * @param pageInfo
     */
    void findDataGrid(PageInfo pageInfo);

    /**
     * 添加用户
     *
     * @param userVo
     */
    Long addUser(UserVo userVo);

    /**
     * 修改密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(Long userId, String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(Long id);

    /**
     * 修改用户
     *
     * @param userVo
     */
    void updateUser(UserVo userVo);
    /**
     * 修改用户 传USER 个人中心使用
     * @param user
     */
    int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id
     */
    void deleteUserById(Long id);
    
    /**
     * 批量删除
     * @param ids
     * @return
     */
    int deleteByPrimaryKeys(String[] ids);
    
    
    /**
     * 用户列表 by Combogrid
     *
     * @param pageInfo
     */
    void findByCombogrid(PageInfo pageInfo);

    
    void findUserByTypeId(PageInfo pageInfo);
    
}

