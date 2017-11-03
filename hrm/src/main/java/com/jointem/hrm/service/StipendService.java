package com.jointem.hrm.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.nfunk.jep.ParseException;

import com.jointem.hrm.entity.Page;
import com.jointem.hrm.entity.Stipend;

public interface StipendService {

	 void saveFixedStipend(Stipend stipend);    //保存固定薪金部分
	
	 void savaFloatStipend(Stipend stipend);         //存储浮动部分的薪金
    
	 void saveCutPay(Stipend stipend);              //保存税前扣款部分

	 Stipend getFixedPart(int id);       ///获取固定部分
	
	 Stipend getFloatPart(int id,String granttime);       //获取浮动部分by user id and granttime
	
	 Stipend getCutPayPart(int id,String month);      //获取税前扣款部分by user id

	 void updateFloatStipend(Stipend stipend);        //更新浮动部分薪金信息
	
	 void updateCutPayStipend(Stipend stipend);       //更新税前扣款薪金信息
	
	 void saveAllStipendInfo(Stipend stipend);                       //保存所有的薪金信息

	 List<Stipend> findAllStipendByMonth(String month) throws Exception;

	 void deleteStipendRecord(int emp_id, String granttime);             //删除薪金信息记录

	 void accountStipend(Stipend stipend) throws ParseException, ParseException;                             //结算
	
	 void savaSalaryRecord(Stipend stipend);

	 List<Stipend> getSalaryRecordList(int currid, String month);
	
     Map<String, Integer> getAttendanceByNameAndMonth(String month,String uName) throws Exception;

	 void saveExpression(String month, String expression);

	String getExpressionByMonth(String month);
}
