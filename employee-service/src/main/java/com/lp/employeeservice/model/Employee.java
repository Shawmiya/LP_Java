package com.lp.employeeservice.model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private String name;
    private  int age;

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<Employee>();

        Employee employee = new Employee();
        employee.setName("John");
        employee.setAge(25);
        employees.add(employee);
        employee.setName("Kenn");
        employee.setAge(20);
        employees.add(employee);
        employee.setName("Ann");
        employee.setAge(30);
        employees.add(employee);

        return employees;
    }
}
