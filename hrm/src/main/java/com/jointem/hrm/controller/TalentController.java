package com.jointem.hrm.controller;

import com.jointem.hrm.entity.Pages;
import com.jointem.hrm.entity.Talent;
import com.jointem.hrm.service.TalentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;


@Controller
@RequestMapping(value="/talent")
public class TalentController {
	
	@Resource
	TalentService talentService;
	int pageSize  = 10;
	
	@RequiresPermissions("talent:delTalent")
	@RequestMapping(value = "/deltalent.do/{id}")
	public String del(@PathVariable Integer id, String job,String sort,Integer pageNum, Model model){
		talentService.remoTalentPool(id);
		return getList(job, sort, pageNum, model);
	}
	
	@RequestMapping(value = "/getdetails/{id}")
	public String getDetails(@PathVariable Integer id, Model model){
		Talent talent = talentService.getTalent(id);
		model.addAttribute("talent", talent);
		return "resume";
		
	}

	
	@RequiresPermissions("talent:getList")
	@RequestMapping(value="/getlist")
	public String getList(@RequestParam(value = "job", required = false) String job, String sort,Integer pageNum, 
			Model model){
		if (sort == null) {
			sort = "desc";
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		Pages page = talentService.queryTalentList(job, sort, pageNum, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("job", job);
		model.addAttribute("sort", sort);
		return "listtalent";
	}
 	
//	@RequiresPermissions("talent:employment")
//	@RequestMapping("/employment/{id}")
//	public String employment(@PathVariable Integer id,HttpServletRequest req){
//		Talent talent = talentService.getTalent(id);
//		String name = talent.getName();
//		Date birthday = talent.getBirthday();
//		Integer sex = talent.getSex();
//		req.setAttribute("name", name);
//		req.setAttribute("birthday", birthday);
//		req.setAttribute("sex", sex);
//		return "forward:/users/toAddUser";
//	}
	@RequiresPermissions("talent:employment")
	@RequestMapping("/employment.do")
	public String employment(HttpServletRequest req){
		String ids=req.getParameter("id");
		Talent talent = talentService.getTalent(Integer.valueOf(ids));
		String name = talent.getName();
		Date birthday = talent.getBirthday();
		Integer sex = talent.getSex();
		req.setAttribute("name", name);
		req.setAttribute("talentid",ids);
		req.setAttribute("birthday", birthday);
		req.setAttribute("sex", sex);
	//	return "forward:/users/toAddUser";
	return "adduser";
	}
}
