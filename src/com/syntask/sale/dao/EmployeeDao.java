package com.syntask.sale.dao;

import java.util.List;

import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Page;

public interface EmployeeDao {
	
	void insertEmployee(Employee emp);
	void deleteEmployee(Employee emp);
	//List<Employee> getEmployees(int pageSize, int pageIndex,int status);
	Employee findByCodeEmp(String code);
	Employee findByIdEmp(int id);
	void updateEmployee(Employee emp) throws Exception;
	List<Employee> filterEmp(EmployeeFilter employeeFilter, Page page, String orderBy, int status);
	long numOfEmployee(EmployeeFilter employeeFilter, int status);

}
