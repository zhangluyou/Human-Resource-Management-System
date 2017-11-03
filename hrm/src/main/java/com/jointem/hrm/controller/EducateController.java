
package com.jointem.hrm.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointem.hrm.common.JsonResult;
import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.Message;
import com.jointem.hrm.entity.Page;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.EducateService;
import com.jointem.hrm.service.MessageServic;
import com.jointem.hrm.service.UserService;
import com.jointem.hrm.vo.Column;

@Controller
@RequestMapping("/educate")
public class EducateController {
	@Autowired
	EducateService educateService;
	@Autowired
	UserService userServic;	
	@Autowired
	MessageServic messageServic;
	public String findEducate(int id) {
		return "index";
	}
/**
	 * 添加培训计划，点击保存后跳转至显示添加的信息详细界面
	 * @param name
	 * @param purpose
	 * @param begintime
	 * @param endtime
	 * @param datum
	 * @param teacher
	 * @param student
	 * @param model
	 * @param flag
	 * @return detaileducate
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:addEducate")
	@RequestMapping("/addEducate.do") 
	public String addEducate(String name, String purpose, String begintime, String endtime, String datum,
			String teacher, String student, Model model,String flag) throws ParseException {
		Educate educate = new Educate();
		educate.setName(name);
		educate.setPurpose(purpose);
		educate.setDatum(datum);
		educate.setTeacher(teacher);
		educate.setStudent(student);
		educate.setCreatetime(new Date());
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat monthFormat = new SimpleDateFormat("yyyy-MM");
		Date data = dateFormat.parse(begintime);
		String month = monthFormat.format(data);
		educate.setBegintime(data);
		educate.setMonth(month);
		Date date = dateFormat.parse(endtime);
		educate.setEndtime(date);
		educate.setEducate(new Byte("0"));	
		educateService.saveEducate(educate);
		Long eid1=educate.getId();
		int eid=eid1.intValue();
		String[] names=student.split(",");
		educateService.insertUserByName(names,eid);  //通过用户名向中间表添加educate的学员
		model.addAttribute("educate", educate);	 
		model.addAttribute("flag", flag);
		return "detaileducate";
	}

	/**
	 * 链接到addeducate.jsp界面,培训计划填写界面
	 * @param model
	 * @param request
	 * @param educate
	 * @return
	 */
	@RequiresPermissions("educate:addEducate")
	@RequestMapping("/educateView")  
	public String Educate(Model model,HttpServletRequest request,Educate educate) {

		List<Users> list=userServic.getAllUsers();
	    String students=null;
	    model.addAttribute("list",list);
	    model.addAttribute("educate",educate);
		model.addAttribute("students",students);
		return "addeducate";
	}
	
	/**
	 *  链接到listeducat.jsp界面，查看计划
	 * @param dModel
	 * @return listeducate
	 */
	@RequiresPermissions("educate:listEducateView")
	@RequestMapping("/listEducateView") 
	public String listEducate(Model dModel,String pageNum) {
		int pagesize=8;
		int pageNum1 = 1;
		if(pageNum!=null&&!"".equals(pageNum))
			pageNum1=Integer.parseInt(pageNum);
		Page<Educate> page = ((EducateService) educateService).getAllEducate(pageNum1,pagesize);
		page.setPageNum(pageNum1);
		dModel.addAttribute("pages", page);
		return "listeducate";
	}
   
	/**
	 * 由计划查看或记录查看界面链接到detaileducate.jsp界面，查看详细信息,用flag来区别两个页面的详细信息
	 * @param dModel
	 * @param request
	 * @return detaileducate
	 */
	@RequiresPermissions("educate:detailEducate")
	@RequestMapping("/detaileducate.do")    // 
	public String detaileducate(Model dModel,int id,int flag,String pageNum) {
		int pageNum1=Integer.parseInt(pageNum);
		Educate educate = new Educate();
		educate = educateService.findEducate(id);
		dModel.addAttribute("pageNum",pageNum1);
		dModel.addAttribute("educate", educate);
		dModel.addAttribute("flag", flag);
		return "detaileducate";
	}
 
/**
 * 删除计划	
 * @param model
 * @param request
 * @return listeducate
 */
	@RequiresPermissions("educate:deleteEducatePlan")
	@RequestMapping("/deletEducate.do") // 删除计划
	public String deleteeducate(Model model, HttpServletRequest request,String pageNum) {
		int startNum=1;
		int pagesize=8;
		Page<Educate> educates =new Page<>();
		if (pageNum!=null&&!"".equals(pageNum)) 
			startNum=Integer.parseInt(pageNum);
		int id = Integer.parseInt(request.getParameter("id"));
		Educate educate=educateService.findEducate(id);
		if (educate.getIsreviewed()!=1) {
			educates= educateService.deleteEducate(id, startNum, pagesize);	
		}else {
			educates=educateService.getAllEducate(startNum, pagesize);
		}
		educates.setPageNum(startNum);	
		model.addAttribute("pages", educates);
		return "listeducate";
	}

