package com.nocturnal.ticketstalker.project;

import com.nocturnal.ticketstalker.user.UserEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "projects")
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private UserEntity managerId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", length = 1000)
    private String description;

    public ProjectEntity() {
    }

    public ProjectEntity(UserEntity managerId, String name, String description) {
        this.managerId = managerId;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserEntity getManager() {
        return managerId;
    }

    public void setManager(UserEntity manager) {
        this.managerId = manager;
    }
}
