package com.syntask.sale.util;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;

public class CustomConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String arg2) throws ConverterException{

		switch (component.getId()) {
		case "code":
			
			Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]+)");
			Matcher matcher = pattern.matcher(arg2.toString());
			if(!matcher.find())
				throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Format code is {Prefix + code}, example: SA1001, EMP1001"));
			
			arg2 = StringUtil.normalizeEmpCode(arg2);
			break;

		case "name":
			arg2 = StringUtil.normalizeEmpName(arg2);
			break;

		case "short_name":
			arg2 = StringUtil.normalizeEmpName(arg2);
			break;
			
		case "address":
			arg2 = StringUtil.normalizeEmpName(arg2);
			break;

		default:
			break;
		}

		return arg2;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object data) {
		if (data instanceof Date) {
			Date date = (Date) data;
			int day = date.getDate();
			int month = date.getMonth() + 1;
			int year = date.getYear() + 1900;

			return StringUtils.leftPad(String.valueOf(day), 2, "0") + "-"
					+ StringUtils.leftPad(String.valueOf(month), 2, "0") + "-" + year;
		}
		return data.toString();

	}

}
