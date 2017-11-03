package com.jointem.hrm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jointem.hrm.entity.File;

public interface FileDao {
	public void insertFile(File d);
	public void deleteFile(int id);
	public void setFile(File d);
	public List<File> selectAllFile();
	public File selectFileSalaryById(int id);
	public List<File> selectRankNum();
	public List<File> selectFileNum(int num);
	public File selectFileSalaryByIds(@Param("rankNum")int rankNum,@Param("fileNum")int fileNum);
	public void deleteFileByIds(@Param("rankNum")int rankNum,@Param("fileNum")int fileNum);
}
