package com.jointem.hrm.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.aspectj.apache.bcel.generic.MULTIANEWARRAY;
import org.nfunk.jep.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jointem.hrm.common.JsonResult;
import com.jointem.hrm.entity.Page;
import com.jointem.hrm.entity.Stipend;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.StipendService;
import com.jointem.hrm.utils.JSONUtil;

import net.sf.ehcache.statistics.sampled.SampledCacheStatisticsImpl;

@Controller
@RequestMapping("/stipend")
public class StipendController {

	
	@Autowired
	StipendService stipendService;
	
	/**
	 * 跳转至添加薪金信息界面
	 * @return
	 */
	/*@RequiresPermissions("stipend:addStipendView")
	@RequestMapping("/addStipendView")
	public String addStipendview(){
		return "addstipend";
	}*/
	

	/*@RequiresPermissions("stipend:addStipendView")
	@RequestMapping("/modifystipend.do")
	public String addstipend(Model model,String position,float basic,float level,float eat,float house,String granttime,float phone_call,float traffic,float secret
			){
         Stipend stipend=new Stipend();
         stipend.setPosition(position);
         stipend.setBasic(basic);
         stipend.setEat(eat);
         stipend.setHouse(house);
         stipend.setGranttime(granttime);
         stipend.setLevel(level);
         stipend.setPhone_call(phone_call);
         stipend.setTraffic(traffic);
         stipend.setSecret(secret);
         Float fixed_sum=basic+level+eat+house+phone_call+traffic+secret;
         stipend.setFixed_sum(fixed_sum);
         stipendService.saveFixedStipend(stipend);
         return "addstipend";
		
	}*/

	@RequestMapping("/edittable")
    public String testBoot(){
		return "edittable";
	}	
	
	@RequestMapping(value="/getSalaryRecord",produces="text/json;charset=UTF-8" )
	@ResponseBody
	public String getSalaryRecord(String selectmonth){
		String month;
		Subject subject = SecurityUtils.getSubject();
        Users user= (Users) subject.getSession().getAttribute("user");
        int currid=user.getId();
		if (selectmonth!=null) {
			month=selectmonth;
		}
		else{
			  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
			     month=dateFormat.format(new java.util.Date());
		}
		JsonResult result= new JsonResult(stipendService.getSalaryRecordList(currid,month));
		return result.toJson();
	}
	
	@RequestMapping(value="/getJson",produces="text/json;charset=UTF-8" )
	@ResponseBody
	public String getStipendJson(String selectmonth,String expression) throws Exception{
		String month;
		String exp=expression;
		System.out.println(exp);
		if (selectmonth!=null) {
			month=selectmonth;
		}
		else{
			  SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM");
			     month=dateFormat.format(new java.util.Date());
		}
		stipendService.saveExpression(month,expression);
		JsonResult result= new JsonResult(stipendService.findAllStipendByMonth(month));
		return result.toJson();
	}
	
    @RequestMapping("/getDeleteStipend")
    @ResponseBody
    public String getDeleteStipend(Stipend stipend){		
    stipendService.deleteStipendRecord(stipend.getEmp_id(),stipend.getGranttime());
    return "edittable";
    }
    
    @RequestMapping("/saveStipend") 
    public String saveStipend(Stipend stipend) throws ParseException {
    	stipendService.saveAllStipendInfo(stipend);//保存
    	stipendService.accountStipend(stipend);  //结算
    	stipendService.savaSalaryRecord(stipend); //保存记录
    	return "edittable";
    }
    
    @RequestMapping("/listStipendView")
    public String listStipendView(){
    	return "liststipend";
    }
}
