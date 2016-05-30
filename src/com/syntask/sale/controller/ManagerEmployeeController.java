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
	protected String orderBy;
	protected int status;
	protected EmployeeDao employeeDao;
	protected Page page;
	
	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

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
		orderBy = "emp_code";
		int numOfEmp = (int) employeeDao.numOfEmployee(empFilter, status);
		page.setNumOfPage(numOfEmp / page.getPageSize() + (numOfEmp % page.getPageSize() == 0 ? 0 : 1));
		getDataAtPage(page.getFirstPage());
	}

	@Destroy
	public void destroy() {
		Conversation.instance().end();
	}
	
	public void filter() {
		page.setCurrentPage(page.getFirstPage());
		employees = employeeDao.filterEmp(empFilter, page, orderBy, status);
		int numOfEmp = (int) employeeDao.numOfEmployee(empFilter, status);
		page.setNumOfPage(numOfEmp / page.getPageSize() + (numOfEmp % page.getPageSize() == 0 ? 0 : 1));
	}
	
	public void nextPage(){
		page.setCurrentPage(page.getCurrentPage() + 1);
		getDataAtPage(page.getCurrentPage());
	}
	
	public void previousPage(){
		page.setCurrentPage(page.getCurrentPage() - 1);
		getDataAtPage(page.getCurrentPage());
	}
	
	public void firstPage(){
		getDataAtPage(page.getFirstPage());
	}
	
	public void lastPage(){
		getDataAtPage(page.getLastPage());
	}
	
	public void getDataAtPage(int pageIndex){
		page.setCurrentPage(pageIndex);
		employees = employeeDao.filterEmp(empFilter, page, orderBy, status);
	}
	
/*	public List<Employee> getEmployees(int pageIndex) {
		return employeeDao.getEmployees(page.getPageSize(), pageIndex, status);
	}*/

	public Page getPage() {
		return page;
	}
	
}
