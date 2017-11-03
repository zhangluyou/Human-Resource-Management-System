package com.jointem.hrm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jointem.hrm.entity.Talent;

public interface TalentDao {
	
	int delTalent(@Param("id") int id);
	
	Talent selectTalent(@Param("id") int id);
	
	int totale(@Param("job")String job);

	List<Talent> selectTalents(@Param("job")String job, @Param("sort")String sort, @Param("startRow") int startRow, @Param("pageSize") int pageSize);
	
}
