package com.example.allocationService.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Allocation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;

	Integer empId;
	String start;
	String end;
	String projectCode;

}
