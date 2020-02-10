package com.example.employeeService.controller;

import com.example.employeeService.Model.Employee;
import com.example.employeeService.Service.EmployeeService;

import javax.xml.bind.annotation.XmlRootElement;

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
}
