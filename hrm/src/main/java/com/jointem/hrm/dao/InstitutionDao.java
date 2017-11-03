package com.jointem.hrm.dao;

import com.jointem.hrm.entity.Attendence;
import com.jointem.hrm.entity.Institution;
import com.jointem.hrm.entity.Message;

import java.util.List;
import java.util.Map;

public interface InstitutionDao {

	void addRap(Institution institution);

	List<Institution> selectAllInstitution(Map<String,Object> map);
	int selectAllInstitutionCount(String name);
	Map<String,Object> selectByPrimaryKey(int id);
	void deleteByPrimaryKey(int id);
	void delectInstitutionByIds(List<String> list);
	void updateSelective(Map<String,Object> map);
	void setIsReadStatus(Map<String ,Object> map);
	List<Institution> selectInstitutionsById(Map<String,Object> map);
	int selectInstitutionsCountById(Map<String,Object> map);
	void addMsg(Message message);

    void addAttendance(Attendence attendence);
}
