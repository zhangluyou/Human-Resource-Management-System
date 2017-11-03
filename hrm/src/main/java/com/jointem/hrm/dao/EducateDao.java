package com.jointem.hrm.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.auth.NTCredentials;
import org.apache.ibatis.annotations.Param;
import org.omg.CosNaming.IstringHelper;
import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.*;
public interface EducateDao {
	public void saveEducate(Educate educate);
	public void updateeducate(Educate educate);
	public void deleteEducate(int id);
	public Educate getEducateById(int id);
	public Educate getEducateByName(String name); 
	public List<Educate> queryByCondition(Map<String, Object> map);
	public void updateEducateFlag(int id);
	public void updateSummarize(int id,int summarize,int effect);
	public int querycountByCondition(Map<String, Object> map);
	
	public List<Educate> getAllEducate(@Param("startnum") int startnum, @Param("pagesize") int pagesize );     //获取所有计划
	public List<Educate> getAllUnLookThrough(@Param("startnum") int startnum, @Param("pagesize") int pagesize );
	public void lookThrough(int id);
	public List<Educate> getAllFinishedEducat(@Param("startnum") int startnum, @Param("pagesize") int pagesize);   //获取所有已经完成的培训
	public int getCountOfAllEducate();
	public int getCountOfAllFinishedEducat();
	public  List<Educate> queryByUserId(int uid);
	public List<Users> selectAllUser(int eid); 
	public List<Users> getPersonEducateRecord(@Param("id")int id,@Param("isjoin") Byte isjoin,@Param("b")Byte b,@Param("name") String name,@Param("time")Date time ,@Param("startnum")int startnum,@Param("pagesize")int pagesize);
	public int getPersonCountOfEducateRecord(@Param("id")int id,@Param("isjoin") Byte isjoin,@Param("b")Byte b, @Param("name") String name,@Param("time")Date time);
	public void updateUet(@Param("uid") int uid,@Param("eid")int eid ,@Param("isjoin") Byte isjoin);

	public void submitLookThrough(int id);
	/*public List<Message> findMessage(int id);
	 * public void messageIsRead(int id);*/
	public void addmessage(Message message);
	
	public int getCountOfUnLookThrough();
	public void insertEducateUser(@Param("uid") int uid,@Param("eid")int eid);
	public Educate findEducateById(int id);
	public void deleteEutByEid(@Param("eid")int eid);
	public void noCheck(int id);
	public void addRemark(@Param("id")int id, @Param("remark") String remark);
	public List<Educate> queryByMonth(String date);
}
