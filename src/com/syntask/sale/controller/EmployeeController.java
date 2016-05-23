package com.syntask.sale.controller;

import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.Conversation;

import com.syntask.sale.entity.Employee;
import com.syntask.sale.service.EmployeeService;

@Name(value = "employeeController")
@Scope(ScopeType.CONVERSATION)
public class EmployeeController {
	
	@In(value = "#{employee}")
	private Employee employee;
	
	//@In(value = "employeeService" , create = true)
	private EmployeeService employeeService;
	
	@Create
	public void init(){
		Conversation.instance().begin();
		employeeService = (EmployeeService) Component.getInstance("employeeService");
	}
	
	public void update(){
		employeeService.update(employee);
	}
	
	public void insert(){
		employeeService.insert(employee);
	}
}
