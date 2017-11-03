package com.jointem.hrm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	/**
	 * 查找角色列表，重定向到角色列表页面rolelist.jsp
	 * @return
	 */
	
	@RequestMapping("/findRoleList")
	@ResponseBody
	public  List<Roles> findRoleList()
	{
		List<Roles> roleList=null;
		try {
			roleList = roleService.findRoleList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(roleList.toString());
	
		return roleList;
	}
	
	
	/**
	 * 分页查询角色列表，返回角色列表页面
	 * @param currPage 当前页
	 * @param model
	 * @return
	 */
	@RequiresPermissions("roles:findRoleByPage")
	@RequestMapping("/findRoleByPage")
	public String findRoleByPage(int currPage,Model model,String searchText)
	{
		
		//设置页面大小
		int pageSize=3;
		PageBean<Roles> bean=null;
		//调用service 返回pagebean
		try {
			bean=roleService.findRoleByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将查询内容放入域中
		model.addAttribute("searchText",searchText);
		model.addAttribute("rb",bean);
		
		return "rolelist";
	}
	
	
	
	
	/**
	 * 修改角色名称，重定向到分页查找角色列表方法
	 * @return
	 */
	@RequestMapping("/updateRole")
	public String updateRole(Roles role,int currPage,String searchText)
	{
		try {
			roleService.updateRole(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:findRoleByPage?currPage="+currPage+"&searchText="+searchText;
	}
	
	/**
	 * 删除角色，重定向到查找角色列表方法
	 * @return
	 */
	@RequestMapping("/deleteRole")
	public String deleteRoleById(int id,int currPage,String searchText)
	{
		try {
			roleService.deleteRoleById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:findRoleByPage?currPage="+currPage+"&searchText="+searchText;
	}
	
	/**
	 * 添加角色 ,重定向到查找角色列表方法
	 * @param description 角色属性（名称）
	 * @return
	 */
	@RequestMapping("/addRole")
	public String addRole(String description,int currPage,String searchText)
	{
		try {
			roleService.addRole(description);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:findRoleByPage?currPage="+currPage+"&searchText="+searchText;
	}
	
	/**
	 * 批量删除角色
	 * @param rid 选中的角色id
	 * @return
	 */
	@RequestMapping("/delCheckedRoles")
	public String delCheckedRoles(String[] rid,int currPage,String searchText)
	{
		try {
			//判断获取的角色id集合是否为空
			if(rid!=null)
			{
				roleService.delCheckedRoles(rid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:findRoleByPage?currPage="+currPage+"&searchText="+searchText;

	}
	
	/**
	 * 查询用户与角色（按查询条件用户昵称）,返回角色分配（userandrole.jsp）页面
	 * @param currPage 当前页码
	 * @param model
	 * @param searchText 查询内容
	 * @return
	 */
	@RequiresPermissions("roles:roleAssign")
	@RequestMapping("/roleAssign")
	public String roleAssign(int currPage,String searchText,Model model)
	{
		//查询用户
		int pageSize=5;
		PageBean<Users> bean=null;
		try {
			bean=roleService.finUsersAndRolesByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//定义map，用于存放用户以及对应的所有角色（采用拼接）
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		for(Users user:bean.getList()){
			String mapValue="";
			int i= 0;
			List<Roles> list = user.getRolesList();
			int size = list.size();
			for(Roles role:list){
				i++;
				mapValue+=role.getDescription();
				mapValue+=i==size?"":",";
			}
			map.put(user.getId(), mapValue);
		}
		//将查询条件放入域中
		model.addAttribute("searchText",searchText);
		//将用户以及所对应的角色放入域中
		model.addAttribute("roleMap",map);
		model.addAttribute("ub",bean);
		return "userandrole";
	}
	
	/**
	 * 分配角色，添加到用户角色关联表,返回查询“用户角色分配列表“方法
	 * @return
	 */
	@RequestMapping("/addUserRole")
	public String addUserRole(String[] roleIds,int userId,int currPage,String searchText)
	{
		try {
			//先将用户所有角色清空
			roleService.clearUserRolesByUserId(userId);
			//当选择的角色不为空时,加入选择的角色
			if(roleIds!=null)
			{
				roleService.updateUsersRoles(roleIds, userId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:roleAssign?currPage="+currPage+"&searchText="+searchText;
	}
	
	/**
	 * 清空用户角色
	 * @param uid 要清空的角色的用户id集合
	 * @return
	 */
	@RequestMapping("/clearCheckedUserRoles")
	public String clearCheckedUserRoles(String[] uid,int currPage,String searchText)
	{
		try {
			//判断是否有选中
			if(uid!=null)
			{
				roleService.clearCheckedUserRoles(uid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:roleAssign?currPage="+currPage+"&searchText="+searchText;
	}
	
	/**
	 * 通过用户名分页查找用户角色列表
	 * @param userName
	 * @return
	 */
	@RequestMapping("/findUsersRolesByUserName")
	public String findUsersRolesByUserName(String userName)
	{
		try {
			roleService.findUsersRolesByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
