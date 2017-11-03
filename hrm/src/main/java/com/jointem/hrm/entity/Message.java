package com.jointem.hrm.entity;

import java.util.Date;

public class Message {
	private Integer msgId;
	public Integer getMsgId() {
		return msgId;
	}
	public void setMsgId(Integer msgId) {
		this.msgId = msgId;
	}
	public Integer getSender_id() {
		return sender_id;
	}
	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}
	public Integer getReceiver_id() {
		return receiver_id;
	}
	public void setReceiver_id(Integer receiver_id) {
		this.receiver_id = receiver_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Boolean getIsRead() {
		return isRead;
	}
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	public Integer getMessage_type() {
		return message_type;
	}
	public void setMessage_type(Integer message_type) {
		this.message_type = message_type;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	private Integer sender_id;
	@Override
	public String toString() {
		return "Message [msgId=" + msgId + ", sender_id=" + sender_id + ", receiver_id=" + receiver_id + ", content="
				+ content + ", isRead=" + isRead + ", message_type=" + message_type + ", send_time=" + send_time + "]";
	}
	private Integer receiver_id;
	private String content;
	private Boolean isRead;
	private Integer message_type;
	private Date send_time;
	
}
