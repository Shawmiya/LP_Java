package com.example.employeeService.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telephone {
	
	@Id
	@GeneratedValue
	private Integer id;
	private String telephoneNumber;
	
	@ManyToOne
	@JoinColumn
	@JsonIgnore
	Employee employee;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	

}
