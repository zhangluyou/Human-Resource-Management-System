package com.jointem.hrm.dao;

import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.entity.UsersRoles;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface RolesMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);
    
    Roles selectByPrimaryKey(Integer id);



    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

	List<Roles> selectAllRoles();


	List<Roles> findCurrRoles(Map<String, Object> params);

	int findCountByName(String roleName);

	List<Users> finUsersAndRolesList(Map<String, Object> params);

	int finUsersAndRolesPageCount(Map<String, Object> params);
	
	void updateUsersRoles(UsersRoles userRole);

	void deleteUserRolesByUserId(UsersRoles userRole);

	int findRolesCount(Map<String, Object> params);

}