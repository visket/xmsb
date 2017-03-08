package com.cody.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cody.common.shiro.DataScope;
import com.cody.common.utils.PageInfo;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.SysArea;
import com.cody.vo.sys.OrganizationVo;
import com.cody.vo.sys.RoleVo;

public interface OrganizationMapper {
	/**
	 * 删除部门
	 * 
	 * @param id
	 * @return
	 */
	int deleteOrganizationById(Long id);

	/**
	 * 添加部门
	 * 
	 * @param organization
	 * @return
	 */
	int insert(Organization organization);

	/**
	 * 更新部门
	 * 
	 * @param organization
	 * @return
	 */
	int updateOrganization(Organization organization);

	/**
	 * 查询一级部门
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAllByPidNull(DataScope dataScope);

	/**
	 * 查询部门子集
	 * 
	 * @param pid
	 * @return
	 */
	List<Organization> findOrganizationAllByPid(Long pid);

	/**
	 * 查询省级部门子集
	 * 
	 * @param pid
	 * @return
	 */
	List<Organization> findOrganizationProvinceAllByPid(PageInfo pageInfo);

	/**
	 * 查询所有部门集合
	 * 
	 * @return
	 */
	List<Organization> findOrganizationAll(DataScope dataScope);

	/**
	 * 根据id查询部门
	 * 
	 * @param id
	 * @return
	 */
	Organization findOrganizationById(Long id);

	int updateParentIds(Organization organization);

	List<Organization> findByParentIdsLike(Organization organization);

	int deleteOrganizationByPid(Long pid);

	List<Organization> findOrganizationAllByPid(@Param("pid") Integer pid);

	int deleteByPrimaryKey(Long id);

	/**
	 * 通过角色反向修改组织的 ROLEID
	 * 
	 * @return
	 */
	int updateToRoleId(OrganizationVo organizationVo);
	/**
	 * 通过角色ID 查询所有组织方法
	 * @param roleid
	 * @return
	 */
	List<Organization> findByRoleId(Long roleid);
}