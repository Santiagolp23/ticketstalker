package com.nocturnal.ticketstalker.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repo;

    public Optional<ProjectEntity> findById(Long id){
        return repo.findById(id);
    }

    public List<ProjectEntity> listAllProjects() {
        return repo.findAll();
    }
}
