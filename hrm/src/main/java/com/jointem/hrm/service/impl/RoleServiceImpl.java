package com.jointem.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.RolesMapper;
import com.jointem.hrm.dao.UsersDao;
import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.entity.UsersRoles;
import com.jointem.hrm.service.RoleService;
@Service("roleService")
public class RoleServiceImpl implements RoleService {

	/**
	 * 查询角色列表，返回list集合
	 */
	@Autowired
	private RolesMapper rolesMapper;
	@Autowired
	private UsersDao usersDao;
	@Override
	public List<Roles> findRoleList() throws Exception {
		
		List<Roles> list=rolesMapper.selectAllRoles();
		return list;
	}
	
	
	/**
	 * 修改角色名称
	 */
	@Override
	public void updateRole(Roles role) throws Exception {
	
		rolesMapper.updateByPrimaryKey(role);
		
	}


	/**
	 * 删除角色
	 */
	@Override
	public void deleteRoleById(int id) throws Exception {
		
		rolesMapper.deleteByPrimaryKey(id);
	}


	/**
	 * 添加角色
	 */
	@Override
	public void addRole(String description) throws Exception {
		Roles role=new Roles();
		role.setDescription(description);
		rolesMapper.insert(role);
	}


	/**
	 * 批量删除角色
	 */
	@Override
	public void delCheckedRoles(String[] rid) throws Exception {
		for(String id:rid)
		{
			int idd=Integer.parseInt(id);
			rolesMapper.deleteByPrimaryKey(idd);
		}
	}

	/**
	 * 分页查询角色列表
	 */

	@Override
	public PageBean<Roles> findRoleByPage(int currPage, int pageSize,String searchText) throws Exception {
		
		//获取当前页的数据
		Map<String,Object> params=new HashMap();
		params.put("start",(currPage-1)*pageSize);
		params.put("pageSize",pageSize);
		params.put("searchText",searchText);
		List<Roles> list=rolesMapper.findCurrRoles(params);
		
		//查询总页数
		int totalCount=rolesMapper.findRolesCount(params);
		
		return new PageBean<Roles>(list, currPage, pageSize, totalCount);
	}




	/**
	 * 分页查询用户列表以及对应的角色
	 */
	@Override
	public PageBean<Users> finUsersAndRolesByPage(int currPage, int pageSize, String searchText) throws Exception {
		//获取当页数据
		//封装传入的参数
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("start", (currPage-1)*pageSize);
		params.put("pageSize", pageSize);
		params.put("searchText", searchText);
		List<Users> list=rolesMapper.finUsersAndRolesList(params);
		
		//查询总条数
		int totalCount=rolesMapper.finUsersAndRolesPageCount(params);
		return new PageBean<Users>(list, currPage, pageSize, totalCount);
	}

	/**
	 * 为用户分配角色，跟新users_roles表
	 */

	@Override
	public void updateUsersRoles(String[] roleIds, int userId) throws Exception {
		for(String roleid:roleIds)
		{
			int roleidd=Integer.parseInt(roleid);
			UsersRoles userRole=new UsersRoles();
			userRole.setUser_id(userId);
			userRole.setRole_id(roleidd);
			rolesMapper.updateUsersRoles(userRole);
		}
	}


	/**
	 * 清空用户角色
	 */
	
	@Override
	public void clearCheckedUserRoles(String[] uid) throws Exception {
		for(String id:uid)
		{
			int uidd=Integer.parseInt(id);
			UsersRoles userRole=new UsersRoles();
			userRole.setUser_id(uidd);
			rolesMapper.deleteUserRolesByUserId(userRole);
		}
	}

	/**
	 * 通过用户名分页查询用户角色列表
	 */

	@Override
	public void findUsersRolesByUserName(String userName) throws Exception {
		
		
	}


	//通过用户id清空用户角色
	@Override
	public void clearUserRolesByUserId(int userId) throws Exception {
		UsersRoles userRole=new UsersRoles();
		userRole.setUser_id(userId);
		rolesMapper.deleteUserRolesByUserId(userRole);
	}


	
}
