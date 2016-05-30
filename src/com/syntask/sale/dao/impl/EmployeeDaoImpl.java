package com.syntask.sale.dao.impl;

import java.util.List;

import org.jboss.seam.annotations.Name;

import com.syntask.sale.dao.BaseDao;
import com.syntask.sale.dao.EmployeeDao;
import com.syntask.sale.entity.Employee;
import com.syntask.sale.entity.EmployeeFilter;
import com.syntask.sale.util.Page;

@Name(value = "employeeDao")
public class EmployeeDaoImpl extends BaseDao<Integer, Employee> implements EmployeeDao {

	private static final long serialVersionUID = 1L;

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

	/*
	 * @Override public List<Employee> getEmployees(int pageSize, int pageIndex,
	 * int status) { return getEntityManager().createQuery(
	 * "select e from Employee e where e.status = :status ", Employee.class)
	 * .setParameter("status", status).setFirstResult((pageIndex - 1) *
	 * pageSize).setMaxResults(pageSize) .getResultList(); }
	 */

	@Override
	public Employee findByCodeEmp(String code) {
		List<Employee> datas = entityManager.createNamedQuery("findByCode", Employee.class).setParameter("code", code)
				.getResultList();
		if (datas.size() > 0)
			return datas.get(0);
		return null;
	}

