package com.jointem.hrm.service.impl;

import com.jointem.hrm.dao.InstitutionDao;
import com.jointem.hrm.entity.Institution;
import com.jointem.hrm.entity.Message;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.InstitutionService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("institutionService")
public class InstitutionServiceImpl implements InstitutionService {

	@Autowired
	private InstitutionDao institutionDao;
	@Override
	
	
	/**
	 * 开启事务，添加rap表和rapmsg表的数据，已经配置了切面，无须注解
	 */
	public void addRapAndRapMsg(String[] userIdList, String type, String content) {
		for(String userId:userIdList)
		{
			
			Date createtime=new Date();
			Integer userIdd=Integer.parseInt(userId);
			
			
			Subject subject = SecurityUtils.getSubject();
			Users user=(Users) subject.getSession().getAttribute("user");
			Integer senderid=user.getId();
			Boolean isread=false;
			Date sendtimes=createtime;
			Integer receiverid=userIdd;
			
			Message message=new Message();
			message.setSender_id(senderid);
			message.setReceiver_id(receiverid);
			message.setContent(content);
			message.setIsRead(isread);
			message.setMessage_type(1);
			message.setSend_time(createtime);
			
		
			institutionDao.addMsg(message);
			

			Institution institution=new Institution();
			institution.setUserid(userIdd);
			institution.setType(type);
			institution.setContent(content);
			institution.setCreatetime(createtime);
			institution.setMsgid(message.getMsgId());
			institutionDao.addRap(institution);

		}
		
	}

    /**
     *
     * @param name
     * @param pageSize
     * @param pageNum
     * @return
     */
	public Map<String,Object> selectAllInstitutions(String name, String pageSize, String pageNum){
		Map<String,Object> param=new HashMap<String,Object>();
		param.put("name",name);
	/*	param.put("pageSize",pageSize);
		param.put("pageNum",pageNum);*/
		if(null != pageSize && !"".equals(pageSize)){
			param.put("pageSize",Integer.valueOf(pageSize));
		}
		else{
		    param.put("pageSize",5);
        }
        if(StringUtils.isNotEmpty(pageNum)){
            param.put("pageNum",(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize));
        }else{
            param.put("pageNum",0);
        }
        List<Institution> ins=institutionDao.selectAllInstitution(param);
        int count=institutionDao.selectAllInstitutionCount(name);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("insList",ins);
        map.put("count",count);
        return map;
	}
    public Map<String, Object> selectByPrimaryKey(int id){
	    return institutionDao.selectByPrimaryKey(id);
    }
	public void deleteByPrimaryKey(int id){
		institutionDao.deleteByPrimaryKey(id);
	}
	public void delectInstitutionByIds(List<String> list){
		institutionDao.delectInstitutionByIds(list);
	}
	public void updateSelective(Institution ins){
		Map<String,Object> maps=new HashMap<>();
		maps.put("type",ins.getType());
		maps.put("content",ins.getContent());
		maps.put("id",ins.getId());
		maps.put("userid",ins.getUserid());
		maps.put("msgid",ins.getMsgid());
		institutionDao.updateSelective(maps);
	}
	public void setIsReadStatus(Integer id){
		Map<String,Object> map=new HashMap<>();
		int a=1;
		map.put("msgid",id);
		map.put("isRead",a);
		institutionDao.setIsReadStatus(map);
	}
	public Map<String,Object> selectInstitutionsById(int userid,String name,String isRead,String pageSize,String pageNum){
		Map<String,Object> map=new HashMap<>();
		map.put("userid",userid);
		map.put("name",name);
		map.put("isRead",isRead);
		if(null != pageSize && !"".equals(pageSize)){
			map.put("pageSize",Integer.valueOf(pageSize));
		}
		else{
			map.put("pageSize",5);
		}
		if(StringUtils.isNotEmpty(pageNum)){
			map.put("pageNum",(Integer.valueOf(pageNum)-1)*Integer.valueOf(pageSize));
		}else{
			map.put("pageNum",0);
		}
		List<Institution> list=institutionDao.selectInstitutionsById(map);
/*		Map<String,Object> maps=new HashMap<>();
		maps.put("name",name);
		maps.put("isRead",isRead);*/
		int count=institutionDao.selectInstitutionsCountById(map);
		Map<String,Object> mapend=new HashMap<>();
		mapend.put("insSmallList",list);
		mapend.put("count",count);
		return mapend;
	}


}
