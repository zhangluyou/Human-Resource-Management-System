package com.jointem.hrm.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class Users {
    private Integer id;
    private String username;
    private String name;
    private List<Educate> educates; 
	private String password;
    private Byte sex;
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前段到后台注解
    private Date birthday;
    private Date createtime;
    //  private Byte isadmin;
    private String content;
    private List<Roles> rolesList;
    private String jobId;
    private String positionId;
    private String department_id;
    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {

         this.jobId =jobId;
    }
    public List<Educate> getEducates() {
    	return educates;
    }
    
    public void setEducates(List<Educate> educates) {
    	this.educates = educates;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

//    public Byte getIsadmin() {
//        return isadmin;
//    }
//
//    public void setIsadmin(Byte isadmin) {
//        this.isadmin = isadmin;
//    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
    
	public List<Roles> getRolesList() {
        return rolesList;
    }

    public void setRolesList(List<Roles> rolesList) {
        this.rolesList = rolesList;
    }

    

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

	@Override
	public String toString() {
		return "Users [id=" + id + ", username=" + username + ", name=" + name + ", educates=" + educates
				+ ", password=" + password + ", sex=" + sex + ", birthday=" + birthday + ", createtime=" + createtime
				+ ", content=" + content + ",job_id="+jobId+",positionId="+positionId+",department_d="+department_id+", rolesList=" + rolesList+"]";
}


}