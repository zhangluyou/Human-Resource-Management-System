package com.jointem.hrm.service.impl;

import com.jointem.hrm.dao.StipendDao;
import com.jointem.hrm.dao.UsersDao;
import com.jointem.hrm.entity.Attendence;
import com.jointem.hrm.entity.Stipend;
import com.jointem.hrm.service.AttendanceService;
import com.jointem.hrm.service.StipendService;
import com.jointem.hrm.vo.DayAndStatus;
import org.nfunk.jep.JEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("StipendService")
public class StipendServiceImp implements StipendService {

	@Autowired
	StipendDao stipendDao;
	@Autowired
	UsersDao usersDao;
	@Autowired
	AttendanceService attendanceService;
	/**
	 * 保存固定部分薪金信息
	 * 
	 */
	@Override
	public void saveFixedStipend(Stipend stipend) { 
		  stipend.setFixed_sum(fixedSum(stipend));
          if (getFixedPart(stipend.getEmp_id())!=null) {
			stipendDao.updateFixedStipend(stipend);
		}else{
		stipendDao.savaFixedStipend(stipend);
		}
	}
 /**
  * 获取固定部分薪金信息
  */
	@Override
	public Stipend getFixedPart(int id) {
		
		return stipendDao.getFixedPart(id);
	}
    /**
     * 保存浮动部分薪金信息
     */
	
	@Override
	public void savaFloatStipend(Stipend stipend) {
        
		stipend.setFloat_sum(getFloatSum(stipend));
		if (getFloatPart(stipend.getEmp_id(), stipend.getGranttime())!=null) {
			stipendDao.udpateFloatStipend(stipend);
		}else{
		stipendDao.savaFloatStipend(stipend);
     	}
	}
	/**
	 * 固定部分之和
	 * @param stipend
	 * @return
	 */
	public Float fixedSum(Stipend stipend){
		Float fixed_sum= stipend.getBasic()+stipend.getEat()+stipend.getHouse()+stipend.getLevel()+
				stipend.getTraffic()+stipend.getSecret()+stipend.getPhone_call();
		return fixed_sum;
	}
	
	/**
	 * 浮动部分之和
	 * @param stipend
	 * @return
	 */
	public Float getFloatSum(Stipend stipend){
		Float  sum=stipend.getAward()+stipend.getOvertime_pay()+stipend.getEp_age()+
        		stipend.getCommission()+stipend.getBd_benefits()+stipend.getPerformance()+
				stipend.getDuty_everyDay()+stipend.getOther();
		return sum;
	}
	
	/**
	 * 社保之和
	 * @param stipend
	 * @return
	 */
	public Float getSocialSum(Stipend stipend){
		Float social_sum=stipend.getSocial_pension()+stipend.getSocial_medical()+stipend.getSocial_unemployment();
		return social_sum;
	}
	/**
	 * 计算补扣之和
	 * @param stipend
	 * @return
	 */
	public Float getTakeSum(Stipend stipend){
		Float take_sum=stipend.getTake_birth()+stipend.getTake_injury()+stipend.getTake_medical()+stipend.getTake_pension()
		+stipend.getTake_unemployment();
		return take_sum;
	}
	
	/**
	 * 税前扣款之和
	 * @param stipend
	 * @return
	 */
   public Float getCutSum(Stipend stipend){
		Float cut_sum=stipend.getPrivate_affairs()+
				stipend.getSick_leave()+
				stipend.getNeglect_work()+
				stipend.getEntry_leave_cut()+stipend.getArrive_late() +stipend.getOther_cut()+stipend.getHouse_pay()+
		        getTakeSum(stipend)+getSocialSum(stipend);
		return cut_sum;
   }
   
   /**
    * 保存税前扣款
    */
	@Override
	public void saveCutPay(Stipend stipend) {
		        stipend.setSocial_sum(getSocialSum(stipend));
		        stipend.setTake_sum(getTakeSum(stipend));
		        stipend.setCut_sum(getCutSum(stipend));
	  if (stipendDao.getCutPayPart(stipend.getEmp_id(),stipend.getGranttime())!=null) {      //判断是否数据库中存在该信息，有则更新无则添加
		stipendDao.updateCutPayStipend(stipend);
	}else{
		        stipendDao.saveCutPay(stipend);		
	}
	}

	/**
	 * 获取浮动部分薪金信息
	 */
	@Override
	public Stipend getFloatPart(int id,String granttime) {
		
		return stipendDao.getFloatPart(id,granttime);
	}
    
	/**
	 * 获取税前扣款部分薪金信息
	 */
	@Override
	public Stipend getCutPayPart(int id,String month) {
		
		return stipendDao.getCutPayPart(id,month);
	}
    
	/**
	 * 保存一条完整的薪金信息
	 * 
	 */
	@Override
	public void saveAllStipendInfo(Stipend stipend) {
		savaFloatStipend(stipend);
		saveFixedStipend(stipend);
		saveCutPay(stipend);
	}
 
