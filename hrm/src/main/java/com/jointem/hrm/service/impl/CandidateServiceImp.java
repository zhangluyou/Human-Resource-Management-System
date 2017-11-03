package com.jointem.hrm.service.impl;

import com.jointem.hrm.dao.CandidateDao;


import com.jointem.hrm.entity.Candidate;
import com.jointem.hrm.entity.Pages;
import com.jointem.hrm.service.CandidateService;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class CandidateServiceImp implements CandidateService {
	
	@Resource
	private CandidateDao candidateDao;

	@Override
	public Candidate addCandidate(Candidate candidate) {
		try {
			candidate.setState(0);
			int i = this.candidateDao.insertCandidate(candidate);
			if (i == 1) {
				return candidate;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void removeCandidate(Integer id) {
		try {
			if(id > 0){
				candidateDao.deleteCandidateById(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void joinStock(int id) {
		try {
			if (id > 0) {
				candidateDao.stock(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public Candidate queryCandidate(int id) {
		Candidate candidate = null;
		if(id > 0){
			candidate = candidateDao.selectCandidate(id);
		}
		return candidate;
	}
	

	@Override
	public Pages queryCandidateList(String job, String sort, int pageNum, int pageSize) {
		Pages ps = new Pages();
		int start = (pageNum - 1) * pageSize;
		if (pageNum <= 1) {		
			int totalCount = candidateDao.totale(job);
			int totalPageNum = ps.totalPageNum(totalCount, pageSize);
			List<Candidate> list = candidateDao.selectCandidates(job, sort, start, pageSize);
			ps.setPageNum(pageNum);
			ps.setPageSize(pageSize);
			ps.setTotalCount(totalCount);
			ps.setTotalPageNum(totalPageNum);
			ps.setT(list);	
		}else{
			int totalCount = candidateDao.totale(job);
			int totalPageNum = ps.totalPageNum(totalCount, pageSize);
			List<Candidate> list = candidateDao.selectCandidates(job, sort, start, pageSize);
			ps.setPageNum(pageNum);
			ps.setPageSize(pageSize);
			ps.setTotalCount(totalCount);
			ps.setTotalPageNum(totalPageNum);
			ps.setT(list);
		}
		return ps;
	}

	

}
