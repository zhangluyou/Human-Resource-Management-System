package com.jointem.hrm.service;

import java.util.List;

import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;

public interface RoleService {

	public List<Roles> findRoleList() throws Exception;

	public void updateRole(Roles role) throws Exception;

	public void deleteRoleById(int id) throws Exception;

	public void addRole(String description) throws Exception;

	public void delCheckedRoles(String[] rid) throws Exception;

	public PageBean<Roles> findRoleByPage(int currPage, int pageSize,String searchText) throws Exception;

	public PageBean<Users> finUsersAndRolesByPage(int currPage, int pageSize,String searchText) throws Exception;

	public void updateUsersRoles(String[] roleIds, int userId) throws Exception;

	public void clearCheckedUserRoles(String[] uid) throws Exception;

	public void findUsersRolesByUserName(String userName) throws Exception;

	public void clearUserRolesByUserId(int userId) throws Exception;
}
