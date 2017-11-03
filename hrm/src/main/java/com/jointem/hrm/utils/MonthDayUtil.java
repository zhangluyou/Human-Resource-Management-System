package com.jointem.hrm.utils;

import java.util.Date;

/**
 * @Author :liubao
 * @Description:根据年月判断该月有几天，例如：2017年8月
 * @Date :Created in 10:48 2017/9/14
 * @Modified By:
 */
public class MonthDayUtil {
    public int getMonthDay(String date)
    {

        //月份为10,11,12
        String year=date.substring(0,4);

        String  tempYear=DateUtil.formatEnDate(new Date()).substring(0,4);
        int curryear;
        int month;
        if(year.contains("年"))
        {
             curryear=Integer.parseInt(tempYear);
             month=Integer.parseInt(date.substring(4,6));
        }
        else
        {
             curryear=Integer.parseInt(date.substring(0,4));
             month=Integer.parseInt(date.substring(5,6));
        }
        int monthDay=0;
        switch (month)
        {
            case 2:
                if(this.isLeap(curryear))
                {
                    monthDay=29;
                }
                else
                {
                    monthDay=28;
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                monthDay=30;
                break;
            default:
                monthDay=31;
        }
        return monthDay;
    }
    public  Boolean isLeap(int year)
    {
        if(year%4==0 &&year %100!=0 || year % 400==0)
        {
            return true;
        }
        return null;
    }


    public static String getcompleteDate(String tempDate)
    {
        //月份为10,11,12
        String year=tempDate.substring(0,4);
        String  tempYear=DateUtil.formatEnDate(new Date()).substring(0,4);
        int curryear;
        int month;
       if(year.contains("年"))
       {
           curryear=Integer.parseInt(tempYear);
           month=Integer.parseInt(tempDate.substring(4,6));
           return curryear+"年"+month+"月";
       }

        return tempDate;
    }
}
