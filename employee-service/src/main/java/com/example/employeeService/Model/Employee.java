package com.example.employeeService.Model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
	private String name;
	private int marks;

	public Employee(String name, int marks) {
		this.name = name;
		this.marks = marks;
	}

	public Employee() {
	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public static List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<Employee>();

		Employee employee = new Employee();
		employee.setName("John");
		employee.setMarks(25);
		employees.add(employee);
		employee.setName("Ann");
		employee.setMarks(20);
		employees.add(employee);
		employee.setName("Kenn");
		employee.setMarks(10);
		employees.add(employee);

		return employees;
	}
}
