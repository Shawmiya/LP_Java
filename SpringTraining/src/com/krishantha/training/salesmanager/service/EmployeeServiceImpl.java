package com.krishantha.training.salesmanager.service;

import java.util.List;

import com.krishantha.training.salesmanager.model.Employee;
import com.krishantha.training.salesmanager.repository.EmployeeRepository;
import com.krishantha.training.salesmanager.repository.HibernateEmployeeRepositoryImpl;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository employeeRepository ;
//	EmployeeRepository employeeRepository = new HibernateEmployeeRepositoryImpl();

	/* (non-Javadoc)
	 * @see com.krishantha.training.salesmanager.service.EmployeeService#getAllEmployees()
	 */
	
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.getAllEmployees();
	}

}
