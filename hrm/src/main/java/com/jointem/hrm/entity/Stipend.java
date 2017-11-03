package com.jointem.hrm.entity;

import java.awt.datatransfer.FlavorTable;
import java.io.Serializable;
import java.util.Date;

public class Stipend implements Serializable {
	
	private String name;
	private Float basic;
	private Float eat;
	private Float house;
	private String granttime;
	private Float duty_everyDay;          //全勤奖
	private Float other;
	private Float private_affairs; // 事假扣款
	private Float sick_leave; // 病假扣款
	private Float neglect_work; // 旷工扣款
	private Float entry_leave_cut; // 离/入职扣款
	private Float arrive_late; // 迟到扣款
	private Float social_pension; // 养老保险
	private Float social_medical; // 医疗保险
	private Float social_unemployment; // 失业险
	private Float take_pension; // 补扣养老金
	private Float take_medical; // 补扣医保
	private Float take_unemployment; // 补扣失业险
	private Float take_injury; // 补扣工伤险
	private Float take_birth; // 补扣生育险
	private Float house_pay; // 住房公积金个人费用
	private Float other_cut; // 其他扣款
	private Float level; // 职级工资
	private Float traffic; // 交通补贴
	private Float secret; // 密保费
	private Float overtime_pay; // 加班费
	private Float ep_age; // 司龄补贴
	private Float performance; // 绩效工资
	private Float bd_benefits; // 生日负福利
	private Float commission; // 提成
	private int emp_id;
	private String position;    //职位
    private Float phone_call;
	private Float fixed_sum;    //固定部分合计
	private Float award;      ///奖金
	private Float float_sum;    //浮动部分合计
	private Float social_sum;   //社保合计
    private Float take_sum;        //补扣部分合计
    private Float cut_sum;        //税前扣款合计
	private String department;         //部门名称
	private Float should_pay;    //应付工资
	private Float personal_tax;  //个税
	private Float real_stipend;   //实付工资
	private int should_attendance;  ///应出勤天数
	private float late_time;      //迟到时间
	private int real_attendance;  //实出勤天数
	private int private_leaveDay;   //事假
	private int sick_leaveDay;      //病假天数
	private int neglect_workDay;    //旷工天数
	private int entry_leaveDay;       //入离职当月未出勤
	
	
	
	public int getShould_attendance() {
		return should_attendance;
	}
	public void setShould_attendance(int should_attendance) {
		this.should_attendance = should_attendance;
	}
	public int getReal_attendance() {
		return real_attendance;
	}
	public void setReal_attendance(int real_attendance) {
		this.real_attendance = real_attendance;
	}
	public int getPrivate_leaveDay() {
		return private_leaveDay;
	}
	public void setPrivate_leaveDay(int private_leaveDay) {
		this.private_leaveDay = private_leaveDay;
	}
	public int getSick_leaveDay() {
		return sick_leaveDay;
	}
	public void setSick_leaveDay(int sick_leaveDay) {
		this.sick_leaveDay = sick_leaveDay;
	}
	public int getNeglect_workDay() {
		return neglect_workDay;
	}
	public void setNeglect_workDay(int neglect_workDay) {
		this.neglect_workDay = neglect_workDay;
	}
	public int getEntry_leaveDay() {
		return entry_leaveDay;
	}
	public void setEntry_leaveDay(int entry_leaveDay) {
		this.entry_leaveDay = entry_leaveDay;
	}

	
	
