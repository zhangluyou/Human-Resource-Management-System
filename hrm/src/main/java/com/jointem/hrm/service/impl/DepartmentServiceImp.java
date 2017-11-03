package com.jointem.hrm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jointem.hrm.dao.DepartmentDao;
import com.jointem.hrm.entity.Department;
import com.jointem.hrm.service.DepartmentService;
@Service("departmentService")
public class DepartmentServiceImp implements DepartmentService{
	@Autowired 
	DepartmentDao departmentDao;
	@Override
	public List<Department> selectAllDepartment() {
		return departmentDao.selectAllDepartment();
	}
	@Override
	public Department insertDepartment(Department d) {
		departmentDao.insertDepartment(d);
		return d;
		
	}
	@Override
	public void deleteDepartment(int id) {
		departmentDao.deleteDepartment(id);
	}
	@Override
	public void setDepartment(Department d) {
		departmentDao.setDepartment(d);
	}

}
