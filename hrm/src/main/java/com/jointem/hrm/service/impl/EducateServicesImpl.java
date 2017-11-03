package com.jointem.hrm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jointem.hrm.dao.EducateDao;
import com.jointem.hrm.dao.MessageDao;
import com.jointem.hrm.entity.Educate;
import com.jointem.hrm.entity.Message;
import com.jointem.hrm.entity.Page;
import com.jointem.hrm.entity.Users;
import com.jointem.hrm.service.EducateService;
import com.jointem.hrm.service.UserService;
import com.jointem.hrm.vo.Column;

@Service("educateService")
public class EducateServicesImpl implements EducateService {
	@Autowired
	EducateDao educatedao;
	@Autowired
	UserService userService;
	@Autowired
	MessageDao messageDao;

	@ResponseBody
	@Override
	public Educate findEducate(int id) {
		Educate dEducate = educatedao.findEducateById(id);
		return dEducate;
	}

	@Override
	public Educate saveEducate(Educate educate) {
		educatedao.saveEducate(educate);
		return educate;
	}

	@Override
	public Page<Educate> getAllEducate(int pageNum, int pagesize) {	
		int startnum = (pageNum - 1) * pagesize;
		if (startnum <= 0)
			startnum = 0;
		Page<Educate> page = new Page<Educate>();
		List<Educate> list = educatedao.getAllEducate(startnum, pagesize);
		int pagenmu = (startnum + pagesize) / pagesize;
		page.setPageNum(pagenmu);
		page.setPageSize(pagesize);
		int totalcount = educatedao.getCountOfAllEducate();
		page.setTotalCount(totalcount);
		int totalpagenum = 0;
		if (totalcount % pagesize == 0)
			totalpagenum = totalcount / pagesize;
		else
			totalpagenum = totalcount / pagesize + 1;
		page.setTotalPageNum(totalpagenum);
		page.setT(list);
		return page;
	}

	/**
	 * 删除信息，连带中间表
	 * 
	 * @param id
	 * @param startnum
	 * @param pagesize
	 * @return
	 */
	@Override
	public Page<Educate> deleteEducate(int id, int startnum, int pagesize) {
		
		educatedao.deleteEducate(id);
		educatedao.deleteEutByEid(id);
		return getAllEducate(startnum, pagesize);
	}

	@Override
	public void updateEducateFlag(int id) {
		
		educatedao.updateEducateFlag(id);
	}

	/**
	 * 修改信息，修改中间表，先删除再插入
	 * 
	 * @param educate
	 * @param id
	 * @param names
	 */
	@Override
	public void updateEducate(Educate educate, int id, String names) {
		int index1=0,index2=0, index3=0, index4=0;
		educatedao.deleteEutByEid(id);
		String[] strings = names.split(",");
		for (String string : strings) {

			Users users = userService.getByUsername(string);
			if (users==null) {                                              //如果查不到users，则删除多余的student
				String name=educate.getStudent();
					if(name.contains(string)){
				      index1=name.indexOf(string);
				      index2=name.length();
				      index3=string.length();
				      index4=index2-index3;
					if (index1==index4) {                                   //在最后的位置或中间位置
						name=name.replace(","+string, "");
					}
					else if(index1==0){                                     //字符串头
					   name=name.replace(string+",", "");
					}
					
				}
					educate.setStudent(name);
				continue;
			}
			int uid = users.getId();
			educatedao.insertEducateUser(uid, id);
		}
		educatedao.updateeducate(educate);
	}

	/**
	 * 填写培训总结
	 * 
	 * @param educate
	 */
	@Override
	public void updateEducate(Educate educate) {
		
		educatedao.updateeducate(educate);
	}

	@Override
	public void toCheck(int id) {
		educatedao.lookThrough(id);
		Subject subject = SecurityUtils.getSubject();
		Users users = (Users) subject.getSession().getAttribute("user");
		int uid = users.getId();
		messageDao.deleteTypeMessage(id, uid, 0);
	}

	@Override
	public Page<Educate> getAllFinishedEducate(int pageNum, int pagesize) {
		

		int startnum = (pageNum - 1) * pagesize;
		if (startnum <= 0)
			startnum = 0;
		List<Educate> list = educatedao.getAllFinishedEducat(startnum, pagesize);
		Page<Educate> page = new Page<Educate>();
		int totalcount = educatedao.getCountOfAllFinishedEducat();
		page.setTotalCount(totalcount);
		int pagenmu = (startnum + pagesize) / pagesize;
		page.setPageNum(pagenmu);
		page.setPageSize(pagesize);
		page.setTotalCount(totalcount);
		int totalpagenum = 0;
		if (totalcount % pagesize == 0)
			totalpagenum = totalcount / pagesize;
		else
			totalpagenum = totalcount / pagesize + 1;
		page.setTotalPageNum(totalpagenum);
		page.setT(list);
		return page;
	}

