package com.syntask.sale.dao;

import java.util.List;

import com.syntask.sale.entity.Employee;

public interface EmployeeDao {
	
	void insertEmployee(Employee emp);
	void deleteEmployee(Employee emp);
	List<Employee> getEmployees(int pageSize, int pageIndex);
	Employee findEmployee(String id);
	void updateEmployee(Employee emp);

}