 /**
  * 转化年月格式
  * @param month
  * @return
  */
	public String replaceMonthFormat(String month)
	{
		String newMonth="";
		newMonth=month.replace("-", "年")+"月";
		String cString=newMonth.substring(5,6);
		if (cString.equals("0")) {
		 newMonth=newMonth.substring(0,5)+newMonth.substring(6,8);
		}
		return newMonth;
	}
	
   /**
    * 获取特定月份的所有员工薪金信息
 * @throws Exception 
    */
	@Override
	public List<Stipend> findAllStipendByMonth(String month){
		List<Stipend> list = stipendDao.findAllStipendByMonth(month);
		Map<String, Integer>attendanceMap=new HashMap<String,Integer>();
		for(int i=0;i<list.size();i++){
			attendanceMap=getAttendanceByNameAndMonth(replaceMonthFormat(month), usersDao.selectUserByID(list.get(i).getEmp_id()).getName());
			if (attendanceMap==null) {
				continue;
			}else{
			list.get(i).setPrivate_leaveDay(attendanceMap.get("thing"));
			list.get(i).setSick_leaveDay(attendanceMap.get("sick"));
			list.get(i).setNeglect_workDay(attendanceMap.get("neglect"));
			list.get(i).setEntry_leaveDay(attendanceMap.get("entry"));
			list.get(i).setShould_attendance(attendanceMap.get("should_attendance"));
			list.get(i).setReal_attendance(attendanceMap.get("real_attendance"));		
			list.get(i).setLate_time(attendanceMap.get("late_time"));
			
			setAttendanceCut(list.get(i));
			}
		}
		return list;
	}

	/**
	 * 计算考勤对应的扣/奖
	 * @param stipend
	 */
	public void setAttendanceCut(Stipend stipend){
		int setThingsCut=20,setSickCut=20,setEntryLeavCut=20,setNeglectCut=20;
		if (stipend.getShould_attendance()==stipend.getReal_attendance()) {
			stipend.setDuty_everyDay((float)100);
		}else if (stipend.getReal_attendance()<stipend.getShould_attendance()) {
			stipend.setPrivate_affairs((float)stipend.getPrivate_leaveDay()*setThingsCut);
			stipend.setSick_leave((float)stipend.getSick_leaveDay()*setSickCut);
			stipend.setEntry_leave_cut((float)stipend.getEntry_leaveDay()*setEntryLeavCut);
			stipend.setNeglect_work((float)stipend.getNeglect_workDay()*setNeglectCut);
		}
		if(stipend.getLate_time()<20){
			stipend.setArrive_late((float)0);
		}else if(stipend.getLate_time()>20){
			stipend.setArrive_late((float)20);
		}
	}
	
	/**
	 * 更新浮动部分薪金信息
	 */
	@Override
	public void updateFloatStipend(Stipend stipend) {
	stipendDao.udpateFloatStipend(stipend);
	}
 
	/**
	 * 更新税前扣款部分薪金信息
	 */
	@Override
	public void updateCutPayStipend(Stipend stipend) {
		stipendDao.updateCutPayStipend(stipend);
	}

	/**
	 * 删除薪金记录
	 */
	@Override
	public void deleteStipendRecord(int emp_id, String granttime) {
	   deleteFloatStipend(emp_id,granttime);
	   deleteCutPayStipend(emp_id,granttime);
		
	}
		/**
		 * 删除税前扣款部分薪金信息
		 * @param emp_id
		 * @param granttime
		 */
	private void deleteCutPayStipend(int emp_id, String granttime) {
	   stipendDao.deleteCutPayStipend(emp_id,granttime);
		
	}

	/**
	 * 删除浮动部分薪金信息
	 * @param emp_id
	 * @param granttime
	 */
	private void deleteFloatStipend(int emp_id, String granttime) {
	  stipendDao.deleteFloatStipend(emp_id,granttime);
		
	}

	@Override
	public void accountStipend(Stipend stipend) {
	  
		stipend.setFloat_sum(getFloatSum(stipend));
		stipend.setCut_sum(getCutSum(stipend));
		stipend.setShould_pay(stipend.getFixed_sum()+stipend.getFloat_sum());/*-stipend.getCut_sum()*/
		
		Float cut_sum=stipend.getCut_sum();
		Float should_pay=stipend.getShould_pay();
		Float fixed_sum=stipend.getFixed_sum();
		Float float_sum=stipend.getFloat_sum();

		Float real_pay=getResultByExpression(stipend.getGranttime(),cut_sum,float_sum,fixed_sum,should_pay);
		stipend.setPersonal_tax(accountPersonnalTax(stipend.getShould_pay()));
		stipend.setReal_stipend(stipend.getPersonal_tax());
		
		if (stipendDao.getPayroll(stipend.getEmp_id(), stipend.getGranttime())!=null) {
			stipendDao.updatePayroll(stipend);
		}else{
		stipendDao.savaPayroll(stipend);
		}
	}
	
