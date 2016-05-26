package com.syntask.sale.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static String normalizeEmpName(String name) {

		if(name.trim().length() == 0)
			return name.trim();
		
		String[] tokens = name.split(" ");
		String strName = "";
		for (String token : tokens)
			strName += token.substring(0, 1).toUpperCase() + token.substring(1, token.length()) + " ";

		return strName.trim();
	}
	
	public static String normalizeEmpCode(String empCode){
		Pattern pattern = Pattern.compile("([a-zA-Z]+)([0-9]+)");
		Matcher matcher = pattern.matcher(empCode);
		boolean find = matcher.find();
		String prefix = matcher.group(1);
		String code = matcher.group(2);
		
		return prefix.toUpperCase() + Integer.valueOf(code);
	}

}
