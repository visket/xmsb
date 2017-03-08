package com.cody.service.sys;

import java.util.List;

import com.cody.common.utils.PageInfo;
import com.cody.common.vo.Tree;
import com.cody.entity.sys.Organization;
import com.cody.vo.sys.RoleVo;

/**
 * @description：部门管理
 * @author：wanhuan
 * @date：2016/11/18
 */
public interface OrganizationService {
	/**
	 * 查询部门资源树
	 * 
	 * @return
	 */
	List<Tree> findTree();

	/**
	 * 查询省级部门资源树
	 * 
	 * @return
	 */
	List<Tree> findTreeProvince(String id);

	/**
	 * 注册放行资源树
	 * 
	 * @return
	 */
	List<Tree> findAnonTree(String type);

	/**
	 * 查询部门数据表格
	 * 
	 * @return
	 */
	List<Organization> findTreeGrid();

	/**
	 * 添加部门
	 * 
	 * @param organization
	 */
	void addOrganization(Organization organization);

	/**
	 * 根据id查找部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 */
	void updateOrganization(Organization organization);

	/**
	 * 根据id删除部门
	 * 
	 * @param id
	 */
	void deleteOrganizationById(Long id);

	void deleteOrganizationAllByPid(Long id);


	/**
	 * 通过角色反向修改组织的 ROLEID
	 * 
	 * @return
	 */
	int updateToRoleId(RoleVo roleVo);
	
	/**
	 * 通过角色ID 查询所有组织方法
	 * @param roleid
	 * @return
	 */
	List<Organization> findByRoleId(Long roleid);
}
