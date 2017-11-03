package com.jointem.hrm.service;

import com.jointem.hrm.entity.Attendence;
import com.jointem.hrm.entity.Institution;

import java.util.List;
import java.util.Map;

public interface InstitutionService {

	void addRapAndRapMsg(String[] userIdList, String type, String content);
	Map<String,Object> selectAllInstitutions(String name, String pageSize, String pageNum);
	Map<String,Object> selectByPrimaryKey(int id);
	void deleteByPrimaryKey(int id);
	void delectInstitutionByIds(List<String> list);
	void updateSelective(Institution institution);
	void setIsReadStatus(Integer id);
	Map<String,Object> selectInstitutionsById(int userid,String name,String isRead,String  pageSize,String pageNum);


}
