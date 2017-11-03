package com.jointem.hrm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.TalentDao;

import com.jointem.hrm.entity.Pages;
import com.jointem.hrm.entity.Talent;
import com.jointem.hrm.service.TalentService;

@Service
public class TalentServiceImp implements TalentService {

	@Resource
	TalentDao talentDao;

	@Override
	public void remoTalentPool(int id) {
		try {
			if (id > 0)
				talentDao.delTalent(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public Pages queryTalentList(String job, String sort, int pageNum, int pageSize) {
		Pages ps = new Pages();
		int start = (pageNum - 1) * pageSize;
		if (pageNum <= 1) {
			int totalCount = talentDao.totale(job);
			int totalPageNum = ps.totalPageNum(totalCount, pageSize);
			List<Talent> list = talentDao.selectTalents(job, sort, start, pageSize);
			ps.setPageNum(pageNum);
			ps.setPageSize(pageSize);
			ps.setTotalCount(totalCount);
			ps.setTotalPageNum(totalPageNum);
			ps.setT(list);
		} else {
			int totalCount = talentDao.totale(job);
			int totalPageNum = ps.totalPageNum(totalCount, pageSize);
			List<Talent> list = talentDao.selectTalents(job, sort, start, pageSize);
			ps.setPageNum(pageNum);
			ps.setPageSize(pageSize);
			ps.setTotalCount(totalCount);
			ps.setTotalPageNum(totalPageNum);
			ps.setT(list);
		}
		return ps;
	}

	@Override
	public Talent getTalent(Integer id) {
		Talent talent = null;
		if(id > 0){
			talent = talentDao.selectTalent(id);
		}
		return talent;
	}

}