	/**
	 * 根据公式结算
	 * @param month
	 * @param cut_sum
	 * @param float_sum
	 * @param fixed_sum
	 * @param should_pay
	 * @return
	 * @throws ParseException
	 */
	public Float getResultByExpression(String month,float cut_sum,float float_sum,float fixed_sum,float should_pay) throws ParseException{
		JEP jep = new JEP(); //一个数学表达式
		String exp = getExpressionByMonth(month); //给变量赋值((a+b)*(c+b))/(c+a)/b
		jep.addVariable("a", fixed_sum);
		jep.addVariable("b", float_sum);
		jep.addVariable("c", cut_sum);
		jep.addVariable("d", should_pay);
		org.nfunk.jep.Node node=jep.parse(exp);
		Object result = jep.evaluate(node);
		return (Float) result;
	}
	
	/**
	 * 计算个税
	 * 缴税＝全月应纳税所得额*税率－速算扣除数
	 * 全月应纳税所得额＝(应发工资－四金)－3500
	 * 实发工资＝应发工资－四金－缴税
	 * @param should_pay
	 * @return
	 */
	private Float accountPersonnalTax(Float should_pay) {
		Float taxbase=should_pay-3500;
		Float Taxrate=(float) 0;  
		Float Quickdeduction=(float) 0;  
		if (taxbase<=0) {
			return should_pay;
		}
		else if(taxbase <=1500)  
        {  
            Taxrate=(float) 3;  
            Quickdeduction=(float) 0;  
        }else if(taxbase <=4500)  
        {  
            Taxrate=(float) 10;  
            Quickdeduction=(float) 105;  
        }else if(taxbase <=9000)  
        {  
            Taxrate=(float) 20;  
            Quickdeduction=(float) 555;  
        }else if(taxbase <=35000)  
        {  
            Taxrate=(float) 25;  
            Quickdeduction=(float) 1005;  
        }else if(taxbase <=55000)  
        {  
            Taxrate=(float) 30;  
            Quickdeduction=(float) 2755;  
        }else if(taxbase <=80000)  
        {  
            Taxrate=(float) 35;  
            Quickdeduction=(float) 5505;  
        }else  
        {  
            Taxrate=(float) 45;  
            Quickdeduction=(float) 13505;  
        }             
        return should_pay-((should_pay-3500)*Taxrate/100-Quickdeduction);  	
	}
	@Override
	public void savaSalaryRecord(Stipend stipend) {
	  stipendDao.addSalaryRecord(stipend);
	}
	@Override
	public List<Stipend> getSalaryRecordList(int currid, String month) {
		List<Stipend> stipends=stipendDao.getSalaryRecordList(currid,month);
		return stipends;
	}



	/**
	 * 获取考勤信息
	 * @param month
	 * @param uNametui
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer>getAttendanceByNameAndMonth(String month,String uname) {
		int thing=0,sick=0,neglect=0,entry=0,duty=0;
		int late_time=0;
		Attendence attendenceList;
		try {
			attendenceList = attendanceService.selectAttendanceListByMonthAndUserName(month, uname).get(0);
			int should_attendance=(int) attendenceList.getShould_attendance();
			int real_attendance=(int)attendenceList.getReal_attendance();
			String lateTime=attendenceList.getLate_time();
			if (lateTime==null||lateTime.equals("")) {
				late_time=0;
			}else{
			 late_time=stringToInteger(lateTime);
			}
			List<DayAndStatus>dayAndStatus=attendenceList.getList();
			  Map<String,Integer> map = new HashMap<String,Integer>();
			
			for(int i=0;i<dayAndStatus.size();i++)
			{
				String status=dayAndStatus.get(i).getStatus();
				switch (status) {
				case "1":
					duty++;
					break;
				case "S":
				thing++;
					break;
				case "B":
					sick++;
					break;
				case "K":
					neglect++;
					break;
				case "L":
					entry++;
					break;
				default:
					break;
				}
				
			}
			map.put("duty", duty);
			map.put("thing", thing);
			map.put("sick", sick);
			map.put("neglect", neglect);
			map.put("entry", entry);
			map.put("should_attendance", should_attendance);
			map.put("real_attendance", real_attendance);
			map.put("late_time", late_time);
			return map;
		} catch (Exception e) {
		
			/*e.printStackTrace();*/
			return null;
		}
	
	}
	@Override
	public String getExpressionByMonth(String month){
		return stipendDao.getExpressionByMonth(month);
	}
	
	public int stringToInteger(String string){
      String sub="";
      sub=string.replace("min", "");
      int result=Integer.parseInt(sub);
      return result;
      }
	@Override
	public void saveExpression(String month, String expression) {
		
		if (getExpressionByMonth(month)!=null) {
			stipendDao.updateExpression(month,expression);
		}else if(expression.equals("")||expression==null||month.equals("")||month==null){
			//如果为空就什么也不做
		}else {
			stipendDao.saveExpression(month,expression);	
		}
		
	}
}
