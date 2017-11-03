package com.jointem.hrm.entity;

import java.util.Date;

public class InstitutionMsg {

	public Integer getSenderid() {
		return senderid;
	}
	public void setSenderid(Integer senderid) {
		this.senderid = senderid;
	}
	public Integer getReceiverid() {
		return receiverid;
	}
	public void setReceiverid(Integer receiverid) {
		this.receiverid = receiverid;
	}
	public Boolean getIsread() {
		return isread;
	}
	public void setIsread(Boolean isread) {
		this.isread = isread;
	}
	public Date getSendtimes() {
		return sendtimes;
	}
	public void setSendtimes(Date sendtimes) {
		this.sendtimes = sendtimes;
	}
	private Integer senderid;
	@Override
	public String toString() {
		return "InstitutionMsg [senderid=" + senderid + ", receiverid=" + receiverid + ", isread=" + isread
				+ ", sendtimes=" + sendtimes + "]";
	}
	private Integer receiverid;
	private Boolean isread;
	private Date sendtimes;
}
