package com.jointem.hrm.vo;

/**
 * @Author :liubao
 * @Description:
 * @Date :Created in 8:51 2017/9/12
 * @Modified By:
 */
public class DayAndStatus {


    public Integer getDay() {
        return day;
    }

    public String getStatus() {
        return status;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getWeekday() {
        return weekday;
    }
    private Integer day;
    private String status;
    private String weekday;
}
