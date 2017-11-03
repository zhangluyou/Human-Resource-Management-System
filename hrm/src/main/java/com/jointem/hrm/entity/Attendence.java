package com.jointem.hrm.entity;

import com.jointem.hrm.utils.JSONUtil;
import com.jointem.hrm.vo.DayAndStatus;

import java.util.List;

/**
 * Created by jenking on 2017/9/8.
 */
public class Attendence
{
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }


    public void setShould_attendance(float should_attendance) {
        this.should_attendance = should_attendance;
    }

    public void setReal_attendance(float real_attendance) {
        this.real_attendance = real_attendance;
    }

    public void setLate_time(String late_time) {
        this.late_time = late_time;
    }

    public void setAttendance_rate(float attendance_rate) {
        this.attendance_rate = attendance_rate;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }


    public float getShould_attendance() {
        return should_attendance;
    }

    public float getReal_attendance() {
        return real_attendance;
    }

    public String getLate_time() {
        return late_time;
    }

    public float getAttendance_rate() {
        return attendance_rate;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getDate() {
        return date;
    }
    public void setInfo(String info) {
        this.info = info;
        list = JSONUtil.getEntities(this.getInfo(),DayAndStatus.class);
    }
    public String getInfo() {
        return info;
    }



    private Integer id;
    private String name;  //员工姓名
    private String department;      //部门
    private float should_attendance;//应出勤
    private float real_attendance;  //实际出勤
    private String late_time;       //迟到时间/分
    private float attendance_rate;  //出勤率
    private String remarks;         //备注
    private String date;            //考勤日期（年月）
    private String info;            //存放考勤信息

    public void setList(List<DayAndStatus> list) {
        this.list = list;
    }

    public List<DayAndStatus> getList() {
        return list;
    }

    private List<DayAndStatus> list ;




}
