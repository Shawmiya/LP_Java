package com.lp.AllocationService.Service;

import com.lp.AllocationService.Model.Allocation;
import com.lp.AllocationService.Repository.AllocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllocationServiceImpl implements AllocationService{
    @Autowired
    AllocationRepository allocationRepository;

    @Override
    public Allocation save(Allocation employee){
        return allocationRepository.save(employee);
    }

    public Allocation findAllocationById(int id){
        Optional<Allocation> allocation = allocationRepository.findById(id);

        if(allocation.isPresent()) {
            return allocation.get();
        }
        return null;
    }

    public List<Allocation> fetchAllAllocations(){
        List<Allocation> allocationList = allocationRepository.findAll();

        if(allocationList.isEmpty()){
            return null;
        }
        return  allocationList;
    }
}
