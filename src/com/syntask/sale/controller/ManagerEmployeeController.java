package com.syntask.sale.controller;

import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.core.Conversation;

import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Page;

public abstract class ManagerEmployeeController {

	protected List<Employee> employees;
	protected EmployeeFilter empFilter;
	protected int status;
	protected EmployeeDao employeeDao;
	protected Page page;

	public EmployeeFilter getEmpFilter() {
		return empFilter;
	}

	public void setEmpFilter(EmployeeFilter empFilter) {
		this.empFilter = empFilter;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public abstract void init();

	@Create
	public void initData() {
		init();
		Conversation.instance().begin();
		employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
		empFilter = new EmployeeFilter();
		page = new Page();
		employees = getEmployees(page.getFirstPage());
		page.setNumOfPage((int) (employeeDao.numOfEmployee(empFilter, status) / page.getPageSize()));
		page.setLastPage(page.getNumOfPage());
	}

	@Destroy
	public void destroy() {
		Conversation.instance().end();
	}

	public List<Employee> getEmployees(int pageIndex) {
		return employeeDao.getEmployees(page.getPageSize(), pageIndex, status);
	}

	public void filter() {
		employees = employeeDao.filterEmp(empFilter, page, status);
	}

}
