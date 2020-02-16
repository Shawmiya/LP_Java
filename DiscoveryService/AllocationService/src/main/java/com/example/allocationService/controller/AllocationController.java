package com.example.allocationService.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.apache.tomcat.jni.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.allocationService.model.Allocation;
import com.example.allocationService.service.AllocationService;

@RestController
@RequestMapping("/service")
public class AllocationController {
	@Autowired
	AllocationService allocationService;



	@RequestMapping(value = "/saveAllocation", method = RequestMethod.POST)
	public Allocation save(@RequestBody Allocation allocation) {
		return allocationService.createAllocation(allocation);
	}

	@RequestMapping(value = "/findAllAllocations", method = RequestMethod.GET)
	public List<Allocation> findAll() {
		return allocationService.fetchAllAllocations();
	}

	@RequestMapping(value= "/findByEmployeeId/{id}", method = RequestMethod.GET)  	
	    	public List<Allocation> findById(@PathVariable Integer id) {
	        return allocationService.getAllocationByEmployeeId(id);

	    }

}
