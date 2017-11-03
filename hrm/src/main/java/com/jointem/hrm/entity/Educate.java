package com.jointem.hrm.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Educate implements Serializable {
	/**
	 * 
	 */
	private Long id;
	private String name;
	private String purpose;
	private Date begintime;
	private Date endtime;
	private String datum;
	private String teacher;
	private String student;
	private Date createtime;
	private Byte educate;
	private String effect;
	private String summarize;
	private String remark;
	private String month;
	private int effectFlag;

	public int getEffectFlag() {
		return effectFlag;
	}

	public void setEffectFlag(int effectFlag) {
		this.effectFlag = effectFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private int isreviewed;

	private List<Users> userList;

	public void setIsreviewed(Byte isreviewed) {
		this.isreviewed = isreviewed;
	}

	public Educate(String name, String purpose, Date begintime, Date endtime, String datum, String teacher,
			String student, Date createtime, Byte educate, String effect, String summarize, String remark) {
		this.name = name;
		this.purpose = purpose;
		this.begintime = begintime;
		this.endtime = endtime;
		this.datum = datum;
		this.teacher = teacher;
		this.student = student;
		this.createtime = createtime;
		this.educate = educate;
		this.effect = effect;
		this.summarize = summarize;
		this.remark = remark;
	}

	/** default constructor */
	public Educate() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return this.purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Date getBegintime() {

		return this.begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	public Date getEndtime() {

		return this.endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public String getDatum() {
		return this.datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStudent() {
		return this.student;
	}

	public void setStudent(String student) {
		this.student = student;
	}

	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getEffect() {
		return this.effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getSummarize() {
		return this.summarize;
	}

	public void setSummarize(String summarize) {
		this.summarize = summarize;
	}

	public String toString() {
		StringBuffer toStr = new StringBuffer();
		toStr.append("[Educate] = [\n");
		toStr.append("    id = " + this.id + ";\n");
		toStr.append("    name = " + this.name + ";\n");
		toStr.append("    begintime = " + this.begintime + ";\n");
		toStr.append("    endtime = " + this.endtime + ";\n");
		toStr.append("    createtime = " + this.createtime + ";\n");
		toStr.append("    purpose = " + this.purpose + ";\n");
		toStr.append("    datum = " + this.datum + ";\n");
		toStr.append("    teacher = " + this.teacher + ";\n");
		toStr.append("    student = " + this.student + ";\n");
		toStr.append("    educate = " + this.educate + ";\n");
		toStr.append("    effect = " + this.effect + ";\n");
		toStr.append("    summarize = " + this.summarize + ";\n");
		toStr.append("    userList = " + this.userList + ";\n");
		toStr.append("    remark = " + this.remark + ";\n");
		toStr.append("    ];\n");
		return toStr.toString();
	}

	/**
	 * @return Returns the educate.
	 */
	public Byte getEducate() {
		return educate;
	}

	/**
	 * @param educate
	 *            The educate to set.
	 */
	public void setEducate(Byte educate) {
		this.educate = educate;
	}

	public List<Users> getUserList() {
		return userList;
	}

	public void setUserList(List<Users> userList) {
		this.userList = userList;
	}

	public int getIsreviewed() {
		return isreviewed;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}
}
