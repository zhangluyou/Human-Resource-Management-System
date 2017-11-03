package com.jointem.hrm.entity;

import java.io.Serializable;
import java.util.Date;


public class Institution implements Serializable {
	private Integer msgid;
	private Integer id;
	private Integer userid;
	private String type;
	private String content;
	private Date  createtime;
	private Users users;
	private Message message;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}


	public Users getUser() {
		return users;
	}
	public void setUser(Users user) {
		this.users = users;
	}
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Integer getMsgid() {
		return msgid;
	}
	public void setMsgid(Integer msgid) {
		this.msgid = msgid;
	}
	
	@Override
	public String toString() {
		return "Institution [msgid=" + msgid + ", id=" + id + ", userid=" + userid + ", type=" + type + ", content="
				+ content + ", createtime=" + createtime + "]";
	}

}
