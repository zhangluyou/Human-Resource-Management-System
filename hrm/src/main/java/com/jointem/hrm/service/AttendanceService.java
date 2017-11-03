package com.jointem.hrm.service;

import com.jointem.hrm.entity.Attendence;

import java.util.List;

/**
 * @Author :liubao
 * @Description:
 * @Date :Created in 10:34 2017/9/12
 * @Modified By:
 */
public interface AttendanceService {
    void uploadExcel(Attendence attendence) throws Exception;

    int selectAttendanceCountByDate(String date);

    void deleteAttendanceByDate(String date);

    List<Attendence> selectAttendanceListByMonthAndUserName(String searchMonth, String currUserName) throws Exception;

    List<String> selectMonthList() throws  Exception;

    String selectNamebyUserName(String currUserName) throws  Exception;
}