	/**
	 * 删除培训记录
	 * @param model
	 * @param request
	 * @return skimEducateSummarize
	 */
	@RequiresPermissions("educate:deleteEducateSummarize")
	@RequestMapping("/deletEducateSummarize")        //删除培训记录
	public String deleteeducateSummarize(Model model, HttpServletRequest request,String pageNum) {
        int startnum=1;
        int pagesize=8;
        if (pageNum!=null&&!"".equals(pageNum)) 
			startnum=Integer.parseInt(pageNum);
		int id = Integer.parseInt(request.getParameter("id"));
		educateService.deleteEducate(id, startnum,pagesize);
		Page<Educate> educates = educateService.getAllFinishedEducate(startnum,pagesize);
		educates.setPageNum(startnum);
		model.addAttribute("pages", educates);
		return "skimEducateSummarize";
	}

	/**
	 * 更新界面。根据flag的值呈现不同的更新界面，flag=5是跳转到修改信息界面，flag=6是填写培训总结界面
	 * @param model
	 * @param request
	 * @return 
	 */
	@RequiresPermissions("educate:updateEducateView")
	@RequestMapping("/updateView")   
	public String updatelist(Model model, HttpServletRequest request,String pageNum) {
		int id = Integer.parseInt(request.getParameter("id"));
		Educate educate = educateService.findEducate(id);
		model.addAttribute("educate", educate);
		List<Users>users=userServic.getAllUsers();
		String flag = request.getParameter("flag");
		model.addAttribute("pageNum",pageNum);
		if ("5".equals(flag)) {
			model.addAttribute("list",users);
			model.addAttribute("flag", flag);
			return "updateEducateInfo";
		} else {
			return "updateeducate";
		}
	}

	/**
	 *  修改信息或填写培训总结，以flag标识flag=5,修改信息，flag=6填写培训记录
	 * @param request
	 * @param model
	 * @param id
	 * @param student
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:updateSummarize")
	@RequestMapping("/updateeducate.do") 
	public String updateSummarize(HttpServletRequest request, Model model,int id,String student,String pageNum) throws ParseException {
		int startnum=1;
		int pagesize=8;
				if (pageNum!=null&&!"".equals(pageNum))
					startnum=Integer.parseInt(pageNum);
		String flag = request.getParameter("flag");
		Educate educate = educateService.findEducate(id);
		if (!"5".equals(flag)) {     //填写培训总结
			int effectFlag=0;
			String effect = request.getParameter("effect");
			String summarize = request.getParameter("summarize");
			effectFlag=educateService.getEffectFlagByEffect(effect); //通过effect获取EffectFlag            
			educate.setEffect(effect);
			educate.setSummarize(summarize);
			educate.setEffectFlag(effectFlag);
			educateService.updateEducate(educate);
			educateService.updateEducateFlag(id);
		} else {                  //修改计划信息
			String begintime = null, endtime = null;
			begintime = request.getParameter("begintime");
			endtime = request.getParameter("endtime");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date data = dateFormat.parse(begintime);
			educate.setBegintime(data);
			Date date = dateFormat.parse(endtime);
			educate.setEndtime(date);
			educate.setName(request.getParameter("name"));
			educate.setPurpose(request.getParameter("purpose"));
			educate.setTeacher(request.getParameter("teacher"));
			educate.setStudent(request.getParameter("student"));
			educate.setDatum(request.getParameter("datum"));
			educateService.updateEducate(educate,id,educate.getStudent());
			
		}
		Page<Educate> page = educateService.getAllEducate(startnum,pagesize);
		page.setPageNum(startnum);
		model.addAttribute("pages", page);
		return "listeducate";
	}
	
	/**
	 *  跳转到浏览培训记录界面
	 * @param model
	 * @param pageNum
	 * @return skimEducateSummarize
	 */
	@RequiresPermissions("educate:skimSummarizeDetail")
	@RequestMapping("/skimSummarizeView") 
	public String skimSummarize(Model model,String pageNum) {
		int pagesize=8;
		int pageNum1 = 1;
		if(pageNum!=null&&!"".equals(pageNum))
			pageNum1=Integer.parseInt(pageNum);
		Page<Educate> educates = educateService.getAllFinishedEducate(pageNum1,pagesize);
		model.addAttribute("pages", educates);
		return "skimEducateSummarize";
	}
	
	
	/**
	 * 跳转到选择学员界面
	 * @param model
	 * @param educate
	 * @param students
	 * @param request
	 * @param name
	 * @param teacher
	 * @return  chooseStudent
	 */
	@RequiresPermissions("educate:addstudentView")
	@RequestMapping("/addStudentView")    
	public String addStudent(Model model,Educate educate,String students,HttpServletRequest request,String name,String teacher)
	{
		educate.setName(request.getParameter("name"));
		educate.setPurpose(request.getParameter("purpose"));
		educate.setStudent(request.getParameter("student"));
		educate.setTeacher(request.getParameter("teacher"));
		educate.setDatum(request.getParameter("datum"));
		List<Users> list=userServic.getAllUsers();
		String st[]=students.split(",");
		model.addAttribute("students",students);
		model.addAttribute("st",st);
		model.addAttribute("educate",educate);
		model.addAttribute("list",list);
		return "chooseStudent";
	}
	