	@Override
	public Page<Educate> queryByCondition(Map<String, Object> map) {
		List<Educate> educates = educatedao.queryByCondition(map);
		Page<Educate> page = new Page<Educate>();
		int totalcount = querycountByCondition(map);
		page.setTotalCount(totalcount);
		int pagenum = (int) map.get("pagenum");
		int pagesize = (int) map.get("pagesize");
		page.setPageNum(pagenum);
		page.setPageSize(pagesize);
		int totalpagenum = 0;
		if (totalcount % pagesize == 0)
			totalpagenum = totalcount / pagesize;
		else
			totalpagenum = totalcount / pagesize + 1;
		page.setTotalPageNum(totalpagenum);
		page.setT(educates);
		return page;
	}

	@Override
	public int querycountByCondition(Map<String, Object> map) {
		
		return educatedao.querycountByCondition(map);
	}

	@Override
	public Page<Educate> getUnLookThroughEducate(int pageNum, int pagesize) {
		
		int startnum = (pageNum - 1) * pagesize;
		if (startnum <= 0)
			startnum = 0;
		Page<Educate> page = new Page<Educate>();
		List<Educate> list = educatedao.getAllUnLookThrough(startnum, pagesize);
		int pagenmu = (startnum + pagesize) / pagesize;
		page.setPageNum(pagenmu);
		page.setPageSize(pagesize);
		int totalcount = educatedao.getCountOfUnLookThrough();
		page.setTotalCount(totalcount);
		int totalpagenum = 0;
		if (totalcount % pagesize == 0)
			totalpagenum = totalcount / pagesize;
		else
			totalpagenum = totalcount / pagesize + 1;
		page.setTotalPageNum(totalpagenum);
		page.setT(list);
		return page;
	}

	@Override
	public void submitlookthrough(int id) {
		
		educatedao.submitLookThrough(id);
	}

	
	@Override
	public void addMessage(Message message) {
		
		educatedao.addmessage(message);
	}

	@Override
	public Page<Users> updateEducateRecord(int uid, int eid, Byte isjoin) {
		
		educatedao.updateUet(uid, eid, isjoin);
		messageDao.deleteTypeMessage(eid, uid, 2);
		Page<Users> page = new Page<>();
		int totalCount = 0;
		byte byte1 = (byte) 0;
		List<Users> list = new ArrayList<>();
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = format.parse("3000-01-01");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		totalCount = educatedao.getPersonCountOfEducateRecord(uid, byte1, (byte) 0, "", date);
		list = educatedao.getPersonEducateRecord(uid, byte1, (byte) 0, "", date, 0, 8);
		page = pageStart(totalCount, 1, 8);
		page.setT(list);
		return page;
	}

