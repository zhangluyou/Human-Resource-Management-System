package com.jointem.hrm.controller;

import com.alibaba.fastjson.JSON;
import com.jointem.hrm.common.JsonResult;
import com.jointem.hrm.entity.Institution;
import com.jointem.hrm.entity.PageBean;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.InstitutionService;
import com.jointem.hrm.service.RoleService;
import com.jointem.hrm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/institution")
public class InstitutionController {

	@Autowired
	private UserService userServic;
	@Autowired
	private RoleService roleService;
	@Autowired
	private InstitutionService institutionService;
	
	/**
	 * 返回奖惩信息登记界面
	 * @return
	 */
	@RequiresPermissions("institution:addInstitution")
	@RequestMapping("/addInstitution")
	public String addInstitution()
	{
		return "addinstitution";
	}
	
	/**
	 * 调用roleservice查找用户列表
	 * @param currPage 当前页码
	 * @param searchText 查找内容（实际不需要该参数，只是为了与roleservice中的方法对应）
	 * @return
	 */
	@RequestMapping("/getStudentsList")
	@ResponseBody
	public PageBean<Users> getStudentsList(int currPage,String searchText)
	{
		int pageSize=10;
		PageBean<Users> bean=null;
		try {
			bean=roleService.finUsersAndRolesByPage(currPage,pageSize,searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	

	/**
	 *
	 * @return
	 */
	@RequestMapping("/tolistInstitution")
	public String toInstitutiontoadmin(){
		Subject subject = SecurityUtils.getSubject();
//		方法1
//		Users user=(Users) subject.getSession().getAttribute("user");
//		List<Roles> roles=user.getRolesList();
//		boolean isHasRole=false;
//		for(Roles role:roles)
//		{
//			if(role.getDescription().equals("admin"))
//			{
//				isHasRole=true;
//			}
//		}
//			if (isHasRole) {
//				return "listinstitutiontoadmin";
//			}
//
//		return "listinstitution";
//		方法2
		if(subject.hasRole("admin"))
		{
			return "listinstitutiontoadmin";
		}

		return "listinstitution";
	}

	/**
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value="/institutionlist" ,produces="application/json;charset=utf-8")
	@ResponseBody
	public String listinstitution(HttpServletRequest req){
		JsonResult jsonResult=new JsonResult();
		Subject subject = SecurityUtils.getSubject();
		Map<String,Object> resMap;
		String name=req.getParameter("name");
		String pageNum=req.getParameter("pageNum");
		String pageSize=req.getParameter("pageSize");
		String isRead=req.getParameter("isRead");
		if(isRead.equals("已读")){
			isRead="1";
		}
		else if(isRead.equals("未读")){
			isRead="0";
		}
		if(subject.hasRole("admin"))
		{
			 resMap=institutionService.selectAllInstitutions(name,pageSize,pageNum);
			resMap.put("role","0");
		}
		else
		{
			Users user=(Users) subject.getSession().getAttribute("user");
			int  _userid=user.getId();
			 resMap=institutionService.selectInstitutionsById(_userid,name,isRead,pageSize,pageNum);
			resMap.put("role","1");
		}

		jsonResult.setData(resMap);
		jsonResult.setCode("0000000");
		jsonResult.setMsg("success");
		return jsonResult.toJson();
	}

	/**
	 *
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("detailinstitution.do")
	public String detailinstitution(HttpServletRequest httpServletRequest,Model model){
		String id=httpServletRequest.getParameter("id");
		int idnew=Integer.valueOf(id);
		Map institutions=institutionService.selectByPrimaryKey(idnew);
		model.addAttribute("insLists",institutions);
		return "detailinstitution";
	}
	@RequestMapping("deleteinstitution.do")
	public String deleteinstitution(String id){
		int idnew=Integer.valueOf(id);
		institutionService.deleteByPrimaryKey(idnew);
		return "listinstitutiontoadmin";
	}

	/**
	 *
	 * @param req
	 * @return
	 */
	@RequestMapping(value="deleteAllInstitution.do" ,method = RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteAllInstitution(HttpServletRequest req){
		JsonResult jsonResult=new JsonResult();
		String idss=req.getParameter("ids");
		List<String> list = Arrays.asList(idss.split(","));
		try{
		institutionService.delectInstitutionByIds(list);
		jsonResult.setCode("111111");
		jsonResult.setMsg("成功删除");
		}
		catch(Exception e){
			e.getStackTrace();
		}
		return JSON.toJSONString(jsonResult);
	}
	@RequestMapping(value="/modifyinstitutionView.do")
	public String updateinstitutionView(HttpServletRequest request){
		int idnew = Integer.parseInt(request.getParameter("id"));
		Map institutions=institutionService.selectByPrimaryKey(idnew);
		request.setAttribute("updateDetail",institutions);
		return "updateinstitution";
	}
	@RequestMapping("modifyinstitution.do")
	public String updateinstitution(Institution ins){
		institutionService.updateSelective(ins);
		return "listinstitutiontoadmin";

	}
	@RequestMapping("/addRapAndRapMsg")
	public String addRapAndRapMsg(String userid,String type, String content)
	{
		//将用户id字符串解析为数组
		String[] userIdList=userid.split(",");
		institutionService.addRapAndRapMsg(userIdList,type,content);
		return "listinstitutiontoadmin";
	}
	@RequestMapping("/isReadinstitution.do")
	public void  isreadinstitution(HttpServletRequest req){
		String ids=req.getParameter("msgid");
		Integer idnew=Integer.valueOf(ids);
		institutionService.setIsReadStatus(idnew);

	}



}
