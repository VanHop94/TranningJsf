package com.syntask.sale.service.impl;

import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;

import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.service.EmployeeService;

@Name(value = "employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	//@In(value = "employeeDao", create = true)
	private EmployeeDao employeeDao;
	
	public EmployeeServiceImpl(){
		super();
		employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
	}

	public List<Employee> getEmployees(int pageSize, int pageIndex) {
		return employeeDao.getEmployees(pageSize, pageIndex);
	}

	@Override
	public void insert(Employee emp) {
		employeeDao.insertEmployee(emp);
	}

	@Override
	public void update(Employee emp) {
		employeeDao.updateEmployee(emp);
	}

}
