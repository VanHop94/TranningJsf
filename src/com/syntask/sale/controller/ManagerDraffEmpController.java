package com.syntask.sale.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name(value = "managerDraffEmpController")
@Scope(ScopeType.CONVERSATION)
public class ManagerDraffEmpController extends ManagerEmployeeController{

	@Override
	public void init() {
		status = 0;
	}

}