	/**
	 *  选择完参加培训的学员后跳转到填写计划界面
	 * @param model
	 * @param request
	 * @param name1
	 * @param teacher1
	 * @param datum1
	 * @param purpose1
	 * @param begintime1
	 * @param endtime1
	 * @param flag
	 * @return addEducate
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:finishChooseStudent")
	@RequestMapping("/chooseStudents")      
	public String getChooseStudents(Model model,HttpServletRequest request,String name1,String teacher1,String datum1,
			String purpose1,Date begintime1,Date endtime1,String flag) throws ParseException{
		String[]names=request.getParameterValues("nameCheckbox");
		
		String students="";
		Educate educate=new Educate();
		List<Users> studentlist=userServic.getAllUsers();
		if (names==null)
		{
			students="";
		}
		else{
			int index=0;
			int size=names.length;
	    for (String string : names) {
	    	index++;
	    	students+=index==size?string:string+",";
	
		}
	    }
		
		educate.setName(name1); 
		educate.setTeacher(teacher1);
		educate.setDatum(datum1);
		educate.setPurpose(purpose1);
		educate.setBegintime(begintime1);
		educate.setEndtime(endtime1);
		educate.setStudent(students);
		model.addAttribute("students",students);
        model.addAttribute("list",studentlist);
		if ("2".equals(flag)) {
			model.addAttribute("educate",educate);
        	return "addeducate";
		}
       
        else {
        	Long id=Long.parseLong(request.getParameter("id1")); 
        	educate.setId(id);
        	
    		for (String s : names) {
    			Users users=userServic.getByUsername(s);
    			int uid=users.getId();
    			educateService.insertEducateUser(uid, id.intValue());
    		}
        	model.addAttribute("st",names);
        	model.addAttribute("educate",educate);
        	return "updateEducateInfo";
		}
	}
	/**
	 * 获取学员列表
	 * @return
	 */
	@RequiresPermissions("educate:getAllStudentList")
	@RequestMapping("/getStudentsList")
	@ResponseBody
	public List<Users> getStudentsList()
	{
		List<Users> userList=null;
		userList=userServic.getAllUsers();
		return userList;
	}
	

	/**
	 * 查看参加培训的学生列表，通过点击detailEducate.jsp界面的”点击查看“超链接”查看
	 * @param model
	 * @param students
	 * @param flag
	 * @param request
	 * @param id
	 * @return listStudents
	 */
	@RequiresPermissions("educate:liststudent")
	@RequestMapping("/listStudent.do")         
	public String listStudent(Model model,String students,String flag,HttpServletRequest request,String id,String pageNum){
		int pageNum1=1;
		if (pageNum!=null&&!"".equals(pageNum)) {
			pageNum1=Integer.parseInt(pageNum);
		}
		
         int ids=Integer.parseInt(id);
		 Educate educate=educateService.findEducate(ids);
		 students=educate.getStudent();
		 String std[]=students.split(",");
		List<Users> liststudents=educateService.getEducateStudents(ids);
		 model.addAttribute("pageNum",pageNum1);
		 model.addAttribute("flag",flag);
		 model.addAttribute("id",ids);
		model.addAttribute("students",liststudents);
		return "liststudents";	
	}
	