	@Override
	public Page<Users> getEducateRecord(String ename, int pagenum, String dropSelectValue) {
		
		Subject subject = SecurityUtils.getSubject();
		Users users = (Users) subject.getSession().getAttribute("user");
		int id = users.getId();
		int totalCount = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		Date d = new Date();
		try {
			d = format.parse("3000-01-01");
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		List<Users> list = new ArrayList<>();
		Byte byte1 = new Byte((byte) 0);
		int pagesize = 8;
		if (pagenum == 0)
			pagenum = 1;
		if (dropSelectValue.equals("已完成")) {
			byte1 = (byte) 1;
			totalCount = educatedao.getPersonCountOfEducateRecord(id, byte1, (byte) 1, ename, d);
			list = educatedao.getPersonEducateRecord(id, byte1, (byte) 1, ename, d, pagenum * pagesize - pagesize,
					pagesize);
		} else if (dropSelectValue.equals("待参加")) {
			byte1 = (byte) 1;
			totalCount = educatedao.getPersonCountOfEducateRecord(id, byte1, (byte) 0, ename, date);
			list = educatedao.getPersonEducateRecord(id, byte1, (byte) 0, ename, date, pagenum * pagesize - pagesize,
					pagesize);
		} else if (dropSelectValue.equals("待确认")) {
			byte1 = (byte) 0;
			totalCount = educatedao.getPersonCountOfEducateRecord(id, byte1, (byte) 0, ename, date);
			list = educatedao.getPersonEducateRecord(id, byte1, (byte) 0, ename, date, pagenum * pagesize - pagesize,
					pagesize);
		}
		Page<Users> page = pageStart(totalCount, pagenum, pagesize);
		page.setT(list);
		return page;
	}

	public Page<Users> pageStart(int totalCount, int pagenum, int pagesize) {

		Page<Users> page = new Page<>();
		if (pagenum == 0)
			pagenum = 1;
		page.setPageNum(pagenum);
		if (pagesize == 0)
			pagesize = 8;
		page.setPageSize(pagesize);
		page.setTotalCount(totalCount);
		int totalPage = 0;
		if (totalCount % pagesize == 0)
			totalPage = totalCount / pagesize;
		else {
			totalPage = totalCount / pagesize + 1;
		}
		if (totalPage == 0 && totalCount == 0)
			totalPage = 1;
		page.setTotalPageNum(totalPage);
		return page;
	}

	@Override
	public List<Users> getEducateStudents(int id) {
		
		return educatedao.getEducateById(id).getUserList();
	}

	@Override
	public Educate getEducateByName(String name) {
		
		Educate educate = educatedao.getEducateByName(name);
		return educate;
	}

	@Override
	public void insertEducateUser(int uid, int eid) {
		
		educatedao.insertEducateUser(uid, eid);
	}

	@Override
	public void cancelCheck(int id) {
		
		educatedao.noCheck(id);
	}

	@Override
	public void addRemark(int id, String remark) {
		
		educatedao.addRemark(id, remark);
	}
	@Override
	public void deleteAlleducate(String ids) {
		
		String[] idStrings=ids.split(",");
		for (String string : idStrings) {
			int id=0;
			id=Integer.parseInt(string);
			Educate educate= educatedao.findEducateById(id);
			if(educate.getIsreviewed()!=1)
			educatedao.deleteEducate(id);
			}
	}

	@Override
	public List<Column> queryMonthCount(String date) {
		List<Educate> list = educatedao.queryByMonth(date);  
		List<Column> cList = Column.getColumns();             
		
		List<Educate> good = new ArrayList<>();
		List<Educate> middle = new ArrayList<>();
		List<Educate> bad = new ArrayList<>();
	
		for (Educate educate : list) {
			int level = educate.getEffectFlag();
			switch (level) {
			case 0:
				good.add(educate);
				break;
			case 1:
				middle.add(educate);
				break;
			case 2:
				bad.add(educate);
				break;
			default:
				break;
			}
		}
		cList.get(0).setData(getColumn(good));
		cList.get(1).setData(getColumn(middle));
		cList.get(2).setData(getColumn(bad));
		cList.get(3).setData(getTotal(getColumn(good), getColumn(middle), getColumn(bad)));
		return cList;
	}

	

	private int[] getColumn(List<Educate> list) {
		int[] statistics = new int[12];
		for (Educate educate : list) {
			String month = educate.getMonth();
			String indexStr = month.split("-")[1];
			int index = Integer.parseInt(indexStr)-1;
			statistics[index]++;
		}
		return statistics;
	}
	private int[]getTotal(int[]a,int []b,int c[]){
		int[] temp=new int[12];
		for(int i=0,len=temp.length;i<len;i++){
			temp[i]=a[i]+b[i]+c[i];
		}
		return temp;
	}

	@Override
	public void insertUserByName(String[] names, int eid) {
		for (String s : names) {
			Users users=userService.getByUsername(s);
			int uid=users.getId();
			insertEducateUser(uid, eid);
		}
		
	}
 
	/**
	 * 通过effet判断获取effectFlag
	 */
	@Override
	public int getEffectFlagByEffect(String effect) {
		int effectFlag=0;
		switch (effect) {
		case "好":
			effectFlag=0;
			break;
		case "中":
			effectFlag=1;
			break;
		case "差":
			effectFlag=2;
			break;
		default:
			break;
		}
		return effectFlag;
	}

	@Override
	public void createMessage(List<Users> users, int id) {
		 for (Users users2 : users) {
				Message message=new Message();
				message.setSender_id(id);
				message.setReceiver_id(users2.getId());
				message.setContent(findEducate(id).getName());
				message.setIsRead(false);
				message.setMessage_type(2);
				message.setSend_time(new Date());
				addMessage(message);
			}
		
	}
}
