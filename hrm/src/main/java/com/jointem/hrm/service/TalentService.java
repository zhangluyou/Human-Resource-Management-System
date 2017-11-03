package com.jointem.hrm.service;

import com.jointem.hrm.entity.Pages;
import com.jointem.hrm.entity.Talent;

public interface TalentService {
	
	void remoTalentPool(int id);
	
	Talent getTalent(Integer id);
	
	Pages queryTalentList(String job, String sort, int pageNum, int pageSize);
		
}
