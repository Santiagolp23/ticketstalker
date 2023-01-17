package com.nocturnal.ticketstalker.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusService {
    @Autowired
    private StatusRepository repo;

    public Optional<StatusEntity> findById(Integer id){
        return repo.findById(id);
    }

    public List<StatusEntity> listAllStatuses() {
        return repo.findAll();
    }
}
