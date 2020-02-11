package com.lp.AllocationService.Controller;

import com.lp.AllocationService.Model.Allocation;
import com.lp.AllocationService.Service.AllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@RestController
@RequestMapping("/Allocation")
@XmlRootElement
public class AllocationController {

    @Autowired
    AllocationService allocationService;

    @RequestMapping(value = "/createAllocation" , method = RequestMethod.POST)
    public Allocation save(@RequestBody Allocation allocation){
        return allocationService.save(allocation);
    }

    @RequestMapping(value="/findAllocationById/{id}",method = RequestMethod.POST)
    public Allocation findEmployee(@PathVariable("id") Integer id){
        return allocationService.findAllocationById(id);
    }

    @RequestMapping(value="/fetchAllAllocations",method = RequestMethod.GET)
    public List<Allocation> fetchAllEmployees(){
        return allocationService.fetchAllAllocations();
    }
}