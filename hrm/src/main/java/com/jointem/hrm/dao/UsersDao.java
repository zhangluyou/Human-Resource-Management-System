package com.jointem.hrm.dao;

import com.jointem.hrm.entity.Department1;
import com.jointem.hrm.entity.Users;

import java.util.List;
import java.util.Map;

/**
 * Created by dartagnan on 17/7/27.
 */

public interface UsersDao {
    //    @Select("select * from users where users.id = #{id}")
    public Users selectUserByID(int id);
    //    @Select("select * from users where user.userName like CONCAT('%',#{userName},'%') ")
    public List<Users> selectUsersByName(String userName);
    public List<Users> getAllUsers();
    public Users selectUserByName(String username);
    public void insertUser(Users user);

    public void updateUser(Users user);

    public void deleteUser(int id);

  //  public Users setList(String user);
    public Users getUserByName(String name);

    public List<Users> selectArrayUser(List list);
    public List<Users> selectAllUser(Map<String,Object> map);
    List<Users> selectAllUserMo();
    Integer delectUserByIds(List<String> list);
    Users findUsersByName(String usename);
    int selectAllCount(Map<String,Object> map);
	public String getNameById(int id);
	public List<String> getDep();
	public List<String> getPositionByDep(String dep);
	public List<Integer> getUidByPos(String pos);
   public  List<Department1> selectHeadquarters();
    public Map selectPositionNameById(Map<String,Object> map);

   
}
