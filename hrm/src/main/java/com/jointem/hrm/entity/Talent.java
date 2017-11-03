package com.jointem.hrm.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Talent implements Serializable {

	private static final long serialVersionUID = 8981021336052574780L;

	private int id;
	private String job;
	private Integer salary;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Timestamp interviewTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dutyTime;
	private Integer channel;
	private String origin;
	private String name;
	private Integer sex;
	private String nativePlace;
	private String edu;
	private String peoples;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthday;
	private Integer domicile;
	private Integer height;
	private Integer weight;
	private Integer marriage;
	private Integer onlyChild;
	private Integer politicalStatus;
	private String address;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date workTime;
	private Integer workAge;
	private String socialSecurity;
	private String phone;
	private String contacts;
	private String contactsPhone;
	private String email;
	private String wechat;
	private String qq;
	private Integer isAdjust;
	private String cause;
	private String education;
	private String work;
	private String training;
	private String certificate;
	private String skill;
	private String interest;
	private String specialty;
	private String family;
	private String instruction;
	private String acquaintances;
	private Integer state;

	public int getId() {
		return id;
	}

	public String getJob() {
		return job;
	}

	public Integer getSalary() {
		return salary;
	}

	public Timestamp getInterviewTime() {
		return interviewTime;
	}

	public Date getDutyTime() {
		return dutyTime;
	}

	public Integer getChannel() {
		return channel;
	}

	public String getOrigin() {
		return origin;
	}

	public String getName() {
		return name;
	}

	public Integer getSex() {
		return sex;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public String getEdu() {
		return edu;
	}

	public String getPeoples() {
		return peoples;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Integer getDomicile() {
		return domicile;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getWeight() {
		return weight;
	}

	public Integer getMarriage() {
		return marriage;
	}

	public Integer getOnlyChild() {
		return onlyChild;
	}

	public Integer getPoliticalStatus() {
		return politicalStatus;
	}

	public String getAddress() {
		return address;
	}

	public Date getWorkTime() {
		return workTime;
	}

	public Integer getWorkAge() {
		return workAge;
	}

	public String getSocialSecurity() {
		return socialSecurity;
	}

	public String getPhone() {
		return phone;
	}

	public String getContacts() {
		return contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public String getEmail() {
		return email;
	}

	public String getWechat() {
		return wechat;
	}

	public String getQq() {
		return qq;
	}

	public Integer getIsAdjust() {
		return isAdjust;
	}

	public String getCause() {
		return cause;
	}

	public String getEducation() {
		return education;
	}

	public String getWork() {
		return work;
	}

	public String getTraining() {
		return training;
	}

	public String getCertificate() {
		return certificate;
	}

	public String getSkill() {
		return skill;
	}

	public String getInterest() {
		return interest;
	}

	public String getSpecialty() {
		return specialty;
	}

	public String getFamily() {
		return family;
	}

	public String getInstruction() {
		return instruction;
	}

	public String getAcquaintances() {
		return acquaintances;
	}

	public Integer getState() {
		return state;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public void setInterviewTime(Timestamp interviewTime) {
		this.interviewTime = interviewTime;
	}

	public void setDutyTime(Date dutyTime) {
		this.dutyTime = dutyTime;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public void setPeoples(String peoples) {
		this.peoples = peoples;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setDomicile(Integer domicile) {
		this.domicile = domicile;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public void setMarriage(Integer marriage) {
		this.marriage = marriage;
	}

	public void setOnlyChild(Integer onlyChild) {
		this.onlyChild = onlyChild;
	}

	public void setPoliticalStatus(Integer politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
	}

	public void setWorkAge(Integer workAge) {
		this.workAge = workAge;
	}

	public void setSocialSecurity(String socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public void setIsAdjust(Integer isAdjust) {
		this.isAdjust = isAdjust;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public void setWork(String work) {
		this.work = work;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public void setAcquaintances(String acquaintances) {
		this.acquaintances = acquaintances;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Talent [id=" + id + ", job=" + job + ", salary=" + salary + ", interviewTime=" + interviewTime
				+ ", dutyTime=" + dutyTime + ", channel=" + channel + ", origin=" + origin + ", name=" + name + ", sex="
				+ sex + ", nativePlace=" + nativePlace + ", edu=" + edu + ", peoples=" + peoples + ", birthday="
				+ birthday + ", domicile=" + domicile + ", height=" + height + ", weight=" + weight + ", marriage="
				+ marriage + ", onlyChild=" + onlyChild + ", politicalStatus=" + politicalStatus + ", address="
				+ address + ", workTime=" + workTime + ", workAge=" + workAge + ", socialSecurity=" + socialSecurity
				+ ", phone=" + phone + ", contacts=" + contacts + ", contactsPhone=" + contactsPhone + ", email="
				+ email + ", wechat=" + wechat + ", qq=" + qq + ", isAdjust=" + isAdjust + ", cause=" + cause
				+ ", education=" + education + ", work=" + work + ", training=" + training + ", certificate="
				+ certificate + ", skill=" + skill + ", interest=" + interest + ", specialty=" + specialty + ", family="
				+ family + ", instruction=" + instruction + ", acquaintances=" + acquaintances + ", state=" + state
				+ "]";
	}

}
