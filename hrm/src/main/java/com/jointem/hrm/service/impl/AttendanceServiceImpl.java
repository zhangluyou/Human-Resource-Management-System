package com.jointem.hrm.service.impl;

import com.jointem.hrm.dao.AttendanceDao;
import com.jointem.hrm.entity.Attendence;
import com.jointem.hrm.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author :liubao
 * @Description:
 * @Date :Created in 10:35 2017/9/12
 * @Modified By:
 */
@Service("AttendanceService")
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    AttendanceDao attendanceDao;

    /**
     * 调用Dao,将数据持久化
     * @param attendence
     * @throws Exception
     */
    @Override
    public void uploadExcel(Attendence attendence) throws Exception {
        attendanceDao.addAttendance(attendence);
    }

    /**
     * 根据日期查询考勤表的条数
     * @param date
     * @return
     */
    @Override
    public int selectAttendanceCountByDate(String date) {
        return attendanceDao.selectAttendanceCountByDate(date);
    }

    /**
     * 按日期删除考勤表
     * @param date
     */
    @Override
    public void deleteAttendanceByDate(String date) {
        attendanceDao.deleteAttendanceByDate(date);
    }

    /**
     * 通过月份和姓名查找考勤列表
     * @param searchMonth 月份
     * @param currUserName 姓名
     * @return
     * @throws Exception
     */
    @Override
    public List<Attendence> selectAttendanceListByMonthAndUserName(String searchMonth, String currUserName) throws Exception {

        Map<Object,Object> map=new HashMap<>();
        map.put("searchMonth",searchMonth);
        map.put("currUserName",currUserName);
        List<Attendence> list=null;
        list=attendanceDao.selectAttendanceListByMonthAndUserName(map);
        return list;
    }

    /**
     * 调用dao查询月份列表
     * @return
     * @throws Exception
     */
    @Override
    public List<String > selectMonthList() throws Exception {
        return attendanceDao.selectMonthList();
    }

    /**
     * 根据用户名查询用户真实姓名
     * @param currUserName
     * @return
     * @throws Exception
     */
    @Override
    public String selectNamebyUserName(String currUserName) throws Exception {
        return attendanceDao.selectNamebyUserName(currUserName);
    }
}
