package com.syntask.sale.dao.impl;

import java.util.List;

import org.jboss.seam.annotations.Name;

import com.syntask.sale.dao.BaseDao;
import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;

@Name(value = "employeeDao")
public class EmployeeDaoImpl extends BaseDao<String, Employee> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertEmployee(Employee emp) {
		persist(emp);
		
	}

	@Override
	public void deleteEmployee(Employee emp) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Employee> getEmployees(int pageSize, int pageIndex) {
		return getEntities(pageSize, pageIndex);
	}

	@Override
	public Employee findEmployee(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub

	}

}
