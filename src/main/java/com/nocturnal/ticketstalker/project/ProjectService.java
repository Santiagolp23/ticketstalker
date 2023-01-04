package com.nocturnal.ticketstalker.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    public ProjectEntity findById(String id){
        return repo.findById(id);
    }
}