	/**
	 * 根据条件查询，查询结果分页显示       
	 * @param begintime
	 * @param name
	 * @param pageNum
	 * @param request
	 * @param model
	 * @return listeducate
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:conditionSelect")
	@RequestMapping("/listSeclct.do")      
	public String listSelect(String begintime,String name,int pageNum,HttpServletRequest request, Model model) throws ParseException {
		int pagenmu=1;
		int pagesize=8;
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = dateFormat.parse("2000-01-01");
		if (begintime != null && !begintime.equals(""))
			date1 = dateFormat.parse(begintime);
		if(pageNum!=0)
			pagenmu=pageNum;
		if(request.getAttribute("pagesize")!=null)
			pagesize=(int) request.getAttribute("pagesize");
		map.put("begintime", date1);
		map.put("name", name);
		map.put("pagenum", pagenmu);
		map.put("startnum", (pagenmu-1)*pagesize);
		map.put("pagesize",pagesize);
		String t1 = dateFormat.format(date1);
		model.addAttribute("begintime", t1);
		model.addAttribute("name", name);
		Page<Educate> page = educateService.queryByCondition(map);
		model.addAttribute("pages", page);
		return "listeducate";
	}
	
	
	/**
	 * 查看个人有关的培训信息
	 * @param model
	 * @param ename
	 * @param pageNum
	 * @param dropSelectValue
	 * @return
	 */
	@RequiresPermissions("educate:studentEducateRecordView")
	@RequestMapping("studentEducateRecordView")
	public String studentRecordView(Model model,String ename,String pageNum,String dropSelectValue)
	{
		int pagenum=0;
		if(pageNum!=null)
			pagenum=Integer.parseInt(pageNum);
		if(ename==null)
			ename="";
		int pagesize=0;
		if(dropSelectValue==null||dropSelectValue=="")
		{
			dropSelectValue="待确认";
		}
		Page<Users> pages=educateService.getEducateRecord(ename, pagenum, dropSelectValue);
		model.addAttribute("pages",pages);
		model.addAttribute("ename",ename);
		model.addAttribute("dropSelectValue", dropSelectValue);
		return"studenteducaterecord";
	}

	/**
	 * 更新确认参加状态
	 * @param sid
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:updateConfirm")
	@RequestMapping("update.do")
	public String updeteDo(String sid,Model model) throws ParseException
	{
		Subject subject=SecurityUtils.getSubject();
		Users users=(Users) subject.getSession().getAttribute("user");
		int uid=users.getId();
		int id=Integer.parseInt(sid);
		Page<Users>pages=new Page<>();
		Byte isjoin=new Byte((byte)1);
		pages=educateService.updateEducateRecord(uid, id, isjoin);
		model.addAttribute("pages",pages);
		model.addAttribute("dropSelectValue","待确认");
		return "studenteducaterecord";
	}
	
	/**
	 * 取消参加
	 * @param sid1
	 * @param model
	 * @return
	 * @throws ParseException
	 */
	@RequiresPermissions("educate:cancelJoin")
	@RequestMapping("cancel.do")
	public String cancelDo(String sid1,Model model) throws ParseException
	{
		Subject subject=SecurityUtils.getSubject();
		Users users=(Users) subject.getSession().getAttribute("user");
		int uid=users.getId();
		int id=Integer.parseInt(sid1);
		Page<Users>pages=new Page<>();
		Byte isjoin=new Byte((byte)0);
		pages=educateService.updateEducateRecord(uid, id, isjoin);
		model.addAttribute("pages",pages);
		model.addAttribute("dropSelectValue","待确认");
		return "studenteducaterecord";
	}
	
	
	/**
	 * 跳转至审核界面  
	 * @param model
	 * @return
	 */
	@RequiresPermissions("educate:lookThroughView")
	@RequestMapping("lookThroughView")
	public String lookthroughview(Model model,String pageNum){
		int pagesize=3;
		int pageNum1 = 1;
		if(pageNum!=null&&!"".equals(pageNum))
			pageNum1=Integer.parseInt(pageNum);
		Page<Educate> page=educateService.getUnLookThroughEducate(pageNum1, pagesize);
		model.addAttribute("pages", page);
		return "lookthrough";
	}
	
