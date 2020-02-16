package com.example.allocationService.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.allocationService.model.Allocation;
@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Integer> {
	
	List<Allocation> findByEmpId(Integer id);
}
