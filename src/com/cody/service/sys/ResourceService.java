package com.cody.service.sys;

import java.util.List;
import com.cody.common.vo.Tree;
import com.cody.entity.sys.Resource;
import com.cody.entity.sys.User;

/**
 * @description：资源管理
 * @author：wanhuan
 * @date：2016/11/18
 */
public interface ResourceService {

    /**
     * 根据用户查询树形菜单列表
     *
     * @param currentUser
     * @return
     */
    List<Tree> findTree(User currentUser);

    /**
     * 查询所有资源
     *
     * @return
     */
    List<Resource> findResourceAll();

    /**
     * 添加资源
     *
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * 查询二级数
     *
     * @return
     */
    List<Tree> findAllTree();

    /**
     * 查询三级数
     *
     * @return
     */
    List<Tree> findAllTrees();

    /**
     * 更新资源
     *
     * @param resource
     */
    void updateResource(Resource resource);

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    Resource findResourceById(Long id);

    /**
     * 根据id删除资源
     *
     * @param id
     */
    void deleteResourceById(Long id);

    /**
     * 根据pid删除所有子节点，包括自己
     * @param id
     */
	void deleteResourceAllByPid(Long id);
}
