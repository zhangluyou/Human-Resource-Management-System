package com.jointem.hrm.dao;

import com.jointem.hrm.entity.Attendence;

import java.util.List;
import java.util.Map;

/**
 * @Author :liubao
 * @Description:
 * @Date :Created in 10:36 2017/9/12
 * @Modified By:
 */
public interface AttendanceDao {
    void addAttendance(Attendence attendence);

    int selectAttendanceCountByDate(String date);

    void deleteAttendanceByDate(String date);

    List<Attendence> selectAttendanceListByMonthAndUserName(Map<Object, Object> map);

    List<String> selectMonthList();

    String selectNamebyUserName(String currUserName);
}
