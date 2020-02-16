package com.example.employeeService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@Data
public class Projects {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	String projectName;
	
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "employee_project",
			joinColumns = {@JoinColumn(name = "proId", referencedColumnName = "id") },
			inverseJoinColumns = {@JoinColumn(name = "empId", referencedColumnName = "id") })
	List<Employee> employee;

}
