package com.jointem.hrm.dao;

import java.util.List;
import java.util.Map;

import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.RolesPermissions;
import com.jointem.hrm.entity.Users;

public interface PermissionDao {

	List<Permissions> findCurrPermissions(Map<String, Object> params);

	int findPermissionsCount(Map<String, Object> params);

	void updateByPrimaryKeySelective(Permissions permission);

	void deleteByPrimaryKey(int id);

	void insertSelective(Permissions permission);


	int findRolesAndPermissionCount(Map<String, Object> params);

	List<Roles> findRolesAndPermissionsList(Map<String, Object> params);

	List<Permissions> findAllPermission();

	void clearRolesPermissionsByRoleId(int roleId);


	void updateRolesPermission(RolesPermissions rolePermission);



	void deleteRolesPermissionsByRoleId(RolesPermissions rolesPermissions);

}
