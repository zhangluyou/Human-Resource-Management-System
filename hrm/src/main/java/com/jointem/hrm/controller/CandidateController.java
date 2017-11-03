package com.jointem.hrm.controller;

import com.jointem.hrm.entity.Candidate;
import com.jointem.hrm.entity.Pages;
import com.jointem.hrm.service.CandidateService;
import javax.annotation.Resource;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/candidate")
public class CandidateController {

	@Resource
	private CandidateService candidateService;
	private int pageSize = 10;

	@RequiresPermissions("candidate:toSave")
	@RequestMapping("/toSave")
	public String toSave() {
		return "addcandidate";
	}

	@RequestMapping(value = "/save.do", method = RequestMethod.POST)
	@ResponseBody
	public String save(@RequestBody Candidate candidate) {
		candidateService.addCandidate(candidate);
		return "添加成功！";
	}
	
	@RequiresPermissions("candidate:getCandidate")
	@RequestMapping("/getcandidate.do/{id}")
	public String getDetails(@PathVariable Integer id, Model model){
		Candidate candidate = candidateService.queryCandidate(id);
		model.addAttribute("candidate", candidate);
		return "resume";	
	}

	@RequiresPermissions("candidate:deleteCandidate")
	@RequestMapping("/delete.do/{id}")
	public String delete(@PathVariable Integer id, String job, String sort, Integer pageNum, Model model) {
		System.out.println(id);
		candidateService.removeCandidate(id);
		return getList(job, sort, pageNum, model);
	}

	@RequiresPermissions("candidate:stock")
	@RequestMapping("/stock.do/{id}")
	public String stock(@PathVariable Integer id, String job, String sort, Integer pageNum, Model model) {
		candidateService.joinStock(id);
		return getList(job, sort, pageNum, model);
	}

    @RequiresPermissions("candidate:getList")
	@RequestMapping("/getlist")
	public String getList(@RequestParam(value = "job", required = false) String job, String sort, Integer pageNum,
			Model model) {
		if (sort == null) {
			sort = "desc";
		}
		if (pageNum == null) {
			pageNum = 1;
		}
		Pages page = candidateService.queryCandidateList(job, sort, pageNum, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("job", job);
		model.addAttribute("sort", sort);
		return "candidates";
	}

}
