package com.syntask.sale.entity;

public class EmployeeFilter {

	private String filterEmpCode;
	private String filterName;
	private String filterGender;

	public EmployeeFilter() {
		filterEmpCode = "";
		filterName = "";
		filterGender = "";
	}

	public String getFilterEmpCode() {
		return filterEmpCode;
	}

	public void setFilterEmpCode(String filterEmpCode) {
		this.filterEmpCode = filterEmpCode.toLowerCase();
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName.toLowerCase();
	}

	public String getFilterGender() {
		return filterGender;
	}

	public void setFilterGender(String filterGender) {
		this.filterGender = filterGender.toLowerCase();
	}

}
