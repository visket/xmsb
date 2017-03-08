package com.cody.service.sys.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cody.common.shiro.DataScope;
import com.cody.common.utils.PageInfo;
import com.cody.common.utils.UserUtils;
import com.cody.common.utils.reflect.ParamsReflect;
import com.cody.common.vo.Tree;
import com.cody.entity.sys.Organization;
import com.cody.entity.sys.SysArea;
import com.cody.entity.sys.User;
import com.cody.mapper.sys.OrganizationMapper;
import com.cody.service.BaseService;
import com.cody.service.sys.OrganizationService;
import com.cody.vo.sys.OrganizationVo;
import com.cody.vo.sys.RoleVo;
import com.google.common.collect.Lists;

/**
 * @author：wanhuan
 * @date：2016/11/18
 */

@Service
public class OrganizationServiceImpl extends BaseService implements
		OrganizationService {

	@Autowired
	private OrganizationMapper organizationMapper;

	@Override
	public List<Tree> findTreeProvince(String id) {
		List<Tree> trees = Lists.newArrayList();

		DataScope dataScope = new DataScope();
		User user = (User) UserUtils.getCurrentUser();
		dataScope.setDataFilterSql(treeScopeFilter(user, "o", "u"));

		// 查询组织架构
		List<Organization> organizationFather = organizationMapper
				.findOrganizationAllByPidNull(dataScope);

		Map<String, Object> condition = ParamsReflect
				.setParamsByReflect(new Organization());
		PageInfo pageInfo = new PageInfo();

		if (organizationFather != null) {
			for (Organization organizationOne : organizationFather) {
				Tree treeOne = new Tree();

				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());
				treeOne.setCode(organizationOne.getCode());

				condition.put("pid =", organizationOne.getId());
				condition.put("type=", id);

				// 查询所有一级机构下的子机构
				// List<Organization> organizationSon =
				// organizationMapper.findOrganizationProvinceAllByPid(organizationOne.getId(),id);

				pageInfo.setCondition(condition);
				List<Organization> organizationSon = organizationMapper
						.findOrganizationProvinceAllByPid(pageInfo);

				if (organizationSon != null) {
					List<Tree> tree = Lists.newArrayList();
					for (Organization organizationTwo : organizationSon) {
						Tree treeTwo = new Tree();
						// 二级架构的属性
						treeTwo.setId(organizationTwo.getId());
						treeTwo.setText(organizationTwo.getName());
						treeTwo.setIconCls(organizationTwo.getIcon());
						treeTwo.setCode(organizationTwo.getCode());
						tree.add(treeTwo);

						// 查询所有二级机构下的子机构
						// List<Organization> organizationThird =
						// organizationMapper.findOrganizationAllByPid(organizationTwo.getId());

					}
					treeOne.setChildren(tree);
					treeOne.setChildrenCodeCount(tree.size());
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}

	@Override
	public List<Tree> findTree() {
		List<Tree> trees = Lists.newArrayList();

		DataScope dataScope = new DataScope();
		User user = (User) UserUtils.getCurrentUser();
		dataScope.setDataFilterSql(treeScopeFilter(user, "o", "u"));

		// 查询组织架构
		List<Organization> organizationFather = organizationMapper
				.findOrganizationAllByPidNull(dataScope);

		if (organizationFather != null) {
			for (Organization organizationOne : organizationFather) {
				Tree treeOne = new Tree();

				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());
				treeOne.setCode(organizationOne.getCode());

				// 查询所有一级机构下的子机构
				List<Organization> organizationSon = organizationMapper
						.findOrganizationAllByPid(organizationOne.getId());

				if (organizationSon != null) {
					List<Tree> tree = Lists.newArrayList();
					for (Organization organizationTwo : organizationSon) {
						Tree treeTwo = new Tree();
						// 二级架构的属性
						treeTwo.setId(organizationTwo.getId());
						treeTwo.setText(organizationTwo.getName());
						treeTwo.setIconCls(organizationTwo.getIcon());
						treeTwo.setCode(organizationTwo.getCode());
						tree.add(treeTwo);

						// 查询所有二级机构下的子机构
						List<Organization> organizationThird = organizationMapper
								.findOrganizationAllByPid(organizationTwo
										.getId());

						if (organizationThird != null) {
							List<Tree> tree2 = Lists.newArrayList();
							for (Organization organizationThree : organizationThird) {
								Tree treeThree = new Tree();
								treeThree.setId(organizationThree.getId());
								treeThree.setText(organizationThree.getName());
								treeThree.setIconCls(organizationThree
										.getIcon());
								treeThree.setCode(organizationThree.getCode());
								tree2.add(treeThree);

								List<Organization> organizationForth = organizationMapper
										.findOrganizationAllByPid(organizationThree
												.getId());
								if (organizationForth != null) {
									List<Tree> tree3 = Lists.newArrayList();
									for (Organization organizationFour : organizationForth) {
										Tree treeFour = new Tree();
										treeFour.setId(organizationFour.getId());
										treeFour.setText(organizationFour
												.getName());
										treeFour.setIconCls(organizationFour
												.getIcon());
										treeFour.setCode(organizationFour
												.getCode());
										tree3.add(treeFour);
									}
									treeThree.setChildren(tree3);
									treeThree
											.setChildrenCodeCount(tree3.size());
								}

							}
							treeTwo.setChildren(tree2);
							treeTwo.setChildrenCodeCount(tree2.size());
						}

					}
					treeOne.setChildren(tree);
					treeOne.setChildrenCodeCount(tree.size());
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}

	@Override
	public List<Tree> findAnonTree(String type) {
		List<Tree> trees = Lists.newArrayList();
		// 查询所有一级机构
		List<Organization> organizationFather = organizationMapper
				.findOrganizationAllByPid(0);
		if (organizationFather != null) {
			for (Organization organizationOne : organizationFather) {
				Tree treeOne = new Tree();
				treeOne.setId(organizationOne.getId());
				treeOne.setText(organizationOne.getName());
				treeOne.setIconCls(organizationOne.getIcon());
				treeOne.setCode(organizationOne.getCode());
				// 查询所有一级机构下的子机构
				List<Organization> organizationSon = organizationMapper
						.findOrganizationAllByPid(organizationOne.getId());
				if (organizationSon != null) {
					List<Tree> tree = Lists.newArrayList();
					for (Organization organizationTwo : organizationSon) {
						Tree treeTwo = new Tree();
						// 二级架构的属性
						if (type==null||type.equals("") || type == "") {
							treeTwo.setId(organizationTwo.getId());
							treeTwo.setText(organizationTwo.getName());
							treeTwo.setIconCls(organizationTwo.getIcon());
							treeTwo.setCode(organizationTwo.getCode());
							tree.add(treeTwo);
						} else {
							if (organizationTwo.getType().equals(type)) {
								treeTwo.setId(organizationTwo.getId());
								treeTwo.setText(organizationTwo.getName());
								treeTwo.setIconCls(organizationTwo.getIcon());
								treeTwo.setCode(organizationTwo.getCode());
								tree.add(treeTwo);
							}
						}
						// 查询所有二级机构下的子机构
						List<Organization> organizationThird = organizationMapper
								.findOrganizationAllByPid(organizationTwo
										.getId());
						if (organizationThird != null) {
							List<Tree> tree2 = Lists.newArrayList();
							for (Organization organizationThree : organizationThird) {
								Tree treeThree = new Tree();
								treeThree.setId(organizationThree.getId());
								treeThree.setText(organizationThree.getName());
								treeThree.setIconCls(organizationThree
										.getIcon());
								treeThree.setCode(organizationThree.getCode());
								tree2.add(treeThree);
								List<Organization> organizationForth = organizationMapper
										.findOrganizationAllByPid(organizationThree
												.getId());
								if (organizationForth != null) {
									List<Tree> tree3 = Lists.newArrayList();
									for (Organization organizationFour : organizationForth) {
										Tree treeFour = new Tree();
										treeFour.setId(organizationFour.getId());
										treeFour.setText(organizationFour
												.getName());
										treeFour.setIconCls(organizationFour
												.getIcon());
										treeFour.setCode(organizationFour
												.getCode());
										tree3.add(treeFour);
									}
									treeThree.setChildren(tree3);
									treeThree
											.setChildrenCodeCount(tree3.size());
								}

							}
							treeTwo.setChildren(tree2);
							treeTwo.setChildrenCodeCount(tree2.size());
						}

					}
					treeOne.setChildren(tree);
					treeOne.setChildrenCodeCount(tree.size());
				} else {
					treeOne.setState("closed");
				}
				trees.add(treeOne);
			}
		}
		return trees;
	}

	@Override
	public List<Organization> findTreeGrid() {
		DataScope dataScope = new DataScope();
		User currentUser = (User) UserUtils.getCurrentUser();
		dataScope.setUser(currentUser);
		dataScope.setDataFilterSql(dataScopeFilter(currentUser, "o", "u"));
		return organizationMapper.findOrganizationAll(dataScope);
	}

	@Override
	public void addOrganization(Organization organization) {
		Organization parent = null;
		if (organization.getPid() == null || organization.getPid() == 0) {
			parent = new Organization();
			parent.setId(0L);
			parent.setPids(StringUtils.EMPTY);
		} else {
			parent = findOrganizationById(organization.getPid());
		}

		// 设置新的父节点串
		organization.setPid(parent.getId());
		organization.setPids(parent.getPids() + parent.getId() + ",");

		// 保存实体
		organizationMapper.insert(organization);
	}

	@Override
	public Organization findOrganizationById(Long id) {
		return organizationMapper.findOrganizationById(id);
	}

	@Override
	public void updateOrganization(Organization organization) {
		Organization parent = null;
		if (organization.getPid() == null || organization.getPid() == 0) {
			parent = new Organization();
			parent.setId(0L);
			parent.setPids(StringUtils.EMPTY);
		} else {
			parent = findOrganizationById(organization.getPid());
		}

		String oldParentIds = findOrganizationById(organization.getId())
				.getPids();

		// 设置新的父节点串
		organization.setPid(parent.getId());
		organization.setPids(parent.getPids() + parent.getId() + ",");

		// 保存实体
		organizationMapper.updateOrganization(organization);

		// 更新子节点 parentIds
		Organization o = new Organization();
		o.setPids("%," + organization.getId() + ",%");
		List<Organization> list = organizationMapper.findByParentIdsLike(o);
		for (Organization e : list) {
			if (e.getPids() != null && oldParentIds != null) {
				e.setPids(e.getPids().replace(oldParentIds,
						organization.getPids()));
				organizationMapper.updateParentIds(e);
			}
		}
		organizationMapper.updateOrganization(organization);
	}

	@Override
	public void deleteOrganizationById(Long id) {
		organizationMapper.deleteOrganizationByPid(id);
	}

	@Override
	public void deleteOrganizationAllByPid(Long id) {
		List<Organization> list = organizationMapper
				.findOrganizationAllByPid(id);
		organizationMapper.deleteByPrimaryKey(id);
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Organization organization = (Organization) iterator.next();
			deleteOrganizationAllByPid(organization.getId());
		}
	}

	@Override
	public int updateToRoleId(RoleVo roleVo) {
		String[] pids = roleVo.getPid().split(",");
		Long[] oids = new Long[pids.length];
		for (int i = 0; i < pids.length; i++) {
			oids[i] = Long.valueOf(pids[i]);
		}
		OrganizationVo o = new OrganizationVo();
		o.setOids(oids);
		o.setRoleid(roleVo.getId());
		return organizationMapper.updateToRoleId(o);
	}

	@Override
	public List<Organization> findByRoleId(Long roleid) {
		return organizationMapper.findByRoleId(roleid);
	}

}
