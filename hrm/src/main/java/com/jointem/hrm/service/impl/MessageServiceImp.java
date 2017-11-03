package com.jointem.hrm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.MessageDao;
import com.jointem.hrm.entity.Message;
import com.jointem.hrm.service.MessageServic;

@Service("MessageService")
public class MessageServiceImp implements MessageServic {

	@Autowired
	MessageDao messageDao;

	@Override
	public List<Message> findIsRead(int id) {
		
		return messageDao.findMessage(id);
	}


	@Override
	public void setIsReadStatus(Integer id) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("isRead", true);
		map.put("receiver_id", id);
		messageDao.setIsReadStatus(map);
	} 


	@Override
	public void readCheckMsg(int id) {
		// TODO Auto-generated method stub
		messageDao.toReadEducateCheckMsg(id);
	}

}
