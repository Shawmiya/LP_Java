package com.krishantha.training.salesmanager.repository;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.krishantha.training.salesmanager.model.Employee;

@Repository("employeeRepository")
public class HibernateEmployeeRepositoryImpl implements EmployeeRepository {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.krishantha.training.salesmanager.repository.EmployeeRepository#
	 * getAllEmployee()
	 */

	@Value("${name}")
	private String employeeName;
	@Value("${country}")
	private String employeeLocation;

	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<>();

		Employee employee = new Employee();
		employee.setEmployeeName(employeeName);
		employee.setEmployeeLocation(employeeLocation);

		employees.add(employee);

		return employees;
	}

}