	public Float getShould_pay() {
		return should_pay;
	}
	public void setShould_pay(Float should_pay) {
		this.should_pay = should_pay;
	}
	public Float getPersonal_tax() {
		return personal_tax;
	}
	public void setPersonal_tax(Float personal_tax) {
		this.personal_tax = personal_tax;
	}
	public Float getReal_stipend() {
		return real_stipend;
	}
	public void setReal_stipend(Float real_stipend) {
		this.real_stipend = real_stipend;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Float getCut_sum() {
		return cut_sum;
	}
	public void setCut_sum(Float cut_sum) {
		this.cut_sum = cut_sum;
		
	}
	public Float getTake_sum() {
		return take_sum;
	}
	public void setTake_sum(Float take_sum) {
		this.take_sum = take_sum;
	}
	public Float getSocial_sum() {
		return social_sum;
	}
	public void setSocial_sum(Float social_sum) {
		this.social_sum = social_sum;
	}
	public Float getFloat_sum() {
		return float_sum;
	}
	public void setFloat_sum(Float float_sum) {
		this.float_sum = float_sum;
	}
	public Float getAward() {
		return award;
	}
	public void setAward(Float award) {
		this.award = award;
	}
	public Float getFixed_sum() {
		return fixed_sum;
	}
	public void setFixed_sum(Float fixed_sum) {
		this.fixed_sum = fixed_sum;
	}
	public Float getPhone_call() {
		return phone_call;
	}
	public void setPhone_call(Float phone_call) {
		this.phone_call = phone_call;
	}
	public String getPosition() {
		return position;
	}

	public Stipend(String name, Float basic, Float eat, Float house, String granttime, Float duty_everyDay, Float other,
			Float private_affairs, Float sick_leave, Float neglect_work, Float entry_leave_cut, Float arrive_late,
			Float social_pension, Float social_medical, Float social_unemployment, Float take_pension,
			Float take_medical, Float take_unemployment, Float take_injury, Float take_birth, Float house_pay,
			Float other_cut, Float level, Float traffic, Float secret, Float overtime_pay, Float ep_age,
			Float performance, Float bd_benefits, Float commission, int emp_id, String position, Float phone_call,
			Float fixed_sum, Float award, Float float_sum, Float social_sum, Float take_sum, Float cut_sum,
			String department, Float should_pay, Float personal_tax, Float real_stipend, int should_attendance,
			int real_attendance, int private_leaveDay, int sick_leaveDay, int neglect_workDay, int entry_leaveDay,float late_time) {
		super();
		this.name = name;
		this.basic = basic;
		this.eat = eat;
		this.house = house;
		this.granttime = granttime;
		this.duty_everyDay = duty_everyDay;
		this.other = other;
		this.private_affairs = private_affairs;
		this.sick_leave = sick_leave;
		this.neglect_work = neglect_work;
		this.entry_leave_cut = entry_leave_cut;
		this.arrive_late = arrive_late;
		this.social_pension = social_pension;
		this.social_medical = social_medical;
		this.social_unemployment = social_unemployment;
		this.take_pension = take_pension;
		this.take_medical = take_medical;
		this.take_unemployment = take_unemployment;
		this.take_injury = take_injury;
		this.take_birth = take_birth;
		this.house_pay = house_pay;
		this.other_cut = other_cut;
		this.level = level;
		this.traffic = traffic;
		this.secret = secret;
		this.overtime_pay = overtime_pay;
		this.ep_age = ep_age;
		this.performance = performance;
		this.bd_benefits = bd_benefits;
		this.commission = commission;
		this.emp_id = emp_id;
		this.position = position;
		this.phone_call = phone_call;
		this.fixed_sum = fixed_sum;
		this.award = award;
		this.float_sum = float_sum;
		this.social_sum = social_sum;
		this.take_sum = take_sum;
		this.cut_sum = cut_sum;
		this.department = department;
		this.should_pay = should_pay;
		this.personal_tax = personal_tax;
		this.real_stipend = real_stipend;
		this.should_attendance = should_attendance;
		this.real_attendance = real_attendance;
		this.private_leaveDay = private_leaveDay;
		this.sick_leaveDay = sick_leaveDay;
		this.neglect_workDay = neglect_workDay;
		this.entry_leaveDay = entry_leaveDay;
		this.late_time=late_time;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Float getPrivate_affairs() {
		return private_affairs;
	}

	public void setPrivate_affairs(Float private_affairs) {
		this.private_affairs = private_affairs;
	}

	public Float getSick_leave() {
		return sick_leave;
	}

	public void setSick_leave(Float sick_leave) {
		this.sick_leave = sick_leave;
	}

	public Float getNeglect_work() {
		return neglect_work;
	}

	public void setNeglect_work(Float neglect_work) {
		this.neglect_work = neglect_work;
	}

	public Float getEntry_leave_cut() {
		return entry_leave_cut;
	}

	public void setEntry_leave_cut(Float entry_leave_cut) {
		this.entry_leave_cut = entry_leave_cut;
	}

	public Float getArrive_late() {
		return arrive_late;
	}

	public void setArrive_late(Float arrive_late) {
		this.arrive_late = arrive_late;
	}

	public Float getSocial_pension() {
		return social_pension;
	}

	public void setSocial_pension(Float social_pension) {
		this.social_pension = social_pension;
	}

	public Float getSocial_medical() {
		return social_medical;
	}

	public void setSocial_medical(Float social_medical) {
		this.social_medical = social_medical;
	}

	public Float getSocial_unemployment() {
		return social_unemployment;
	}

	public void setSocial_unemployment(Float social_unemployment) {
		this.social_unemployment = social_unemployment;
	}

	public Float getTake_pension() {
		return take_pension;
	}

	public void setTake_pension(Float take_pension) {
		this.take_pension = take_pension;
	}

	public Float getTake_medical() {
		return take_medical;
	}

	public void setTake_medical(Float take_medical) {
		this.take_medical = take_medical;
	}

	public Float getTake_unemployment() {
		return take_unemployment;
	}

	public void setTake_unemployment(Float take_unemployment) {
		this.take_unemployment = take_unemployment;
	}

	public Float getTake_injury() {
		return take_injury;
	}

	public void setTake_injury(Float take_injury) {
		this.take_injury = take_injury;
	}

	public Float getTake_birth() {
		return take_birth;
	}

	public void setTake_birth(Float take_birth) {
		this.take_birth = take_birth;
	}

	public Float getHouse_pay() {
		return house_pay;
	}

	public void setHouse_pay(Float house_pay) {
		this.house_pay = house_pay;
	}

	public Float getOther_cut() {
		return other_cut;
	}

	public void setOther_cut(Float other_cut) {
		this.other_cut = other_cut;
	}

	public Float getLevel() {
		return level;
	}

	public void setLevel(Float level) {
		this.level = level;
	}

	public Float getTraffic() {
		return traffic;
	}

	public void setTraffic(Float traffic) {
		this.traffic = traffic;
	}

	public Float getSecret() {
		return secret;
	}

	public void setSecret(Float secret) {
		this.secret = secret;
	}

	public Float getOvertime_pay() {
		return overtime_pay;
	}

	public void setOvertime_pay(Float overtime_pay) {
		this.overtime_pay = overtime_pay;
	}

	public Float getEp_age() {
		return ep_age;
	}

	public void setEp_age(Float ep_age) {
		this.ep_age = ep_age;
	}

	public Float getPerformance() {
		return performance;
	}

	public void setPerformance(Float performance) {
		this.performance = performance;
	}

	public Float getBd_benefits() {
		return bd_benefits;
	}

	public void setBd_benefits(Float bd_benefits) {
		this.bd_benefits = bd_benefits;
	}

	public Float getCommission() {
		return commission;
	}

	public void setCommission(Float commission) {
		this.commission = commission;
	}

	public int getEmp_id() {
		return emp_id;
	}

	public void setEmp_id(int emp_id) {
		this.emp_id = emp_id;
	}

	/** default constructor */
	public Stipend() {
	}

	

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getBasic() {
		return this.basic;
	}

	public void setBasic(Float basic) {
		this.basic = basic;
	}

	public Float getEat() {
		return this.eat;
	}

	public void setEat(Float eat) {
		this.eat = eat;
	}

	public Float getHouse() {
		return this.house;
	}

	public void setHouse(Float house) {
		this.house = house;
	}

	/**
	 * @return Returns the granttime.
	 */
	public String getGranttime() {
		return granttime;
	}

	/**
	 * @param data
	 *            The granttime to set.
	 */
	public void setGranttime(String data) {
		this.granttime = data;
	}

	public Float getDuty_everyDay() {
		return this.duty_everyDay;
	}

	public void setDuty_everyDay(Float duty_everyDay) {
		this.duty_everyDay = duty_everyDay;
	}

	public Float getOther() {
		return this.other;
	}

	public void setOther(Float other) {
		this.other = other;
	}

	@Override
	public String toString() {
		return "Stipend [name=" + name + ", basic=" + basic + ", eat=" + eat + ", house=" + house + ", granttime="
				+ granttime + ", duty_everyDay=" + duty_everyDay + ", other=" + other + ", private_affairs="
				+ private_affairs + ", sick_leave=" + sick_leave + ", neglect_work=" + neglect_work
				+ ", entry_leave_cut=" + entry_leave_cut + ", arrive_late=" + arrive_late + ", social_pension="
				+ social_pension + ", social_medical=" + social_medical + ", social_unemployment=" + social_unemployment
				+ ", take_pension=" + take_pension + ", take_medical=" + take_medical + ", take_unemployment="
				+ take_unemployment + ", take_injury=" + take_injury + ", take_birth=" + take_birth + ", house_pay="
				+ house_pay + ", other_cut=" + other_cut + ", level=" + level + ", traffic=" + traffic + ", secret="
				+ secret + ", overtime_pay=" + overtime_pay + ", ep_age=" + ep_age + ", performance=" + performance
				+ ", bd_benefits=" + bd_benefits + ", commission=" + commission + ", emp_id=" + emp_id + ", position="
				+ position + ", phone_call=" + phone_call + ", fixed_sum=" + fixed_sum + ", award=" + award
				+ ", float_sum=" + float_sum + ", social_sum=" + social_sum + ", take_sum=" + take_sum + ", cut_sum="
				+ cut_sum + ", department=" + department + ", should_pay=" + should_pay + ", personal_tax="
				+ personal_tax + ", real_stipend=" + real_stipend + ", should_attendance=" + should_attendance
				+ ", real_attendance=" + real_attendance + ", private_leaveDay=" + private_leaveDay + ", sick_leaveDay="
				+ sick_leaveDay + ", neglect_workDay=" + neglect_workDay + ", entry_leaveDay=" + entry_leaveDay + "]";
	}
	public float getLate_time() {
		return late_time;
	}
	public void setLate_time(float late_time) {
		this.late_time = late_time;
	}
	

}
