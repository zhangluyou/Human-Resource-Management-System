package com.jointem.hrm.controller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointem.hrm.common.JsonResult;
import com.jointem.hrm.entity.Department;
import com.jointem.hrm.entity.File;
import com.jointem.hrm.entity.Position;
import com.jointem.hrm.service.DepartmentService;
import com.jointem.hrm.service.FileService;
import com.jointem.hrm.service.PositionService;

@Controller
@RequestMapping("/department")
public class departmentController {
	@Autowired
	DepartmentService departmentService;
	@Autowired
	PositionService positionService;
	@Autowired
	FileService fileService;
	@RequestMapping("/departmentView")
	public String departmnet() {
		return "departmentTree";
	}
	@RequestMapping("/jobManage")
	public String jobManage() {
		return "jobManage";
	}
	@RequestMapping("/test")
	public String deprtmnet() {
		return "test";
	}
	@RequestMapping(value = "/getTree", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getTree() {
		JsonResult result = new JsonResult();
		result.setData(departmentService.selectAllDepartment());
		return result.toJson();
	}
	@RequestMapping(value = "/getPosition", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getposition(int did) {
		JsonResult result = new JsonResult();
		List<Position> positions=positionService.selectPositionByDid(did);
		result.setData(positions);
		return result.toJson();
	}
	@RequestMapping("/updatedepartment.do")
	@ResponseBody
	public void  update(String departmentName,int id) {
		Department department=new Department();

		department.setId(id);
		department.setDepartmentName(departmentName);
		departmentService.setDepartment(department);
	}
	@RequestMapping(value="/insertdepartment.do")
	@ResponseBody
	public void  insertdepartment(String departmentName,int pId) {
		Department department=new Department();
		department.setpId(pId);
		department.setDepartmentName(departmentName);
		departmentService.insertDepartment(department);
	}
	@RequestMapping(value="/delete.do")
	@ResponseBody
	public void  deleteDepartment(int id) {
		departmentService.deleteDepartment(id);
	}
	@RequestMapping(value="/deletePosition.do")
	@ResponseBody
	public void  deletePosition(int id) {
		positionService.deletePosition(id);
	}
	@RequestMapping(value="/insertPosition.do")
	@ResponseBody
	public void  insertPosition(int did,String positionName,int rankNum,int fileNum) {
		Position p=new Position();
		p.setDid(did);
		p.setPositionName(positionName);
		p.setRankNum(rankNum);
		p.setFileNum(fileNum);
		positionService.insertPosition(p);
	}
	@RequestMapping(value = "/getfile", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getfile() {
		JsonResult result = new JsonResult();
		List<File> files=fileService.selectAllFile();
		result.setData(files);
		return result.toJson();
	}
	@RequestMapping(value = "/getFileByIds", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getfileByIds(int rankNum,int fileNum) {
		JsonResult result = new JsonResult();
		File files=fileService.selectFileSalaryByIds(rankNum, fileNum);
		result.setData(files);
		return result.toJson();
	}
	@RequestMapping(value="/insertFile.do")
	@ResponseBody
	public boolean  inserFile(int salary,int rankNum,int fileNum) {
		File file=new File();
		file.setFileNum(fileNum);
		file.setRankNum(rankNum);
		file.setSalary(salary);
		return fileService.insertFile(file);
	}
	@RequestMapping(value="/setFile.do")
	@ResponseBody
	public boolean setFile(int salary,int rankNum,int fileNum) {
		File file=new File();
		file.setFileNum(fileNum);
		file.setRankNum(rankNum);
		file.setSalary(salary);
		return fileService.setFile(file);
	}
	@RequestMapping(value="/delFile.do")
	@ResponseBody
	public void delFile(int rankNum,int fileNum) {
		fileService.deleteFileByIds(rankNum, fileNum);
	}
	
	@RequestMapping(value = "/selectRankNum", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectRankNum() {
		JsonResult jsonResult=new JsonResult();
		List<File> files=fileService.selectRankNum();
		jsonResult.setData(files);
		return jsonResult.toJson();
	}
	
	@RequestMapping(value = "/selectFileNum", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectFileNum( int num) {
		JsonResult jsonResult=new JsonResult();
		List<File> files=fileService.selectFileNum(num);
		jsonResult.setData(files);
		return jsonResult.toJson();
	}

}
