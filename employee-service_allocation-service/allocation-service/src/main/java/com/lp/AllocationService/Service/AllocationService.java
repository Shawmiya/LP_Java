package com.lp.AllocationService.Service;

import com.lp.AllocationService.Model.Allocation;

import java.util.List;

public interface AllocationService {
    Allocation save(Allocation allocation);

    Allocation findAllocationById(int id);

    List<Allocation> fetchAllAllocations();
}
