package com.example.employeeService.controller;

import com.example.employeeService.Model.Address;
import com.example.employeeService.Model.Employee;
import com.example.employeeService.Model.Project;
import com.example.employeeService.Model.Telephone;
import com.example.employeeService.Service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.sql.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Service")
@XmlRootElement
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping(value = "/displayEmployees" , method = RequestMethod.POST)
	public Employee save(@RequestBody Employee employee){
		return employeeService.save(employee);
	}
	
	@RequestMapping(value="/testFetch")
	Employee TestFetch(){
		List<Telephone> telephones = new ArrayList<>();
		//List<Employee> employees = new ArrayList<>();
		List<Project> projects = new ArrayList<>();
		
		Address address = new Address();
		address.setId(1);
		address.setCity("Colombo");

		Project project = new Project();
		project.setId(1);
		project.setProjectName("ProjectName");
		//project.setEmployees(employees);
		projects.add(project);
		
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("ABC");
		employee.setAddress(address);
		employee.setTelephones(telephones);
		employee.setProjects(projects);
		//employees.add(employee);
		
		Telephone telephone = new Telephone();
		telephone.setId(1);
		telephone.setTelephoneNumber("45435345");
		telephone.setEmployee(employee);
		telephones.add(telephone);
		
		return employee;
	}
	
	@RequestMapping(value = "/save" , method = RequestMethod.POST)
	Employee saveEmployee(@RequestBody Employee employee){
		
		for(Telephone telephone: employee.getTelephones()) {
			telephone.setEmployee(employee);
		}
		
		return employeeService.save(employee);
	}
	
}
