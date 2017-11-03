package com.jointem.hrm.service;

import java.util.List;
import java.util.Map;

import org.apache.taglibs.standard.lang.jstl.test.beans.PublicInterface2;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.Message;
import com.jointem.hrm.entity.Page;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.vo.Column;

public interface EducateService {
	public Educate findEducate(int id);

	public Educate saveEducate(Educate educate);

	public Page<Educate> getAllEducate(int startnum, int pagesize);

	public Page<Educate> getAllFinishedEducate(int pageNum, int pagesize);

	public Page<Educate> deleteEducate(int id, int startnum, int pagesize);

	public void updateEducateFlag(int id);

	public void updateEducate(Educate educate,int id,String names);
    public void updateEducate (Educate educate);
	public Page<Educate> queryByCondition(Map<String, Object> map);

	public int querycountByCondition(Map<String, Object> map);
	public void toCheck(int id);
	public void submitlookthrough(int id);
	public Page<Educate> getUnLookThroughEducate(int startnum,int pagesize);



	public void addMessage(Message message);
	public Page<Users> getEducateRecord(String ename, int pagenum, String dropSelectValue);
	public Page<Users> updateEducateRecord(int uid, int eid, Byte isjoin);
	public Educate getEducateByName(String name);
	public void insertEducateUser(int uid, int eid);

	public List<Users> getEducateStudents(int id);

	public void cancelCheck(int id);

	public void addRemark(int id, String remark);
    public  List<Column> queryMonthCount(String date);
    
	public void deleteAlleducate(String ids);

	public void insertUserByName(String[] names, int eid);

	public int getEffectFlagByEffect(String effect);

	public void createMessage(List<Users> users, int id);

}
