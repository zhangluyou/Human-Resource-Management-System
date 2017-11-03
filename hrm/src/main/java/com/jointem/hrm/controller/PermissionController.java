package com.jointem.hrm.controller;

import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Permissions;
import com.jointem.hrm.entity.Roles;
import com.jointem.hrm.service.PermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService permissionService;
	
	
	/**
	 * 分页查询权限列表，返回权限列表页面（perimissionlist.jsp）
	 * @param currPage 当前页码，初始化为1
	 * @param model    
	 * @param searchText  查找内容，初始化为空
	 * @return
	 */
	@RequiresPermissions("permission:permissionList")
	@RequestMapping("/permissionList")
	public String permissionList(int currPage,Model model,String searchText)
	{
		//设置页面大小
		int pageSize=6;
		PageBean<Permissions> bean=null;
		try {
			bean=permissionService.findPermissionsByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//将查询内容放入域中
		model.addAttribute("searchText",searchText);
		model.addAttribute("pb",bean);
		return "permissionlist";
	}
	
	/**
	 * 修改权限名称，重定向到查询权限列表方法
	 * @param permission 页面传回的permission，包括id,name
	 * @param currPage   当前页码
	 * @param searchText 查找内容
	 * @return
	 */
	@RequestMapping("/updatePermission")
	public String updatePermission(Permissions permission,int currPage,String searchText)
	{
		try {
			permissionService.updatePermission(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:permissionList?currPage="+currPage+"&searchText="+searchText;
	}
	/**
	 * 删除权限，重定向到查询权限列表方法
	 * @param id  权限id
	 * @param currPage 当前页码
	 * @param searchText 查找内容
	 * @return
	 */
	@RequestMapping("/deletePermission")
	public String deletePermission(int id,int currPage,String searchText)
	{
		try {
			permissionService.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:permissionList?currPage="+currPage+"&searchText="+searchText;
	}
	/**
	 * 添加权限
	 * @param url 权限资源路径
	 * @param name 权限名
	 * @param currPage 当前页码
	 * @param searchText 查找内容
	 * @return
	 */
	@RequestMapping("/addPermission")
	public String addPermission(String url,String name,int currPage,String searchText)
	{
		try {
			Permissions permission=new Permissions();
			permission.setName(name);
			permission.setUrl(url);
			permissionService.insertSelective(permission);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:permissionList?currPage="+currPage+"&searchText="+searchText;
	}
	/**
	 * 批量删除权限
	 * @param pid 权限id
	 * @param currPage 当前页码
	 * @param searchText 查找内容
	 * @return
	 */
	@RequestMapping("/delCheckedRoles")
	public String delCheckedRoles(String[] pid,int currPage,String searchText)
	{
		try {
			//判断获取的角色id集合是否为空
			if(pid!=null)
			{
				permissionService.delCheckedRoles(pid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:permissionList?currPage="+currPage+"&searchText="+searchText;
	}
	//权限分配===========================================================================
	@RequiresPermissions("permission:roleAndPermissionList")
	@RequestMapping("/roleAndPermissionList")
	public String roleAndPermissionList(int currPage,String searchText,Model model)
	{
		//查询角色
		int pageSize=4;
		PageBean<Roles> bean=null;
		try {
			
			bean=permissionService.findRolesAndPermissionByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//定义map，用于存放角色以及对应的所有权限（采用拼接）
		Map<Integer, Object> map=new HashMap<Integer, Object>();
		for(Roles role:bean.getList()){
			String mapValue="";
			int i= 0;
			List<Permissions> list = role.getPermissionsList();
			int size = list.size();
			for(Permissions permission:list){
				i++;
//				mapValue+=permission.getName();
				mapValue+=permission.getId();
				mapValue+=i==size?"":",";
			}
			map.put(role.getId(), mapValue);
		}
		//将查询条件放入域中
		model.addAttribute("searchText",searchText);
		//将角色以及所对应的角色放入域中
		model.addAttribute("roleMap",map);
		model.addAttribute("ub",bean);
		return "roleandpermission";
	}
	
	
	/**
	 * 查询所有权限列表
	 * @return 返回json格式的数据
	 */
	@RequestMapping("/findPermissionList")
	@ResponseBody
	public PageBean<Permissions> findPermissionList(int currPage,String searchText,Model model)
	{
		int pageSize=10;
		PageBean<Permissions> bean=null;
		try {
			bean=permissionService.findPermissionsByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("bean","bean");
		return bean;
		
	}
	
	/**
	 * 分配权限
	 * @param permissionId 权限id集合
	 * @param roleId    角色id
	 * @param currPage 当前页码
	 * @param searchText 查找内容
	 * @return
	 */
	@RequestMapping("/addRolePermission")
	public String addRolePermission(String permissionId,int roleId,int currPage,String searchText)
	{
		String[] permissionIds=permissionId.split(",");
		try {
			//先将角色所有权限清空s
			permissionService.clearRolesPermissionsByRoleId(roleId);
			//当选择的角色不为空时,加入选择的角色
			if(permissionIds!=null)
			{
				permissionService.updateRolesPermission(permissionIds, roleId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:roleAndPermissionList?currPage="+currPage+"&searchText="+searchText;
	}
	@RequestMapping("/clearCheckedRoles")
	public String clearCheckedRoles(String[] rid,int currPage,String searchText)
	{
		try {
			//判断是否有选中
			if(rid!=null)
			{
				permissionService.clearCheckedUserRoles(rid);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:roleAndPermissionList?currPage="+currPage+"&searchText="+searchText;
	}
	
}
