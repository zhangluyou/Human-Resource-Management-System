package com.jointem.hrm.service;

import java.util.List;

import com.jointem.hrm.entity.Department;

public interface DepartmentService {
	public List<Department> selectAllDepartment();
	public Department insertDepartment(Department d);
	public void deleteDepartment(int id);
	public void setDepartment(Department d);
}
