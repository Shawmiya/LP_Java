package com.krishantha.training.salesmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.krishantha.training.salesmanager.model.Employee;
import com.krishantha.training.salesmanager.repository.EmployeeRepository;
//import com.krishantha.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
//	EmployeeRepository employeeRepository = new HibernateEmployeeRepositoryImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.krishantha.training.salesmanager.service.EmployeeService#getAllEmployees(
	 * )
	 */

	public EmployeeServiceImpl() {
		System.out.println("default constructor executed");
	}

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		System.out.println("overloaded constructor executed");
		this.employeeRepository = employeeRepository;
	}

	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}

	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		System.out.println("setter injection fired");
		this.employeeRepository = employeeRepository;
	}

}
