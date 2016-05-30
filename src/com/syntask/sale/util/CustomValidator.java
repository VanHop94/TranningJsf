package com.syntask.sale.util;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.jboss.seam.Component;

import com.syntask.sale.dao.EmployeeDao;

public class CustomValidator implements Validator {

	@SuppressWarnings("deprecation")
	@Override
	public void validate(FacesContext arg0, UIComponent component, Object input) throws ValidatorException {

		switch (component.getId()) {
		case "code":
			
			if(input.toString().length() < 3)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Length of code is at least 3"));
			
			EmployeeDao employeeDao = (EmployeeDao) Component.getInstance("employeeDao");
			if(employeeDao.findByCodeEmp(input.toString()) != null)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Employee is exist"));
			
			break;

		case "name":
			
			if(input.toString().length() < 3 || input.toString().length() > 20)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Length of name is from 3 -> 20 character"));
			
			break;

		case "birdth":

			Date date = (Date) input;
			if(date.compareTo(new Date(0,1,1)) < 0 || date.compareTo(new Date()) > 0)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Date is invalided"));
			
			break;

		case "short_name":
			if(input.toString().length() < 2 || input.toString().length() > 20)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Length of short name is from 2 -> 20 character"));
			
			break;

		case "address":
			
			if(input.toString().length() < 3 || input.toString().length() > 100)
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Length of address is from 3 -> 100 character"));

			break;

		default:
			break;
		}

	}
	
}
