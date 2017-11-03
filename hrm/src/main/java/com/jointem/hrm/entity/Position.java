package com.jointem.hrm.entity;

public class Position {
int id;
int did;
String positionName;
int rankNum;
int fileNum;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getDid() {
	return did;
}
public void setDid(int did) {
	this.did = did;
}
public String getPositionName() {
	return positionName;
}
public void setPositionName(String positionName) {
	this.positionName = positionName;
}
public int getRankNum() {
	return rankNum;
}
public void setRankNum(int rankNum) {
	this.rankNum = rankNum;
}
public int getFileNum() {
	return fileNum;
}
public void setFileNum(int fileNum) {
	this.fileNum = fileNum;
}
@Override
public String toString() {
	return "Position [id=" + id + ", did=" + did + ", positionName=" + positionName + ", rankNum=" + rankNum
			+ ", fileNum=" + fileNum + "]";
}

}
