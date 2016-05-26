package com.syntask.sale.service;

import java.util.List;

import com.syntask.sale.entity.Employee;

public interface EmployeeService {
	List<Employee> getEmployees(int pageSize, int pageIndex);
	void insert(Employee emp);
	void update(Employee emp);
	Employee findById(String id);
}
