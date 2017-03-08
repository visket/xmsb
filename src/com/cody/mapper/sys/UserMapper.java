package com.cody.mapper.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.User;
import com.cody.mapper.BaseMapper;
import com.cody.vo.sys.UserVo;

public interface UserMapper extends BaseMapper {
    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    int updateUser(User user);

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
     * @return
     */
    List findUserPageCondition(PageInfo pageInfo);

    /**
     * 统计用户
     *
     * @param pageInfo
     * @return
     */
    int findUserPageCount(PageInfo pageInfo);

    /**
     * 修改用户密码
     *
     * @param userId
     * @param pwd
     */
    void updateUserPwdById(@Param("userId") Long userId, @Param("pwd") String pwd);

    /**
     * 根据用户id查询用户带部门
     *
     * @param id
     * @return
     */
    UserVo findUserVoById(Long id);
    
    
    /**
     * 用户列表 by combogrid
     *
     * @param pageInfo
     * @return
     */
    List<UserVo> findCombogridCondition(PageInfo pageInfo);

    /**
     * 统计用户 by combogrid
     *
     * @param pageInfo
     * @return
     */
    int findCombogridCount(PageInfo pageInfo);
    
    List<UserVo> findUserByTypeId(PageInfo pageInfo);
    
}