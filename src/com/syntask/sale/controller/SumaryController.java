package com.syntask.sale.controller;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.ws.rs.DefaultValue;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.web.RequestParameter;

import com.syntask.sale.entity.Employee;
import com.syntask.sale.service.EmployeeService;

@Name(value = "sumaryController")
@Scope(ScopeType.CONVERSATION)
public class SumaryController {

	private List<Employee> employees;
	private Employee emp;
	private int currentPage;
	private final int PAGE_SIZE = 10;

	@In(value = "employeeService", create = true)
	private EmployeeService employeeService;

	@Create
	public void init() {
		currentPage = 1;
		employees = getData(currentPage);
	}

	public void nextPage(){
		employees = getData(++currentPage);
	}
	
	public void previousPage(){
		employees = getData(--currentPage);
	}
	
	public void getEmployeesPageIndex(int index){
		currentPage = index;
		employees = getData(currentPage);
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	private List<Employee> getData(int pageIndex){
		return employeeService.getEmployees(PAGE_SIZE, pageIndex);
	}

}
