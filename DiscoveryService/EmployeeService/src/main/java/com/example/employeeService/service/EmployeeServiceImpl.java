package com.example.employeeService.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public Employee findByEmployeeId(Integer id) {
        Optional<Employee> emp = employeeRepository.findById(id);
        if (emp.isPresent()) {
            Employee employee = emp.get();

            HttpHeaders httpHeaders = new HttpHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<String>("", httpHeaders);

            ResponseEntity<Allocation[]> responseEntity = restTemplate.exchange(
                    "http://localhost:8181/service/findByEmployeeId/" + id, HttpMethod.GET, httpEntity, Allocation[].class);

            // if(responseEntity.getStatusCode().value()==200) {
            //return responseEntity.getBody();
            employee.setAllocation(responseEntity.getBody());
            return employee;

        } else {
            return null;
        }
    }


}
