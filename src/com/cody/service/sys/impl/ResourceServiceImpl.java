package com.cody.service.sys.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cody.common.core.Constants;
import com.cody.common.vo.Tree;
import com.cody.entity.sys.Resource;
import com.cody.entity.sys.User;
import com.cody.mapper.sys.ResourceMapper;
import com.cody.mapper.sys.RoleMapper;
import com.cody.mapper.sys.UserRoleMapper;
import com.cody.service.sys.ResourceService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * @author：wanhuan
 * @date：2016/11/18
 */

@Service
public class ResourceServiceImpl implements ResourceService {

    private static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Tree> findTree(User user) {
        List<Tree> trees = Lists.newArrayList();
        // 超级管理
        if (user.getLoginname().equals("admin")) {
            List<Resource> resourceFather = resourceMapper.findResourceAllByTypeAndPidNull(Constants.RESOURCE_MENU);
            if (resourceFather == null) {
                return null;
            }

            for (Resource resourceOne : resourceFather) {
                Tree treeOne = new Tree();

                treeOne.setId(resourceOne.getId());
                treeOne.setText(resourceOne.getName());
                treeOne.setIconCls(resourceOne.getIcon());
                treeOne.setAttributes(resourceOne.getUrl());
                List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Constants.RESOURCE_MENU, resourceOne.getId());

                if (resourceSon != null) {
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceSon) {
                        Tree treeTwo = new Tree();
                        treeTwo.setId(resourceTwo.getId());
                        treeTwo.setText(resourceTwo.getName());
                        treeTwo.setIconCls(resourceTwo.getIcon());
                        treeTwo.setAttributes(resourceTwo.getUrl());
                        //后加
                        treeTwo.setPid(String.valueOf(resourceTwo.getPid()));
                        tree.add(treeTwo);
                    }
                    treeOne.setChildren(tree);
                } else {
                    treeOne.setState("closed");
                }
                trees.add(treeOne);
            }
            return trees;
        }

        // 普通用户
        Set<Resource> resourceIdList = Sets.newHashSet();
        List<Long> roleIdList = userRoleMapper.findRoleIdListByUserId(user.getId());
        for (Long i : roleIdList) {
            List<Resource> resourceIdLists = roleMapper.findResourceIdListByRoleIdAndType(i);
            for (Resource resource: resourceIdLists) {
                resourceIdList.add(resource);
            }
        }
        
        List<Resource> resourceList = new ArrayList<Resource>(resourceIdList);
        Collections.sort(resourceList);
        
        for (Resource resource : resourceList) {
                if (resource != null && resource.getPid() == 0) {
                    Tree treeOne = new Tree();
                    treeOne.setId(resource.getId());
                    treeOne.setText(resource.getName());
                    treeOne.setIconCls(resource.getIcon());
                    treeOne.setAttributes(resource.getUrl());
                    List<Tree> tree = Lists.newArrayList();
                    for (Resource resourceTwo : resourceIdList) {
                        if (resourceTwo.getPid() != null && resource.getId().longValue() == resourceTwo.getPid().longValue()) {
                            Tree treeTwo = new Tree();
                            treeTwo.setId(resourceTwo.getId());
                            treeTwo.setText(resourceTwo.getName());
                            treeTwo.setIconCls(resourceTwo.getIcon());
                            treeTwo.setAttributes(resourceTwo.getUrl());
                          //后加
                            treeTwo.setPid(String.valueOf(resourceTwo.getPid()));
                            tree.add(treeTwo);
                        }
                    }
                    treeOne.setChildren(tree);
                    trees.add(treeOne);
                }
        }
        return trees;
    }

    @Override
    public List<Resource> findResourceAll() {
        return resourceMapper.findResourceAll();
    }

    @Override
    public void addResource(Resource resource) {
    	Resource parent = null;
    	if(resource.getPid() == null || resource.getPid() == 0){
    		parent = new Resource();
    		parent.setId(0L);
    		parent.setPids(StringUtils.EMPTY);
    	}else{
    		parent = findResourceById(resource.getPid());
    	}
    	
		// 设置新的父节点串
    	resource.setPid(parent.getId());
		resource.setPids(parent.getPids()+parent.getId()+",");
		
		// 保存实体
		resourceMapper.insert(resource);
    }

    @Override
    public List<Tree> findAllTree() {
        List<Tree> trees = Lists.newArrayList();
        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Constants.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }
        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());
            // 查询所有一级树下的菜单
            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Constants.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon != null) {
                List<Tree> tree = Lists.newArrayList();
                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();
                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());

                    treeTwo.setPid(String.valueOf(resourceTwo.getPid()));
                    tree.add(treeTwo);
                }
                treeOne.setChildren(tree);
            } else {
                treeOne.setState("closed");
            }
            trees.add(treeOne);
        }
        return trees;
    }

    @Override
    public List<Tree> findAllTrees() {
        List<Tree> treeOneList = Lists.newArrayList();

        // 查询所有的一级树
        List<Resource> resources = resourceMapper.findResourceAllByTypeAndPidNull(Constants.RESOURCE_MENU);
        if (resources == null) {
            return null;
        }

        for (Resource resourceOne : resources) {
            Tree treeOne = new Tree();

            treeOne.setId(resourceOne.getId());
            treeOne.setText(resourceOne.getName());
            treeOne.setIconCls(resourceOne.getIcon());
            treeOne.setAttributes(resourceOne.getUrl());

            List<Resource> resourceSon = resourceMapper.findResourceAllByTypeAndPid(Constants.RESOURCE_MENU, resourceOne.getId());

            if (resourceSon == null) {
                treeOne.setState("closed");
            } else {
                List<Tree> treeTwoList = Lists.newArrayList();

                for (Resource resourceTwo : resourceSon) {
                    Tree treeTwo = new Tree();

                    treeTwo.setId(resourceTwo.getId());
                    treeTwo.setText(resourceTwo.getName());
                    treeTwo.setIconCls(resourceTwo.getIcon());
                    treeTwo.setAttributes(resourceTwo.getUrl());
                  //后加
                    treeTwo.setPid(String.valueOf(resourceTwo.getPid()));

                    /***************************************************/
                    List<Resource> resourceSons = resourceMapper.findResourceAllByTypeAndPid(Constants.RESOURCE_BUTTON, resourceTwo.getId());

                    if (resourceSons == null) {
                        treeTwo.setState("closed");
                    } else {
                        List<Tree> treeThreeList = Lists.newArrayList();

                        for (Resource resourceThree : resourceSons) {
                            Tree treeThree = new Tree();

                            treeThree.setId(resourceThree.getId());
                            treeThree.setText(resourceThree.getName());
                            treeThree.setIconCls(resourceThree.getIcon());
                            treeThree.setAttributes(resourceThree.getUrl());

                            treeThreeList.add(treeThree);
                        }
                        treeTwo.setChildren(treeThreeList);
                    }
                    /***************************************************/

                    treeTwoList.add(treeTwo);
                }
                treeOne.setChildren(treeTwoList);
            }

            treeOneList.add(treeOne);
        }
        return treeOneList;
    }

    @Override
    public void updateResource(Resource resource) {
        Resource parent = null;
    	if(resource.getPid() == null || resource.getPid() == 0){
    		parent = new Resource();
    		parent.setId(0L);
    		parent.setPids(StringUtils.EMPTY);
    	}else{
    		parent = findResourceById(resource.getPid());
    	}
    	
    	String oldParentIds = findResourceById(resource.getId()).getPids();
		
		// 设置新的父节点串
    	resource.setPid(parent.getId());
		resource.setPids(parent.getPids()+parent.getId()+",");
		
		// 保存实体
		resourceMapper.updateResource(resource);
		
		// 更新子节点 parentIds
		Resource o = new Resource();
		o.setPids("%,"+resource.getId()+",%");
		List<Resource> list = resourceMapper.findByParentIdsLike(o);
		for (Resource e : list){
			if (e.getPids() != null && oldParentIds != null){
				e.setPids(e.getPids().replace(oldParentIds, resource.getPids()));
				resourceMapper.updateParentIds(e);
			}
		}
    }

    @Override
    public Resource findResourceById(Long id) {
        return resourceMapper.findResourceById(id);
    }

    @Override
    public void deleteResourceById(Long id) {
        int delete = resourceMapper.deleteResourceById(id);
        if (delete != 1) {
            throw new RuntimeException("删除失败");
        }
    }
    
    @Override
    public void deleteResourceAllByPid(Long id){
    	//资源删除
    	List<Resource> list = resourceMapper.findResourceAllByPid(id);
    	resourceMapper.deleteResourceById(id);
    	for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Resource resource = (Resource) iterator.next();
			deleteResourceAllByPid(resource.getId());
		}
    }

}
