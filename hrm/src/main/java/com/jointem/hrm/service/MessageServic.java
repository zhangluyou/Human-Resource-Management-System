package com.jointem.hrm.service;

import java.util.List;

import com.jointem.hrm.entity.Message;

public interface MessageServic {

	List<Message> findIsRead(int id);


	void setIsReadStatus(Integer id);


	void readCheckMsg(int id);


    
	
}
