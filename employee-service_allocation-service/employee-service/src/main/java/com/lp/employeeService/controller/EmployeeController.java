package com.lp.employeeService.controller;

import com.lp.employeeService.Model.Address;
import com.lp.employeeService.Model.Employee;
import com.lp.employeeService.Model.Project;
import com.lp.employeeService.Model.Telephone;
import com.lp.employeeService.Service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/Employee")
@XmlRootElement
public class EmployeeController {

	 @Autowired
    EmployeeService employeeService;

    @RequestMapping("/allocation/{id}")
    public Employee getAllemployees(@PathVariable int id) {
        Employee employee = employeeService.fethAllEmployees(id);
        return employee;
    }

    @RequestMapping("test")
    public Employee test() {
        Employee employee = new Employee();
        employee.setId(1);
        employee.setName("john");
        employee.setMarks("80");

        
        Address adress = new Address();
        adress.setCity("Galle");
        employee.setAddress(adress);

        Telephone telephone = new Telephone();
        telephone.setNo(23462144);

        Telephone telephone1 = new Telephone();
        telephone1.setNo(66779433);

        List<Telephone> list = new ArrayList<>();
        list.add(telephone);
        list.add(telephone1);

        employee.setTelephones(list);

        
        employee.getTelephones().stream().forEach(t -> {
            t.setEmployee(employee);
        });
        

       
        Project project = new Project();
        project.setName("project1");

        Project project1 = new Project();
        project1.setName("project2");

        List<Project> projectList = new ArrayList<Project>();
        projectList.add(project);
        projectList.add(project1);
     

        employee.setProjects(projectList);

        return employee;
    }

    
    @RequestMapping("/employee/{id}")
    public Employee getEmployee(@PathVariable int id) {
        if (id == 0) {
            Employee employee = new Employee();
            employee.setId(1);
            employee.setName("John");
            return employee;
        } else
            return employeeService.findById(id);

    }

    
    @RequestMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable int id) {
        return employeeService.deleteEmployee(id);
    }

    
    @Transactional(rollbackOn = TransactionalException.class)
    @RequestMapping(value = "/employees", method = RequestMethod.POST)
    public Employee getEmployees(@RequestBody Employee e) {

        
        e.getTelephones().stream().forEach(t -> {
            t.setEmployee(e);
        });

        Employee emp = employeeService.save(e);
        return emp;
    }
	
}
