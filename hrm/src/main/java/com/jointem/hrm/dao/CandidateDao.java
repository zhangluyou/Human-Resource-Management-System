package com.jointem.hrm.dao;

import com.jointem.hrm.entity.Candidate;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public abstract interface CandidateDao {
	
	int totale(@Param("job")String job);

	int insertCandidate(Candidate candidate);
	
	int deleteCandidateById(@Param("id")Integer id);

	int stock(@Param("id") int id);
	
	Candidate selectCandidate(@Param("id")Integer id);

	List<Candidate> selectCandidates(@Param("job")String job, @Param("sort")String sort, @Param("startRow") int startRow, @Param("pageSize") int pageSize);


}
