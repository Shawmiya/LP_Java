package com.example.employeeService.service;

import java.util.List;
import java.util.Optional;

import com.example.employeeService.hystrix.AllocationCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.StreamingHttpOutputMessage.Body;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.employeeService.model.Employee;
import com.example.employeeService.repository.EmployeeRepository;
import com.example.employeeService.sharedModel.Allocation;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Bean
    @LoadBalanced
    RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Override
    public Employee createEmployee(Employee employee) {

        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    public Allocation[] fetchAllocation(Employee employee) throws Exception {

        HttpHeaders httpHeaders = new HttpHeaders();
        AllocationCommand allocationCommand = new AllocationCommand(employee, httpHeaders, restTemplate);
        return allocationCommand.run();
    }

    @Override
    public Employee findByEmployeeId(Integer id) throws Exception {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();
            
            employee.setAllocation(fetchAllocation(employee));
            return employee;

        } else {
            return null;
        }
    }




}
