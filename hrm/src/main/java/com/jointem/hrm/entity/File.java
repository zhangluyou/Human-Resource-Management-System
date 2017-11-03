package com.jointem.hrm.entity;

public class File {
	int id;
	int fileNum;
	int rankNum;
	int salary;
	public File()
	{
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFileNum() {
		return fileNum;
	}
	public void setFileNum(int fileNum) {
		this.fileNum = fileNum;
	}
	public File(int fileNum, int rankNum, int salary) {
		super();
		this.fileNum = fileNum;
		this.rankNum = rankNum;
		this.salary = salary;
	}
	public int getRankNum() {
		return rankNum;
	}
	public void setRankNum(int rankNum) {
		this.rankNum = rankNum;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "file [id=" + id + ", fileNum=" + fileNum + ", rankNum=" + rankNum + ", salary=" + salary + "]";
	}
}
