package com.syntask.sale.controller;

import javax.faces.model.SelectItem;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.web.ServletContexts;

import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;

@Name(value = "employeeController")
@Scope(ScopeType.CONVERSATION)
public class EmployeeController {

	private Employee employee;
	private EmployeeDao employeeDao;

	@Create
	public void init() {

		Conversation.instance().begin();
		employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
		String empCode = ServletContexts.getInstance().getRequest().getParameter("code");

		if (empCode == null)
			createNewEmployee();
		else
			employee = employeeDao.findEmployee(empCode);
	}

	@Destroy
	public void destroy() {
		Conversation.instance().end();
	}
	
	public String close(){
		Conversation.instance().end();
		return "/sumary.jsf?faces-redirect=true";
	}

	public void update() {
		if(employee.getStatus() == 0 && employee.isValidInfo())
			employee.setStatus(1);
		employeeDao.updateEmployee(employee);
	}
	
	public String delete(){
		employeeDao.deleteEmployee(employee);
		Conversation.instance().end();
		return "/sumary.jsf?faces-redirect=true";
	} 

	public void createNewEmployee() {
		employee = new Employee(true);
		employeeDao.insertEmployee(employee);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SelectItem[] getGender() {
		return new SelectItem[] { new SelectItem(new Integer(0), "Male"), new SelectItem(new Integer(1), "Female") };
	}

	public SelectItem[] getTypeEmployee() {
		return new SelectItem[] { new SelectItem(new Integer(0), "Domestic"),
				new SelectItem(new Integer(1), "Commercial"), new SelectItem(new Integer(2), "Dealer") };
	}

}