	@Override
	public void updateEmployee(Employee emp) throws Exception {
		update(emp);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> filterEmp(EmployeeFilter empFilter, Page page, String orderBy, int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;

		if (empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0) {
			if (empFilter.getFilterGender().startsWith("m") && "male".contains(empFilter.getFilterGender().trim())) { // filter by male
				gender1 = gender2 = 0;
			} else if (empFilter.getFilterGender().startsWith("f") && "female".contains(empFilter.getFilterGender().trim())) { // filter by female
				gender2 = gender1 = 1;
			}
		}

		if (empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0) { // do not filter
			gender1 = 0;
			gender2 = 1;
		}

		/*
		 * String queryStr =
		 * "SELECT e FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY('e.birdth') = "
		 * + empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr
		 * += " AND MONTH('e.birdth') = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(e.birdth) = " +
		 * String.valueOf(empFilter.getFilterYear());
		 * 
		 * queryStr += " ORDER BY " + orderBy + " ASC";
		 */

		/*
		 * String queryStr =
		 * "SELECT * FROM DEMO_ALEX_EMPLOYEE where LOWER(emp_code) LIKE :code AND LOWER(name) LIKE :name AND (gender = :gender1 OR e.gender = :gender2) AND status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY(birdth) = " +
		 * empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr +=
		 * " AND MONTH(birdth) = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(birdth) = " +
		 * empFilter.getFilterYear();
		 */

		String queryStr = "SELECT * FROM DEMO_ALEX_EMPLOYEE where ";

		if (empFilter.getFilterEmpCode().trim().length() > 0)
			queryStr += "LOWER(emp_code) LIKE '" + empFilter.getFilterEmpCode() + "%' AND ";
		if (empFilter.getFilterName().trim().length() > 0)
			queryStr += "LOWER(name) LIKE '" + empFilter.getFilterName() + "%' AND ";
		if (gender1 == gender2 && gender1 != -1)
			queryStr += "(gender = " + gender1 + " OR gender = " + gender2 + ") AND ";
		if (empFilter.isFilterByDay())
			queryStr += "DAY(birdth) = " + empFilter.getFilterDay() + " AND ";
		if (empFilter.isFilterByMonth())
			queryStr += "MONTH(birdth) = " + empFilter.getFilterMonth() + " AND ";
		if (empFilter.isFilterByYear())
			queryStr += "YEAR(birdth) = " + empFilter.getFilterYear() + " AND ";

		queryStr += " status = " + status;
		queryStr += " ORDER BY " + orderBy + " ASC";

		return entityManager.createNativeQuery(queryStr, Employee.class)
				.setFirstResult((page.getCurrentPage() - 1) * page.getPageSize()).setMaxResults(page.getPageSize())
				.getResultList();

		/*
		 * String queryStr1 =
		 * "select * from DEMO_ALEX_EMPLOYEE where YEAR(birdth) = 1994";
		 * 
		 * return entityManager.createNativeQuery(queryStr1, Employee.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status)
		 * .setFirstResult((page.getCurrentPage() - 1) *
		 * page.getPageSize()).setMaxResults(page.getPageSize())
		 * .getResultList();
		 */

		/*
		 * return entityManager.createNativeQuery(queryStr, Employee.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status)
		 * .setFirstResult((page.getCurrentPage() - 1) *
		 * page.getPageSize()).setMaxResults(page.getPageSize())
		 * .getResultList();
		 */
	}

	@Override
	public long numOfEmployee(EmployeeFilter empFilter, int status) {
		int gender1 = -1; // does not match anything
		int gender2 = -1;

		if (empFilter.getFilterGender() != null || empFilter.getFilterGender().trim().length() > 0) {
			if (empFilter.getFilterGender().startsWith("m") && "male".contains(empFilter.getFilterGender().trim())) { // filter by male
				gender1 = gender2 = 0;
			} else if (empFilter.getFilterGender().startsWith("f")
					&& "female".contains(empFilter.getFilterGender().trim())) { // filter by female
				gender2 = gender1 = 1;
			}
		}

		if (empFilter.getFilterGender() == null || empFilter.getFilterGender().trim().length() == 0) { // do not filter
			gender1 = 0;
			gender2 = 1;
		}

		/*
		 * String queryStr =
		 * "SELECT count(e.id) FROM Employee e where LOWER(e.empCode) LIKE :code AND LOWER(e.name) LIKE :name AND (e.gender = :gender1 OR e.gender = :gender2) AND e.status = :status"
		 * ; if(empFilter.isFilterByDay()) queryStr += " AND DAY(e.birdth) = " +
		 * empFilter.getFilterDay(); if(empFilter.isFilterByMonth()) queryStr +=
		 * " AND MONTH(e.birdth) = " + empFilter.getFilterMonth();
		 * if(empFilter.isFilterByYear()) queryStr += " AND YEAR(e.birdth) = " +
		 * String.valueOf(empFilter.getFilterYear());
		 */

		// String queryStr = "SELECT count(*) FROM DEMO_ALEX_EMPLOYEE where
		// LOWER(emp_code) LIKE :code AND LOWER(name) LIKE :name AND (gender =
		// :gender1 OR gender = :gender2) AND status = :status";
		String queryStr = "SELECT count(*) FROM DEMO_ALEX_EMPLOYEE where ";

		if (empFilter.getFilterEmpCode().trim().length() > 0)
			queryStr += "LOWER(emp_code) LIKE '" + empFilter.getFilterEmpCode() + "%' AND ";
		if (empFilter.getFilterName().trim().length() > 0)
			queryStr += "LOWER(name) LIKE '" + empFilter.getFilterName() + "%' AND ";
		if (gender1 == gender2 && gender1 != -1)
			queryStr += "(gender = " + gender1 + " OR gender = " + gender2 + ") AND ";
		if (empFilter.isFilterByDay())
			queryStr += "DAY(birdth) = " + empFilter.getFilterDay() + " AND ";
		if (empFilter.isFilterByMonth())
			queryStr += "MONTH(birdth) = " + empFilter.getFilterMonth() + " AND ";
		if (empFilter.isFilterByYear())
			queryStr += "YEAR(birdth) = " + empFilter.getFilterYear() + " AND ";
		queryStr += " status = " + status;

		return (long) entityManager.createNativeQuery(queryStr, Long.class).getSingleResult();
		/*
		 * return (long) entityManager.createNativeQuery(queryStr, Long.class)
		 * .setParameter("code", empFilter.getFilterEmpCode() + "%")
		 * .setParameter("name", empFilter.getFilterName() +
		 * "%").setParameter("gender1", gender1) .setParameter("gender2",
		 * gender2).setParameter("status", status).getSingleResult();
		 */

		// String queryStr1 = "select COUNT(*) from DEMO_ALEX_EMPLOYEE where
		// YEAR(birdth) = 1994";
		// return (long) entityManager.createNativeQuery(queryStr1,
		// Long.class).getSingleResult();
	}

	@Override
	public Employee findByIdEmp(int id) {
		return findById(id);
	}

}
