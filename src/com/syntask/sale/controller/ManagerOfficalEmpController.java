package com.syntask.sale.controller;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name(value = "managerOfficalEmpController")
@Scope(ScopeType.CONVERSATION)
public class ManagerOfficalEmpController extends ManagerEmployeeController{

	@Override
	public void init() {
		status = 1;
	}


}
