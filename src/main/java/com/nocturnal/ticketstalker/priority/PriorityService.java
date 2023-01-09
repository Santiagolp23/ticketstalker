package com.nocturnal.ticketstalker.priority;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    @Autowired
    PriorityRepository repo;

    public List<PriorityEntity> listAllPriorities(){
        return repo.findAll();
    }

    public Optional<PriorityEntity> findById(Integer Id){
        return repo.findById(Id);
    }

}
