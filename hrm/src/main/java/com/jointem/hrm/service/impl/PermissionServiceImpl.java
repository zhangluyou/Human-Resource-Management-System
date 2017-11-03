package com.jointem.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.PermissionDao;
import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.RolesPermissions;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.entity.UsersRoles;
import com.jointem.hrm.service.PermissionService;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Autowired
	private PermissionDao permissionDao; 
	
	/**
	 * 按查询内容分页查询权限列表
	 */
	@Override
	public PageBean<Permissions> findPermissionsByPage(int currPage, int pageSize, String searchText) throws Exception {
		//获取当前页的数据
		Map<String,Object> params=new HashMap();
		params.put("start",(currPage-1)*pageSize);
		params.put("pageSize",pageSize);
		params.put("searchText",searchText);
		List<Permissions> list=permissionDao.findCurrPermissions(params);
		//查询总页数
		int totalCount=permissionDao.findPermissionsCount(params);
		
		return new PageBean<Permissions>(list, currPage, pageSize, totalCount);
				
	}

	/**
	 * 更改权限名
	 */
	@Override
	public void updatePermission(Permissions permission) throws Exception {
		permissionDao.updateByPrimaryKeySelective(permission);
	}

	/**
	 * 根据id删除权限
	 */
	@Override
	public void deleteByPrimaryKey(int id) throws Exception {
		permissionDao.deleteByPrimaryKey(id);
	}
	
	/**
	 * 添加权限
	 */

	@Override
	public void insertSelective(Permissions permission) throws Exception {
		permissionDao.insertSelective(permission);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delCheckedRoles(String[] pid) throws Exception {
		for(String id:pid)
		{
			int idd=Integer.parseInt(id);
			permissionDao.deleteByPrimaryKey(idd);
		}
	}

	/**
	 * 查找角色以及其所对应的所有权限集合
	 */
	@Override
	public PageBean<Roles> findRolesAndPermissionByPage(int currPage, int pageSize, String searchText)throws Exception {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("start", (currPage-1)*pageSize);
		params.put("pageSize", pageSize);
		params.put("searchText", searchText);
		List<Roles> list=permissionDao.findRolesAndPermissionsList(params);
		
		//查询总条数
		int totalCount=permissionDao.findRolesAndPermissionCount(params);
		return new PageBean<Roles>(list, currPage, pageSize, totalCount);
	}
	
	/**
	 * 查找权限列表
	 */

	@Override
	
	public List<Permissions> findPermissionList() throws Exception {
		List<Permissions> permissionList=permissionDao.findAllPermission();
		return permissionList;
	}

	/**
	 * 通过用户名id清空角色权限表
	 */
	@Override
	public void clearRolesPermissionsByRoleId(int roleId) throws Exception {
		RolesPermissions rolesPermissions=new RolesPermissions();
		rolesPermissions.setRole_Id(roleId);
		permissionDao.deleteRolesPermissionsByRoleId(rolesPermissions);
	}

	/**
	 * 插入数据
	 */
	@Override
	public void updateRolesPermission(String[] permissionIds, int roleId) throws Exception {
		for(String permissionId:permissionIds)
		{
			Integer perimissionIdd=Integer.parseInt(permissionId);
			RolesPermissions rolePermission=new RolesPermissions();
			rolePermission.setRole_Id(roleId);
			rolePermission.setPermission_Id(perimissionIdd);
			permissionDao.updateRolesPermission(rolePermission);
		}
	}
	/**
	 * 根据角色id集合批量清除角色权限
	 */

	@Override
	public void clearCheckedUserRoles(String[] rid) throws Exception {
		for(String roleId:rid)
		{
			Integer roleIdd=Integer.parseInt(roleId);
			this.clearRolesPermissionsByRoleId(roleIdd);
		}
	}
	
}
