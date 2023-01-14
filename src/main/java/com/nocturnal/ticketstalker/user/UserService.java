package com.nocturnal.ticketstalker.user;

import com.nocturnal.ticketstalker.priority.PriorityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<UserEntity> listAllUsers() {
        return repo.findAll();
    }

    public Optional<UserEntity> findById(Long Id){
        return repo.findById(Id);
    }
}