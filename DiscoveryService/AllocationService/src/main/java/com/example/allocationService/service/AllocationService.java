package com.example.allocationService.service;

import java.util.List;

import com.example.allocationService.model.Allocation;

public interface AllocationService {
    Allocation createAllocation(Allocation allocation);

    List<Allocation> fetchAllAllocations();

    List<Allocation> getAllocationByEmployeeId(Integer id);

}



