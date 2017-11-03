package com.jointem.hrm.controller;

import com.jointem.hrm.entity.Message;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.MessageServic;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("message")
public class MessageController {
    @Autowired
    MessageServic messageService;
	
	@RequestMapping("/findIsRead")
	@ResponseBody
	public List<Message> findIsRead()
	{
		List<Message> message=null;
		try {
			Subject subject = SecurityUtils.getSubject();
            Users user= (Users) subject.getSession().getAttribute("user");
 	    //System.out.println(user);
            if(user!=null)
            {
            	message=messageService.findIsRead(user.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return message;
		
	}
	@RequestMapping("/ignoreCurrentMessage")
	public void ignoreCurrentMessage()
	{
		Subject subject = SecurityUtils.getSubject();
        Users user= (Users) subject.getSession().getAttribute("user");
		messageService.setIsReadStatus(user.getId());
	}
	
	
}
