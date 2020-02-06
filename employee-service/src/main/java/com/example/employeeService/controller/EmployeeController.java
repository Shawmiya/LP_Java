package com.example.employeeService.controller;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeService.Model.Employee;

@RestController
@RequestMapping("/Service")
public class EmployeeController {
	@RequestMapping("/employees")
	public 	List<Employee> getAllEmployees() {
		return Employee.getAllEmployees();
	}
}
