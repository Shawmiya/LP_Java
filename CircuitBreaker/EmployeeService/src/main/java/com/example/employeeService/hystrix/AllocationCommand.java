package com.example.employeeService.hystrix;

import com.example.employeeService.model.Employee;
import com.example.employeeService.sharedModel.Allocation;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.cloud.netflix.hystrix.HystrixCommands;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class AllocationCommand extends HystrixCommands<Allocation[]>{

    Employee employee;
    HttpHeaders httpHeaders;
    RestTemplate restTemplate;

    public AllocationCommand(Employee employee, HttpHeaders httpHeaders, RestTemplate restTemplate) {
        super(HystrixCommandGroupKey.Factory.asKey("default"));
        this.employee = employee;
        this.httpHeaders = httpHeaders;
        this.restTemplate = restTemplate;
    }

    @Override
    public Allocation[] run()throws Exception{

        httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<String>("", httpHeaders);

        ResponseEntity<Allocation[]> responseEntity = restTemplate.exchange(
                "http://allocation/service/findByEmployeeId/" + employee.getId(), HttpMethod.GET, httpEntity, Allocation[].class);
        return responseEntity.getBody();
    }

    //@Override
    protected Allocation[] getFallback(){
        return new Allocation[1];
    }

}
