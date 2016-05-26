package com.syntask.sale.dao.impl;

import java.util.List;

import org.jboss.seam.annotations.Name;

import com.syntask.sale.dao.BaseDao;
import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Page;

@Name(value = "employeeDao")
public class EmployeeDaoImpl extends BaseDao<String, Employee> implements EmployeeDao {

	public EmployeeDaoImpl() {
		super();
	}

	@Override
	public void insertEmployee(Employee emp) {
		persist(emp);
	}

	@Override
	public void deleteEmployee(Employee emp) {
		delete(emp);
	}

	@Override
	public List<Employee> getEmployees(int pageSize, int pageIndex,int status) {
		return getEntityManager().createQuery("select e from Employee e where e.status = :status ", Employee.class)
				.setParameter("status", status)
				.setFirstResult((pageIndex - 1) * pageSize)
				.setMaxResults(pageSize)
				.getResultList();
	}
	

	@Override
	public Employee findEmployee(String id) {
		return findById(id);
	}

	@Override
	public void updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		update(emp);

	}

	@Override
	public List<Employee> filterEmp(EmployeeFilter empFilter, Page page,int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;
		
		if(empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0){
			if(empFilter.getFilterGender().startsWith("m") && "male".contains(empFilter.getFilterGender().trim())){  //filter by male
				gender1 = gender2 = 0;
			} else if (empFilter.getFilterGender().startsWith("f") && "female".contains(empFilter.getFilterGender().trim())){ //filter by female
				gender2 = gender1 = 1;
			}
		}
		
		if(empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0){ // do not filter
			gender1 = 0;
			gender2 = 1;
		}

		return getEntityManager().createNamedQuery("filterEmployee", Employee.class)
					.setParameter("code", empFilter.getFilterEmpCode() + "%")
					.setParameter("name", empFilter.getFilterName() + "%")
					.setParameter("gender1", gender1)
					.setParameter("gender2", gender2)
					.setParameter("status", status)
					.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize())
					.setMaxResults(page.getPageSize())
					.getResultList();
	}
	
	@Override
	public long numOfEmployee(EmployeeFilter empFilter,int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;
		
		if(empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0){
			if(empFilter.getFilterGender().startsWith("m") && "male".contains(empFilter.getFilterGender().trim())){  //filter by male
				gender1 = gender2 = 0;
			} else if (empFilter.getFilterGender().startsWith("f") && "female".contains(empFilter.getFilterGender().trim())){ //filter by female
				gender2 = gender1 = 1;
			}
		}
		
		if(empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0){ // do not filter
			gender1 = 0;
			gender2 = 1;
		}

		return getEntityManager().createNamedQuery("countEmployee", Long.class)
					.setParameter("code", empFilter.getFilterEmpCode() + "%")
					.setParameter("name", empFilter.getFilterName() + "%")
					.setParameter("gender1", gender1)
					.setParameter("gender2", gender2)
					.setParameter("status", status)
					.getSingleResult();
	} 

}