	/**
	 *去审核
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("educate:toLookThrough")
	@RequestMapping("/lookthrough.do")
	public String check(Model model,int id,String pageNum){
		int startnum=1;
		int pagesize=3;
		int uid=0;
		if(pageNum!=null&&!"".equals(pageNum)){
			startnum=Integer.parseInt(pageNum);
		}
		Subject subject = SecurityUtils.getSubject();
        Users user= (Users) subject.getSession().getAttribute("user");
        int currid=user.getId();
        if (currid==109) {          //控制特定的人或角色才可审核信息
		educateService.toCheck(id);      //去审核    
		//生成通知消息
		 List<Users> users=educateService.getEducateStudents(id);
		 educateService.createMessage(users,id);
        }
		 Page<Educate> page = ((EducateService) educateService).getUnLookThroughEducate(startnum,pagesize);  //获取待审核的记录
		page.setPageNum(startnum);
		model.addAttribute("pages", page);
		return "lookthrough";
	}
	
	/**
	 * 取消审核
	 * @param model
	 * @param id
	 * @param pageNum
	 * @param remark
	 * @return
	 */
	@RequiresPermissions("educate:cancelLookThrough")
	@RequestMapping("/cancellookthrough.do")
	 public String cancelCheck(Model model,int id,String pageNum,String remark){
		int startnum=1;
		int pagesize=3;
		if(pageNum!=null&&!"".equals(pageNum)){
			startnum=Integer.parseInt(pageNum);
		}
		educateService.cancelCheck(id);      //取消审核
		messageServic.readCheckMsg(id);        //消息设为已读
		educateService.addRemark(id,remark);      //添加备注
		Page<Educate> page = ((EducateService) educateService).getUnLookThroughEducate(startnum,pagesize);  //获取待审核的记录
		page.setPageNum(startnum);
		model.addAttribute("pages", page);
		return "lookthrough";
	}
	
	/**
	 * 提交审核
	 * @param model
	 * @param id
	 * @return
	 */
	@RequiresPermissions("educate:submitLookThrough")
	@RequestMapping("/submitLookthrough")
	public String submitlookthrough(Model model,int id,String pageNum){
		int startnum=1;
		int pagesize=8;
		Educate educate=educateService.findEducate(id);
		int isCheck=educate.getIsreviewed();
		String content=educate.getName();
		Date sendtime=new Date();
		if(pageNum!=null&&!"".equals(pageNum))
			startnum=Integer.parseInt(pageNum);
		if (isCheck==0||isCheck==3) {
			educateService.submitlookthrough(id);  //改变审核状态为待审核
			Message message=new Message();
			message.setSender_id(id);
			message.setMessage_type(0);
			message.setIsRead(false);
			message.setContent(content);
			message.setReceiver_id(109);
			message.setSend_time(sendtime);
			educateService.addMessage(message);      //插入消息
		}	
		Page<Educate> page = ((EducateService) educateService).getAllEducate(startnum, pagesize);  //获取所有未完成计划
		page.setPageNum(startnum);
		model.addAttribute("pages", page);
		return "listeducate";
	}

    /**
     * 统计图表
     * @return
     */
	@RequiresPermissions("educate:chartView")
	@RequestMapping("/chartView")
	public  String chartView(){
		return "educateChart";
	}
	
	@RequiresPermissions("educate:chartView")
	@RequestMapping(value = "/getData", produces="application/json;charset=utf-8")
	@ResponseBody
	public  String chartView(String date){
		JsonResult jsonResult=new JsonResult();
		List<Column> list =  educateService.queryMonthCount(date);
		jsonResult.setData(list);
		return jsonResult.toJson();
		}
	
	@RequiresPermissions("educate:batchDeleteEducate")
	@RequestMapping(value="/deleteAlleducate.do",method=RequestMethod.POST)
	public @ResponseBody String deleteAlleducate(Model dModel,String pageNum,String ids)
	{
		educateService.deleteAlleducate(ids);
			int pagesize=8;
			int pageNum1 = 1;
			if(pageNum!=null&&!"".equals(pageNum))
				pageNum1=Integer.parseInt(pageNum);
			Page<Educate> page = ((EducateService) educateService).getAllEducate(pageNum1,pagesize);
			page.setPageNum(pageNum1);
			JsonResult js=new JsonResult();
			js.setCode("123");
			js.setMsg("ok");
			js.setData(page);
			return js.toJson();

		
	}	
}

