package com.lp.AllocationService.Repository;

import com.lp.AllocationService.Model.Allocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation,Integer> {
}
