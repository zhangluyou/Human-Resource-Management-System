package com.jointem.hrm.service;

import com.jointem.hrm.entity.Department1;
import com.jointem.hrm.entity.Users;

import java.util.List;

/**
 * Created by dartagnan on 17/7/27.
 */
public interface UserService {
    public int insertUser(Users user);
    
    public Users setList1(String user);
    
    public Users getByUsername(String name);
    
    public void delectUser(int id);
    
    public void updateUser(Users user);
    
    public String getNameById(int id);    //通过id得到真实姓名
    
    public List<String> getDepartment();       //获取所有部门名称 
    
    public List<String>getPositionByDep(String dep); //通过部门获取该部门的所有职位
    
    public List<Integer>getUidByPosition(String pos); //通过职位获取员工ID；
    
    public List<Users> selectUsers(String sort,String startTime,String endTime,String username,String pageNum,String pageSize);
    
    public void delectAllUser(List<String> list);
    
	public Users findUsersByName(String username);
	
    public int selectAllCount(String sort,String startTime,String endTime,String username);
    
    public List<Users>getAllUsers();
    public Users selectById(int id);
    public  List<Department1> selectHeadquarters();
}
