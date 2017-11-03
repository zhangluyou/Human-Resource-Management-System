package com.jointem.hrm.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jointem.hrm.entity.File;

public interface FileService {
	public boolean insertFile(File d);
	public void deleteFile(int id);
	public boolean setFile(File d);
	public List<File> selectAllFile();
	public List<File> selectRankNum();
	public List<File> selectFileNum(int num);
	public File selectFileSalaryById(int id);
	public File selectFileSalaryByIds(@Param("rankNum")int rankNum,@Param("fileNum")int fileNum);
	public void deleteFileByIds(@Param("rankNum")int rankNum,@Param("fileNum")int fileNum);
}
