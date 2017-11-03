package com.jointem.hrm.service;

import com.jointem.hrm.entity.Candidate;

import com.jointem.hrm.entity.Pages;

public abstract interface CandidateService {
	
	Candidate addCandidate(Candidate candidate);

	void removeCandidate(Integer id);

	void joinStock(int id);
	
	Candidate queryCandidate(int id);

	Pages queryCandidateList(String job, String sort, int pageNum, int pageSize);
	
}
