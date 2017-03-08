package com.cody.mapper.sys;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.cody.entity.sys.Resource;

public interface ResourceMapper {
    /**
     * 添加资源
     *
     * @param resource
     * @return
     */
    int insert(Resource resource);

    /**
     * 修改资源
     *
     * @param resource
     * @return
     */
    int updateResource(Resource resource);

    /**
     * 查询菜单资源
     *
     * @param resourceType
     * @param pid
     * @return
     */
    List<Resource> findResourceAllByTypeAndPid(@Param("resourceType") Integer resourceType, @Param("pid") Long pid);

    /**
     * 查询所有资源
     *
     * @return
     */
    List<Resource> findResourceAll();

    /**
     * 查询一级资源
     *
     * @param resourceMenu
     * @return
     */
    List<Resource> findResourceAllByTypeAndPidNull(Integer resourceMenu);

    /**
     * 根据id查询资源
     *
     * @param id
     * @return
     */
    Resource findResourceById(Long id);

    /**
     * 删除资源
     *
     * @param id
     * @return
     */
    int deleteResourceById(Long id);
    
    /**
     * 根据pid查询所有子节点
     * @param id
     * @return
     */
    List<Resource> findResourceAllByPid(Long pid);
    /**
     * @Title: updateParentIds 
     * @Description: 更新父节点
     * @param @param resource
     * @param @return    设定文件 
     * @return int    返回类型 
     * @throws
     */
    int updateParentIds(Resource resource);
    /**
     * 
     * @Title: findByParentIdsLike 
     * @Description: 根据父ids查询
     * @param @param resource
     * @param @return    设定文件 
     * @return List<Resource>    返回类型 
     * @throws
     */
    List<Resource> findByParentIdsLike(Resource resource);
}