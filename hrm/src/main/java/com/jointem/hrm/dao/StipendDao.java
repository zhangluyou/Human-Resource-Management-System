package com.jointem.hrm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.Stipend;

public interface StipendDao {

	public void savaFixedStipend(Stipend stipend);
	
	public Stipend getFixedPart(int id);         ///获取固定部分

	public void savaFloatStipend(Stipend stipend);      //保存浮动部分
 
	public void saveCutPay(Stipend stipend);              //保存税前扣款部分

	public Stipend getFloatPart(@Param("id")int id,@Param("granttime")String granttime);                //获取浮动部分

	public Stipend getCutPayPart(@Param("id")int id,@Param("granttime")String granttime);                //获取税前扣款部分
	
	public List<Stipend> findAllStipendByMonth(String month);

	public void udpateFloatStipend(Stipend stipend);

	public void updateCutPayStipend(Stipend stipend);

	public void deleteCutPayStipend(@Param("id")int id,@Param("granttime")String granttime);            //删除税前扣款部分

	public void deleteFloatStipend(@Param("id")int id,@Param("granttime")String granttime);             //删除浮动部分

	public void savaPayroll(Stipend stipend);                            //工资结算
	
	public Stipend  getPayroll(@Param("id")int id,@Param("granttime")String granttime);    //获取结算的工资信息
	
	public void updatePayroll(Stipend stipend);

	public void updateFixedStipend(Stipend stipend);                   //更新固定部分薪金信息

	public void addSalaryRecord(Stipend stipend);

	public List<Stipend> getSalaryRecordList(@Param("id")int id,@Param("granttime")String granttime);

	public void saveExpression(@Param("month")String month,@Param("expression")String expression);
	
	public String getExpressionByMonth(String month);

	public void updateExpression(@Param("month")String month,@Param("expression")String expression);
}
