package com.lp.employeeService.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;

	@OneToOne(cascade = CascadeType.ALL)
	Address Address;
	
	@OneToMany(mappedBy="employee",cascade = CascadeType.ALL)
	List<Telephone>telephones;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name="project_employee",
			   joinColumns= @JoinColumn (name="eid", referencedColumnName="id"),
			   inverseJoinColumns=@JoinColumn(name="pid",referencedColumnName="id"))
	List<Project>projects;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return Address;
	}

	public void setAddress(Address address) {
		Address = address;
	}

	public List<Telephone> getTelephones() {
		return telephones;
	}

	public void setTelephones(List<Telephone> telephones) {
		this.telephones = telephones;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}
	
	

}
