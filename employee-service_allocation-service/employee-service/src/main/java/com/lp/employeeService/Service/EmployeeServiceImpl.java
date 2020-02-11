package com.lp.employeeService.Service;

import com.lp.employeeService.Model.Address;
import com.lp.employeeService.Model.Employee;
import com.lp.employeeService.Model.Project;
import com.lp.employeeService.Model.Telephone;
import com.lp.employeeService.Repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findEmployee(int id){
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            return employee.get();
        }
        return null;
    }

    public List<Employee> fetchAllEmployee(){
        List<Employee> employeeList = employeeRepository.findAll();

        if(employeeList.isEmpty()){
            return null;
        }
        return  employeeList;
    }

}
