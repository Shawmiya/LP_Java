package com.lp.employeeservice.controller;

import com.lp.employeeservice.model.Employee;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
public class EmployeeController {
    @RequestMapping("/employees")
    public List<Employee> getAllEmployee(){
        return Employee.getAllEmployees();
    }


}
