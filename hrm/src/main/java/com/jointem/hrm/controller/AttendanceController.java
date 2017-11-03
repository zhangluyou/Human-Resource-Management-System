package com.jointem.hrm.controller;

import com.jointem.hrm.entity.Attendence;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.AttendanceService;
import com.jointem.hrm.utils.DateUtil;
import com.jointem.hrm.utils.ImportExcelUtil;
import com.jointem.hrm.utils.JSONUtil;
import com.jointem.hrm.utils.MonthDayUtil;
import com.jointem.hrm.vo.DayAndStatus;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author :liubao
 * @Description:
 * @Date :Created in 10:31 2017/9/12
 * @Modified By:
 */
@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    AttendanceService attendanceService;


    /**
     * 返回考勤表上传界面
     * @return
     */
    @RequiresPermissions("addAttendance:uploadExcel")
    @RequestMapping("/addAttendance")
    public String addAttendance()
    {
        return "exceluploadtest";
    }

    /**
     * 从前端接收excel用vo对象保存接收，返回考勤列表界面
     * @param request
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/uploadExcel")
    public Object uploadExcel(HttpServletRequest request) throws Exception
    {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        System.out.println("通过传统方式form表单提交方式导入excel文件！");

        InputStream in =null;
        List<List<Object>> listob = null;
        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty())
        {
            throw new Exception("文件不存在！");
        }
        in = file.getInputStream();
        listob = new ImportExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        in.close();
        System.out.println("总条数:"+listob.size());


        //读取excel的第三行记录，获取考勤表的日期（年月）
        List<Object> lotemp=listob.get(3);
        int length=String.valueOf(lotemp.get(0)).length();
        String tempDate=String.valueOf(lotemp.get(0)).substring(length-7,length);

        //根据年月判断该月的天数
        int monthDay=new MonthDayUtil().getMonthDay(tempDate);
        String completeDate=MonthDayUtil.getcompleteDate(tempDate);
        //判断上传的月份的考勤表是否已经存在了
        if(attendanceService.selectAttendanceCountByDate(completeDate)>0);
        {
            //将之前存储的删除
            attendanceService.deleteAttendanceByDate(completeDate);
        }

        for (int i = 7; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);

            //读取第5行，读取日期对应的星期
            List<Object> weekDayLo=listob.get(5);
            //跳过读取“小计”这一行
            if(String.valueOf(lo.get(0)).equals("小计"))
            {
                continue;
            }

            Attendence attendence = new Attendence();
            attendence.setDepartment(String.valueOf(lo.get(0)));
            attendence.setName(String.valueOf(lo.get(2)));

            //用list以及vo接收出勤,循环读取当月的天数
            List<DayAndStatus> voList=new ArrayList<>();
            for(int j=3;j<=monthDay+2;j++)
            {
                DayAndStatus dayAndStatusVo=new DayAndStatus();
                String status="";
                dayAndStatusVo.setDay(j-2);
                if(String.valueOf(lo.get(j)).equals("√"))
                {
                    status="1";
                }
                else if(String.valueOf(lo.get(j)).equals("×")) {
                    status = "0";
                }
                else
                {
                    status=String.valueOf(lo.get(j));
                }

                dayAndStatusVo.setWeekday(String .valueOf(weekDayLo.get(j)));
                dayAndStatusVo.setStatus(status);
                voList.add(dayAndStatusVo);

            }

            attendence.setInfo(JSONUtil.createJsonString(voList));
            attendence.setShould_attendance(Integer.parseInt(String.valueOf(lo.get(34))));
            attendence.setReal_attendance(Integer.parseInt(String.valueOf(lo.get(35))));
            attendence.setLate_time(String.valueOf(lo.get(36)));
            attendence.setAttendance_rate(Integer.parseInt(String.valueOf(lo.get(37))));
            attendence.setRemarks(String.valueOf(lo.get(38)));
            attendence.setDate(completeDate);
            //调用service
            attendanceService.uploadExcel(attendence);
        }
        return listob;
    }

    /**
     * 考勤信息查看，根据查询月份与当前用户名差找考勤列表
     * @param searchMonth 查找的月份
     * @param model
     * @return
     */
    @RequestMapping("/attendanceList")
    public String attendanceList(String searchMonth, Model model)
    {

        //获取上个月的月份
        Date currDate=new Date();
        String  tempYear=DateUtil.formatEnDate(currDate).substring(0,4);
        String tempMonth=Integer.toString(Integer.parseInt(DateUtil.formatEnDate(currDate).substring(6,7))-1);

        //初始化时，传入的参数为空，默认查询上个月的考勤
        if(searchMonth==null|| searchMonth=="")
        {
            searchMonth=tempYear+"年"+tempMonth+"月";
        }
        String currName="";
        Subject subject = SecurityUtils.getSubject();
        //如果当前用户不是管理员
        if(!subject.hasRole("admin"))
        {
            Users user= (Users) subject.getSession().getAttribute("user");
            String currUserName=user.getUsername();
            try {
                currName=attendanceService.selectNamebyUserName(currUserName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //调用service
        List<Attendence> attendanceList=null;
        List<String> monthList=null;
        try {
            //查询考勤列表
            attendanceList= attendanceService.selectAttendanceListByMonthAndUserName(searchMonth,currName);
            //查询考勤月份
            monthList=attendanceService.selectMonthList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将查询结果放入域中
        //考勤月份
        model.addAttribute("monthList",monthList);
        //查找的月份
        model.addAttribute("searchMonth",searchMonth);
        //查询的考勤列表
        model.addAttribute("attendenceList",attendanceList);
        //显示当月的天数
        if(attendanceList.size()>0)
        {
            model.addAttribute("DayAndStatus",attendanceList.get(0).getList());

        }
        return "attendancelist";
    }
}
