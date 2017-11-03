package com.jointem.hrm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.jointem.hrm.entity.Message;

public interface MessageDao {

	public List<Message> findMessage(Integer id);
	public Integer insertMessage(Message m);
	public void deleteMessage(int id);
	public void updeteMessage(int id);
	public void setIsReadStatus(Map<String, Object> map);
	public void deleteTypeMessage(@Param("sid") int sid,@Param("rid") int rid,@Param("type") int type);
    public void toReadEducateCheckMsg(int educate_id);    //审核一条改变一条的阅读状态

}
