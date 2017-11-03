package com.jointem.hrm.dao;

import java.util.List;

import com.jointem.hrm.entity.Department;

public interface DepartmentDao {
	public void insertDepartment(Department d);
	public void deleteDepartment(int id);
	public void setDepartment(Department d);
	public List<Department> selectAllDepartment();
}
