package com.lp.employeeService.Service;

import java.util.List;

import com.lp.employeeService.Model.Address;
import com.lp.employeeService.Model.Employee;
import com.lp.employeeService.Model.Project;
import com.lp.employeeService.Model.Telephone;

public interface EmployeeService  {
    Employee save(Employee employee);

    Employee findEmployee(int id);

    List<Employee> fetchAllEmployee();
}
