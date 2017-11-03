package com.jointem.hrm.service.impl;

import com.alibaba.fastjson.JSON;
import com.jointem.hrm.common.MD5Tools;
import com.jointem.hrm.dao.UsersDao;
import com.jointem.hrm.entity.Department1;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dartagnan on 17/7/27.
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersDao userDao;


    public int insertUser(Users user) {
        Users users = userDao.selectUserByName(user.getUsername());
     if (users != null) {
            throw new RuntimeException("用户名已存在");
        }
            String password = MD5Tools.MD5(user.getPassword());
            user.setPassword(password);
            user.setCreatetime(new Date());
        userDao.insertUser(user);
    return user.getId();
    }

    public Users setList1(String user) {
        return userDao.selectUserByName(user);
    }

    public List<Users> selectUsers(String sort, String startTime, String endTime, String username, String pageNum, String pageSize) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("sort", sort);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        if(null != pageSize && !"".equals(pageSize)){
            params.put("pageSize",Integer.valueOf(pageSize));
        }
        else{
            params.put("pageSize",5);
        }
        if(StringUtils.isNotEmpty(pageNum)){
            params.put("pageNum",(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize));
        }else{
            params.put("pageNum",0);
        }

        List<Users> list = userDao.selectAllUser(params);
        return list;
    }


    public Users getByUsername(String name) {
        return userDao.getUserByName(name);
    }

    public void delectUser(int id) {
        userDao.deleteUser(id);
    }

    public void updateUser(Users user) {
        userDao.updateUser(user);
    }

    public Users selectById(int id) {
        return userDao.selectUserByID(id);
    }


    @Override
    public void delectAllUser(List<String> list) {
        userDao.delectUserByIds(list);
    }

    //    @Override
//    public Map<String,Object> getUserAbstract(){
//        Map<String,Object> result = new HashMap<String,Object>();
//        result.put("userList",userDao.getUserAbstract());
//        result.put("user",userDao.selectUserByID(1));
//
//        return result;
//    }
    public Users findUsersByName(String name) {
        Users user = userDao.findUsersByName(name);
        return user;

    }

    /**
     * @param sort
     * @param startTime
     * @param endTime
     * @param username
     * @return
     */
    public int selectAllCount(String sort, String startTime, String endTime, String username) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        params.put("sort", sort);
        params.put("startTime", startTime);
        params.put("endTime", endTime);
        int count = userDao.selectAllCount(params);
        return count;
    }

    @Override
    public List<Users> getAllUsers() {
        // TODO Auto-generated method stub
        return userDao.selectAllUserMo();
    }
	@Override
	public String getNameById(int id) {
	
		return userDao.getNameById(id);
		
	}

	@Override
	public List<String> getDepartment() {
	
		return userDao.getDep();
	}

	@Override
	public List<String> getPositionByDep(String dep) {
		List<String>pos=userDao.getPositionByDep(dep);         //解决重复
		for(int i=0;i<pos.size();i++){
			if (pos.get(i).equals(pos.get(i+1))) {
				pos.remove(i);
			}
		}
		return pos;
	}

	@Override
	public List<Integer> getUidByPosition(String pos) {
		
		return userDao.getUidByPos(pos);
	}


    /**
     *
     * @return
     */
    public  List<Department1> selectHeadquarters(){
        List<Department1> d_name=userDao.selectHeadquarters();
        return d_name;
    }

}
