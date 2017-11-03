package com.jointem.hrm.service;

import java.util.List;

import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;

public interface PermissionService {

	PageBean<Permissions> findPermissionsByPage(int currPage, int pageSize, String searchText) throws Exception;

	void updatePermission(Permissions permission) throws Exception;

	void deleteByPrimaryKey(int id) throws Exception;

	void insertSelective(Permissions permission) throws Exception;

	void delCheckedRoles(String[] pid) throws Exception;

	PageBean<Roles> findRolesAndPermissionByPage(int currPage, int pageSize, String searchText) throws Exception;

	List<Permissions> findPermissionList() throws Exception;


	void clearRolesPermissionsByRoleId(int roleId) throws Exception;

	void updateRolesPermission(String[] permissionIds, int roleId) throws Exception;

	void clearCheckedUserRoles(String[] rid) throws Exception;

}
