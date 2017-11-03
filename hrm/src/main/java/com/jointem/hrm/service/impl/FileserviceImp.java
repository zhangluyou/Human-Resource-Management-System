package com.jointem.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.FileDao;
import com.jointem.hrm.entity.File;
import com.jointem.hrm.service.FileService;

@Service("Fileservice")
public class FileserviceImp implements FileService{
	@Autowired
	FileDao filedao;
	@Override
	public boolean insertFile(File d) {
		File file=filedao.selectFileSalaryByIds(d.getRankNum(), d.getFileNum());
		if(file==null){
			filedao.insertFile(d);
			return true;
		}
		else
			return false;
	}

	@Override
	public void deleteFile(int id) {
		filedao.deleteFile(id);
	}

	@Override
	public boolean setFile(File d) {
		File file=filedao.selectFileSalaryByIds(d.getRankNum(), d.getFileNum());
		if(file==null)
		{
			return false;
		}
		else {
			filedao.setFile(d);
			return true;
		}
	}

	@Override
	public List<File> selectAllFile() {
		return filedao.selectAllFile();
	}

	@Override
	public File selectFileSalaryById(int id) {
		return filedao.selectFileSalaryById(id);
	}

	@Override
	public File selectFileSalaryByIds(int rankNum, int fileNum) {
		return filedao.selectFileSalaryByIds(rankNum, fileNum);
	}

	@Override
	public void deleteFileByIds(int rankNum, int fileNum) {
		filedao.deleteFileByIds(rankNum, fileNum);
	}

	@Override
	public List<File> selectRankNum() {
		return filedao.selectRankNum();
	}

	@Override
	public List<File> selectFileNum(int num) {
		return filedao.selectFileNum( num);
	}

}
